package com.example.zti.presentation.main_activity.fragment.notification_fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.zti.adapters.deposit.NotificationAdapter;
import com.example.zti.databinding.FragmentNotificationBinding;
import com.example.zti.model.network.response.FetchNotificationResponse;
import com.example.zti.model.network.service.FetchNotificationService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationFragment extends Fragment {
    FragmentNotificationBinding mBinding;
    FetchNotificationService mFetchNotificationService;
    Retrofit mRetrofit;
    SharedPreferences mSharedPreferences;
    NotificationAdapter notificationAdapter;
    List<FetchNotificationResponse> fetchNotificationResponse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mBinding = FragmentNotificationBinding.inflate(getLayoutInflater());
        mBinding.back.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mSharedPreferences = requireContext().getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://zeeenterprises.online/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mFetchNotificationService = mRetrofit.create(FetchNotificationService.class);

        notificationAdapter = new NotificationAdapter(new ArrayList<>());
        mBinding.recyclerView.setAdapter(notificationAdapter) ;

        userNotify("ec6734cde10a5a7615c8cbbb40f0f8ae19019245",
                mSharedPreferences.getString("id", null));


        return mBinding.getRoot();
    }

    public  void  userNotify(
            String apikey, String userUid
    ){
        mFetchNotificationService.fetchNotification(apikey, userUid)
                .enqueue(new Callback<FetchNotificationResponse>() {
                    @Override
                    public void onResponse(Call<FetchNotificationResponse> call, Response<FetchNotificationResponse> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(requireContext(), "Invalid Response!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (response.body().state.equals("OK")) {
                              notificationAdapter.setUserNotificationsModels(response.body().data.userNotifications);
                            } else if (response.body().data.exceptions.noNotificationFound) {
                                Toast.makeText(requireContext(), "No Notification are found ", Toast.LENGTH_SHORT).show();
                            } else {

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<FetchNotificationResponse> call, Throwable t) {
                        Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
package com.example.zti.presentation.main_activity.fragment.deposit_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zti.adapters.BankAdapter;
import com.example.zti.adapters.BankAdapterEvent;
import com.example.zti.databinding.FragmentChooseBankBinding;
import com.example.zti.presentation.MyApp;

import java.util.ArrayList;
import java.util.List;

public class ChooseBankFragment extends Fragment{
    FragmentChooseBankBinding mBinding;
    List<String> banks;
    BankAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentChooseBankBinding.inflate(getLayoutInflater());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mBinding.backward.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
        mBinding.cross.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
        banks = new ArrayList<>();
        banks.add("EASY PAISA");
        banks.add("JAZZ CASH");
        banks.add("MCB");
        banks.add("BOP");
        banks.add("Allied Bank");
        banks.add("Bank Alfalah");
        banks.add("Payonner");
        banks.add("Sadapay");
        banks.add("Nayapay");
        banks.add("EASY PAISA");
        banks.add("JAZZ CASH");
        banks.add("MCB");
        banks.add("BOP");
        banks.add("Allied Bank");
        banks.add("Bank Alfalah");
        banks.add("Payonner");
        banks.add("Sadapay");
        banks.add("Nayapay");

        adapter = new BankAdapter(banks, new BankAdapterEvent() {
            @Override
            public void onClicked(int position) {
                String value = banks.get(position);
                ((MyApp) requireContext().getApplicationContext()).bankType = value;
                 Navigation.findNavController(getView()).popBackStack();
                Toast.makeText(requireContext(), "Bank is Clicked on " + position, Toast.LENGTH_SHORT).show();

            }
        });

       mBinding.recyclerView.setAdapter(adapter);
        return mBinding.getRoot();
    }

}
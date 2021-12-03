package com.example.inventory.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventory.R;
import com.example.inventory.databinding.FragmentDashboardBinding;


public class DashBoardFragment extends Fragment implements View.OnClickListener{

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        binding.imageButtonInventory.setOnClickListener(this);
        binding.imageButtonDependency.setOnClickListener(this);
        binding.imageButtonSections.setOnClickListener(this);
        binding.imageButtonProductos.setOnClickListener(this);
        binding.imageButtonSettings.setOnClickListener(this);
        binding.imageButtonAboutUs.setOnClickListener(this);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * este metodo maneja todos los eventos
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButtonAboutUs:
                showAboutUs();
                break;
            case R.id.imageButtonInventory:
                showAddInventory();
                break;
            case R.id.imageButtonDependency:
                showDependencyList();
                break;
        }
    }

    private void showDependencyList() {
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_dependencyListFragment);
    }

    /**
     * Mostrar el fragment que da de alta un inventory
     */
    private void showAddInventory() {
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_addInventoryFragment);
    }

    /**
     * Mostrar el fragment About Us
     */
    private void showAboutUs() {
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_aboutUsFragment);
    }
}
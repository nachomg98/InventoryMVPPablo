package com.example.inventory.ui;

import android.os.Bundle;

import com.example.inventory.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



import com.example.inventory.R;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_aboutus:
                Toast.makeText(this,"Se ha pulsado Sobr Nosotros",Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.action_settings:
                Toast.makeText(this,"Se ha pulsado Ajustes",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(this,"Se ha pulsado buscar",Toast.LENGTH_SHORT).show();
                return true;
            default:
                //Si los fragment modifican el menu de la activity se devuelve false
                return false;
        }

    }
}
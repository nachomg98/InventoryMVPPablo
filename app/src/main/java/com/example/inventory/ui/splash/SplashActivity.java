package com.example.inventory.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.inventory.R;
import com.example.inventory.databinding.ActivitySplashBinding;
import com.example.inventory.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;

    //Declarar variable constante privada
    private static final long WAIT_TIME = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    /**
     * Vamos a similar un tiempo de espera con un hilo que duerme 2 segundos y cuaando despierte se ejecutara
     * un metodo starLogin() que inicia la activity login
     */
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(() -> starLogin(),WAIT_TIME);
    }

    private void starLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        //Voy a llamar de forma explicita al metodo finish() de una activity, para eliminar
        //esta activity de la pila de actividades, porque si el usuario pulsa back no que se visualice de nuevo.
        finish();
    }
}
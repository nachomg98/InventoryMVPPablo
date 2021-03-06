package com.example.inventory.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.base.Event;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.signup.SignUpContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

public class LoginRepositoryImpl implements LoginContract.Repository, SignUpContract.Repository {

    private static final String TAG = LoginRepositoryImpl.class.getName();
    private static LoginRepositoryImpl instance;
    private OnRepositoryCallback callback;



    public static LoginRepositoryImpl getInstance(OnRepositoryCallback listener) {
        if (instance == null) {
            instance = new LoginRepositoryImpl();
        }
        instance.callback = listener;
        return instance;
    }



    @Override
    public void login(User user) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            callback.onSuccess("usuario correcto");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Event loginEvent = new Event();
                            loginEvent.setEventType(Event.onOnLoginError);
                            loginEvent.setMessage(task.getException().toString());

                            //Publica el evento mediante el metodo post
                            EventBus.getDefault().post(loginEvent);
                        }
                    }
                });
    }

    @Override
    public void SignUp(String user, String email, String password, String comfirmPassword) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            callback.onSuccess("usuario correcto");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Event SignUpEvent = new Event();
                            SignUpEvent.setEventType(Event.onSignUpError);
                            SignUpEvent.setMessage(task.getException().toString());

                            //Publica el evento mediante el metodo post
                            EventBus.getDefault().post(SignUpEvent);
                        }
                    }
                });
    }
}

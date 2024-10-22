package com.example.nursecall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity<Common> extends AppCompatActivity {

    public Button button;

    private final static int LOGIN_REQUEST_CODE =7171;
    private List<AuthUI.IdpConfig> providers;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        if(firebaseAuth != null && listener != null)
            firebaseAuth.removeAuthStateListener(listener);
        super.onStop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.go);
        button = (Button) findViewById(R.id.btn_go);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NurseHome.class);
                startActivity(intent);

            }
        });


         init();

    }

    private void init() {
        providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build());


        firebaseAuth = FirebaseAuth.getInstance();
        listener = myFirebaseAuth -> {
            FirebaseUser user = myFirebaseAuth.getCurrentUser();
            if(user!=null)
                delaySplashScreen();
            else
                showLoginLayout();


        };
    }

    private void showLoginLayout() {

        AuthMethodPickerLayout authMethodPickerLayout = new AuthMethodPickerLayout.
                Builder(R.layout.layout_sign_in)
                .setPhoneButtonId(R.id.btngo).build();

        startActivityForResult(AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAuthMethodPickerLayout(authMethodPickerLayout)
        .setIsSmartLockEnabled(false)
        .setAvailableProviders(providers)
        .build(),LOGIN_REQUEST_CODE);


    }


    private void delaySplashScreen() {
        Completable.timer(2, TimeUnit.SECONDS,
                AndroidSchedulers.mainThread())
                .subscribe(() -> Toast.makeText(MainActivity.this, "Hello Nurse!", Toast.LENGTH_SHORT).show());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_REQUEST_CODE)
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK)
            {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            }
            else
            {
                Toast.makeText(this,"[Error]:"+response.getError().getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
package com.example.quixorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.example.quixorder.api.AccountService;
import com.example.quixorder.model.Account;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public EditText usernameInput;
    public EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_login).setOnClickListener(btn_loginOnClickListener);
        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.password);
    }

    private OnClickListener btn_loginOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            AccountService accountService = retrofit.create(AccountService.class);
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            accountService.getAccountByUsername(username).enqueue(new Callback<Account>() {

                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if (response.isSuccessful()) {
                        Log.e("onResponseSuccess.", new Gson().toJson(response.body()));
                        String type = response.body().getType();
                        switch(type) {
                            case "Owner":
                                setContentView(R.layout.owner);
                                break;
                            case "Table":
                                setContentView(R.layout.table);
                            case "Server":
                                setContentView(R.layout.server);
                                break;
                            case "Cook":
                                setContentView(R.layout.cook);
                        }
                    } else {
                        Log.e("onResponseFail.", new Gson().toJson(response.errorBody()));
                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Log.e("onFailure", t.toString());
                }
            });
        }
    };
}

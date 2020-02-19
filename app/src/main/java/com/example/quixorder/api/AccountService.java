package com.example.quixorder.api;

import com.example.quixorder.model.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AccountController {

    @GET("/account")
    Call<List<Account>> getAccounts();

}

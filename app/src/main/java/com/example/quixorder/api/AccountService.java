package com.example.quixorder.api;

import com.example.quixorder.model.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AccountService {

    @GET("account")
    Call<List<Account>> getAllAccount();

    @GET("account/id/{id}")
    Call<Account> getAccountById(@Path("id") String id);

    @GET("account/username/{username}")
    Call<Account> getAccountByUsername(@Path("username") String username);
}

package com.werockstar.rxmultiplesource.api;

import com.werockstar.rxmultiplesource.model.RepoCollection;
import com.werockstar.rxmultiplesource.model.UserInfoCollection;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubApi {
    @GET("users/{user}")
    Observable<UserInfoCollection> getUsers(@Path("user") String username);

    @GET("users/{user}/repos")
    Observable<RepoCollection> getRepo(@Path("user") String user);
}

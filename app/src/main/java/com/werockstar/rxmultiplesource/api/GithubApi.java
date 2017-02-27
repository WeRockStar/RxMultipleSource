package com.werockstar.rxmultiplesource.api;

import com.werockstar.rxmultiplesource.model.RepoCollection;
import com.werockstar.rxmultiplesource.model.UserInfoCollection;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {
    @GET("users/{user}")
    Observable<UserInfoCollection> getUsers(@Path("user") String username);

    @GET("users/{user}/repos")
    Observable<List<RepoCollection>> getRepo(@Path("user") String user);
}

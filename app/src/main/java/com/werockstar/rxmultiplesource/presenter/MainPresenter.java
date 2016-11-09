package com.werockstar.rxmultiplesource.presenter;


import android.util.Log;

import com.werockstar.rxmultiplesource.api.GithubApi;
import com.werockstar.rxmultiplesource.model.RepoCollection;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainPresenter {

    private View view;
    private GithubApi api;
    private CompositeSubscription subscription = new CompositeSubscription();

    public MainPresenter(GithubApi api, View view) {
        this.view = view;
        this.api = api;
    }

    public interface View {
        void onDisplayRepo(List<RepoCollection> repoList);
    }

    public void getRepo(String user) {
        subscription.add(api.getUsers(user)
                .onBackpressureBuffer()
                .flatMap(userInfo -> api.getRepo(userInfo.getLogin()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repo -> {
                    view.onDisplayRepo(repo);
                }, throwable -> {
                    Log.d("Error", throwable.getMessage());
                }));
    }
}

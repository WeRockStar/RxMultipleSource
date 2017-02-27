package com.werockstar.rxmultiplesource.presenter;


import android.util.Log;

import com.werockstar.rxmultiplesource.api.GithubApi;
import com.werockstar.rxmultiplesource.model.RepoCollection;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private View view;
    private GithubApi api;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public MainPresenter(GithubApi api) {
        this.api = api;
    }

    public void attachView(View view) {
        this.view = view;
    }

    public interface View {
        void onDisplayRepo(List<RepoCollection> repoList);

        void loading();

        void loadingComplete();
    }

    public void getUser() {

        view.loading();
        String[] users = new String[]{"google", "facebook", "ReactiveX", "WeRockStar"};
        disposable.add(Observable.fromArray(users)
                .flatMap(u -> api.getUsers(u))
                .flatMap(userInfo -> api.getRepo(userInfo.getLogin()))
                .doOnTerminate(() -> view.loadingComplete())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repo -> {
                    view.onDisplayRepo(repo);
                }, throwable -> {
                    Log.d("Error", throwable.getMessage());
                }));
    }

    public void onDestroy() {
        disposable.clear();
    }
}

package com.werockstar.rxmultiplesource.presenter;


import android.util.Log;

import com.werockstar.rxmultiplesource.api.GithubApi;
import com.werockstar.rxmultiplesource.model.RepoCollection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private View view;
    private GithubApi api;
    private CompositeDisposable disposable = new CompositeDisposable();

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    public MainPresenter(GithubApi api) {
        this.api = api;
    }

    public void attachView(View view) {
        this.view = view;
    }

    public interface View {
        void onDisplayRepo(List<RepoCollection> repoList);

        void showLoading();

        void dismissLoading();
    }

    public void getUser() {
        final String google = "google";
        final String reactiveX = "ReactiveX";
        final String facebook = "facebook";
        final String weRockStar = "WeRockStar";

        Observable<List<RepoCollection>> googleRepo = api.getUsers(google)
                .flatMap(u -> api.getRepo(u.getLogin()));

        Observable<List<RepoCollection>> facebookRepo = api.getUsers(facebook)
                .flatMap(u -> api.getRepo(u.getLogin()));

        Observable<List<RepoCollection>> reactiveXRepo = api.getUsers(reactiveX)
                .flatMap(u -> api.getRepo(u.getLogin()));

        Observable<List<RepoCollection>> werockstarRepo = api.getUsers(weRockStar)
                .flatMap(u -> api.getRepo(u.getLogin()));

        Observable<List<RepoCollection>> repoObs =
                Observable.zip(googleRepo, facebookRepo, reactiveXRepo, werockstarRepo, (g, f, r, w) -> {
                    final List<RepoCollection> repoList = new ArrayList<>();
                    repoList.addAll(g);
                    repoList.addAll(f);
                    repoList.addAll(r);
                    repoList.addAll(w);
                    return repoList;
                }).subscribeOn(Schedulers.io());

        disposable.add(repoObs
                .doOnSubscribe(__ -> {
                    view.showLoading();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(() -> view.dismissLoading())
                .subscribe(repo -> {
                            view.onDisplayRepo(repo);
                        },
                        throwable -> {
                            Log.d(TAG, "getUser error: " + throwable.getMessage());
                        }
                )
        );
    }

    public void onDestroy() {
        disposable.clear();
    }
}

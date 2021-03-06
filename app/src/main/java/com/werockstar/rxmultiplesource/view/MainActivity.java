package com.werockstar.rxmultiplesource.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.werockstar.rxmultiplesource.MainApplication;
import com.werockstar.rxmultiplesource.R;
import com.werockstar.rxmultiplesource.adapter.RepoAdapter;
import com.werockstar.rxmultiplesource.api.GithubApi;
import com.werockstar.rxmultiplesource.model.RepoCollection;
import com.werockstar.rxmultiplesource.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    @Inject
    GithubApi api;

    @Inject
    MainPresenter presenter;

    private Button btnGet;
    private RecyclerView rvRepoList;

    private RepoAdapter adapter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplication()).getComponent().inject(this);
        presenter.attachView(this);

        initialView();
        configurationRecyclerView();
        createProgressDialog();
    }

    private void initialView() {
        btnGet = (Button) findViewById(R.id.btnGet);
        rvRepoList = (RecyclerView) findViewById(R.id.recyclerView);

        btnGet.setOnClickListener(v -> {
            presenter.getUser();
        });
    }

    private void createProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        dialog.setIndeterminate(false);
    }

    private void configurationRecyclerView() {
        adapter = new RepoAdapter();
        rvRepoList.setHasFixedSize(true);
        rvRepoList.setLayoutManager(new LinearLayoutManager(this));
        rvRepoList.setAdapter(adapter);
    }

    @Override
    public void onDisplayRepo(List<RepoCollection> repoList) {
        adapter.setRepo(repoList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void dismissLoading() {
        dialog.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }
}

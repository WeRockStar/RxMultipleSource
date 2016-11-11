package com.werockstar.rxmultiplesource.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.werockstar.rxmultiplesource.MainApplication;
import com.werockstar.rxmultiplesource.R;
import com.werockstar.rxmultiplesource.api.GithubApi;
import com.werockstar.rxmultiplesource.model.RepoCollection;
import com.werockstar.rxmultiplesource.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    @Inject
    GithubApi api;

    MainPresenter presenter;

    EditText edtUsername;
    Button btnSearch;
    RecyclerView rvRepoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplication()).getComponent().inject(this);
        presenter = new MainPresenter(api, this);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        rvRepoList = (RecyclerView) findViewById(R.id.recyclerView);

        btnSearch.setOnClickListener(v -> {
            presenter.getRepo(edtUsername.getText().toString());
        });

        configurationRecyclerView();
    }

    private void configurationRecyclerView() {
        rvRepoList.setHasFixedSize(true);
    }

    @Override
    public void onDisplayRepo(List<RepoCollection> repoList) {

    }
}

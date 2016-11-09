package com.werockstar.rxmultiplesource.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.werockstar.rxmultiplesource.MainApplication;
import com.werockstar.rxmultiplesource.R;
import com.werockstar.rxmultiplesource.api.GithubApi;
import com.werockstar.rxmultiplesource.model.RepoCollection;
import com.werockstar.rxmultiplesource.presenter.MainPresenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    @Inject
    GithubApi api;

    MainPresenter presenter;

    EditText edtUsername;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplication()).getComponent().inject(this);
        presenter = new MainPresenter(api, this);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(v -> {
            presenter.getRepo(edtUsername.getText().toString());
        });
    }

    @Override
    public void onDisplayRepo(RepoCollection collection) {

    }
}

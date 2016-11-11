package com.werockstar.rxmultiplesource.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.werockstar.rxmultiplesource.R;
import com.werockstar.rxmultiplesource.model.RepoCollection;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private List<RepoCollection> repo;

    public void setRepo(List<RepoCollection> repo) {
        this.repo = repo;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item_row, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.tvRepoName.setText(repo.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return repo == null ? 0 : repo.size();
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {

        TextView tvRepoName;

        public RepoViewHolder(View view) {
            super(view);

            tvRepoName = (TextView) view.findViewById(R.id.tvRepoName);
        }
    }
}

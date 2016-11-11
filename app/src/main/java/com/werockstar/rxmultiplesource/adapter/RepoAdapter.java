package com.werockstar.rxmultiplesource.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {

        public RepoViewHolder(View itemView) {
            super(itemView);
        }
    }
}

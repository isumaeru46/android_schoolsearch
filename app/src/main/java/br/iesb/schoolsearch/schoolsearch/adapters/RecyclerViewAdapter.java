package br.iesb.schoolsearch.schoolsearch.adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.iesb.schoolsearch.schoolsearch.Fragments.ViewEscolaDialogFragment;
import br.iesb.schoolsearch.schoolsearch.activities.MapsActivity;
import br.iesb.schoolsearch.schoolsearch.holders.RecyclerViewHolder;
import br.iesb.schoolsearch.schoolsearch.R;
import br.iesb.schoolsearch.schoolsearch.models.EscolaModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter{

    private List<EscolaModel> listaDeEscolas;
    private Context context;

    public RecyclerViewAdapter(List<EscolaModel> escolas, Context context){
        this.listaDeEscolas = escolas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_recycler_view, parent, false);

        RecyclerViewHolder holder = new RecyclerViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerViewHolder holder = (RecyclerViewHolder) viewHolder;

        final EscolaModel escola = listaDeEscolas.get(viewHolder.getAdapterPosition());

        holder.nome.setText(escola.getNome());
        holder.codEscola.setText(escola.getCodEscola().toString());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showEditDialog(escola);
            }
        });
    }

    private void showEditDialog(EscolaModel escola) {
        FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
        ViewEscolaDialogFragment editNameDialogFragment = ViewEscolaDialogFragment.newInstance(escola);
        editNameDialogFragment.show(fm, "fragment_view_escola");
    }


    @Override
    public int getItemCount() {
        return listaDeEscolas.size();
    }
}

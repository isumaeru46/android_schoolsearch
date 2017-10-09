package br.iesb.schoolsearch.schoolsearch.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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

        EscolaModel escola = listaDeEscolas.get(position);

        holder.nome.setText(escola.getNome());
        holder.codEscola.setText(escola.getCodEscola().toString());
    }

    @Override
    public int getItemCount() {
        return listaDeEscolas.size();
    }
}

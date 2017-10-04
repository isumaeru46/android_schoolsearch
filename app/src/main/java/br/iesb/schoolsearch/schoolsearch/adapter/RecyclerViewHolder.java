package br.iesb.schoolsearch.schoolsearch.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.iesb.schoolsearch.schoolsearch.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{

    final TextView nome;
    final TextView codEscola;

    public RecyclerViewHolder(View view) {
        super(view);
        nome = (TextView) view.findViewById(R.id.nomeEscola);
        codEscola = (TextView) view.findViewById(R.id.codEscola);
    }
}

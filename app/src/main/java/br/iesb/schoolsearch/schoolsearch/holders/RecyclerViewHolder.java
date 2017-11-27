package br.iesb.schoolsearch.schoolsearch.holders;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.iesb.schoolsearch.schoolsearch.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout relativeLayout;
    public final TextView nome;
    public final TextView codEscola;

    public RecyclerViewHolder(View view) {
        super(view);
        nome = (TextView) view.findViewById(R.id.nomeEscola);
        codEscola = (TextView) view.findViewById(R.id.codEscola);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.linha);
    }
}

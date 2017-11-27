package br.iesb.schoolsearch.schoolsearch.holders;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.iesb.schoolsearch.schoolsearch.R;
import br.iesb.schoolsearch.schoolsearch.activities.TelaPrincipalActivity;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    public RelativeLayout relativeLayout;
    public final TextView nome;
    public final TextView codEscola;

    public RecyclerViewHolder(View view,final Context context) {
        super(view);
        this.context = context;
        nome = (TextView) view.findViewById(R.id.nomeEscola);
        codEscola = (TextView) view.findViewById(R.id.codEscola);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.linha);
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {


            }
        });
    }

    public View.OnClickListener onClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Position ", Toast.LENGTH_SHORT).show();
            }
        };
    }


}

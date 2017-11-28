package br.iesb.schoolsearch.schoolsearch.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.iesb.schoolsearch.schoolsearch.R;
import br.iesb.schoolsearch.schoolsearch.models.EscolaModel;

public class ViewEscolaDialogFragment extends DialogFragment {

    private Button teste;
    private EscolaModel escola;

    public ViewEscolaDialogFragment() {
    }

    public static ViewEscolaDialogFragment newInstance(EscolaModel escola) {
        ViewEscolaDialogFragment frag = new ViewEscolaDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("escola",escola);
        //args.putString("title", escola.getNome());
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_escola, container);

        teste = (Button) v.findViewById(R.id.buttonMaps);

        teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"teste",Toast.LENGTH_SHORT);
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        escola = (EscolaModel) getArguments().getSerializable("escola");
        setStyle(DialogFragment.STYLE_NORMAL, getTheme());
    }

    @Override
    public int getTheme() {
        return R.style.full_screen_dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        EditText mEditText;
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        mEditText.setText(escola.getNome());
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        //getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }



}

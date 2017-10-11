package br.iesb.schoolsearch.schoolsearch.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import br.iesb.schoolsearch.schoolsearch.R;
import br.iesb.schoolsearch.schoolsearch.adapters.RecyclerViewAdapter;
import br.iesb.schoolsearch.schoolsearch.interfaces.RetrofitInterface;
import br.iesb.schoolsearch.schoolsearch.models.EscolaModel;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TelaPrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.ENDPOINT).addConverterFactory(GsonConverterFactory.create(gson)).build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<EscolaModel>> call = retrofitInterface.callListEscolas();

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(TelaPrincipalActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Buscando escolas");
        progressDoalog.show();

        call.enqueue(new retrofit2.Callback<List<EscolaModel>>(){
            @Override
            public void onResponse(Call<List<EscolaModel>> call, Response<List<EscolaModel>> response) {
                if(response.isSuccessful()){
                    List<EscolaModel> listaEscolas = new ArrayList<EscolaModel>();
                    for(EscolaModel estabelecimento : response.body()) {
                        listaEscolas.add(estabelecimento);
                    }
                    progressDoalog.dismiss();
                    recyclerView.setAdapter(new RecyclerViewAdapter(listaEscolas, TelaPrincipalActivity.this));
                }
                else{
                }
            }

            @Override
            public void onFailure(Call<List<EscolaModel>> call, Throwable t) {
            }
        });

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);


    }

    @Override
    public void onBackPressed() {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        fAuth.signOut();
        Intent intent = new Intent(TelaPrincipalActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        //super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tela_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            Intent intent = new Intent(TelaPrincipalActivity.this, EditUserActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

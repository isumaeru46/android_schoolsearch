package br.iesb.schoolsearch.schoolsearch.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
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
    private RecyclerViewAdapter mAdapter;

    private TextView textViewEmailUser;
    private TextView textViewNameUser;
    private ImageView imageViewPhotoUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav = (NavigationView)findViewById(R.id.nav_view);
        View navigationView = nav.getHeaderView(0);

        nav.setNavigationItemSelectedListener(this);

        textViewEmailUser = (AppCompatTextView) navigationView.findViewById(R.id.textViewEmailUser);
        textViewNameUser = (AppCompatTextView) navigationView.findViewById(R.id.textViewNameUser);
        imageViewPhotoUser = (ImageView) navigationView.findViewById(R.id.imageViewPhotoUser);
        Uri photoUrl = null;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            textViewEmailUser.setText(user.getEmail());
            textViewNameUser.setText(user.getDisplayName());

            photoUrl = user.getPhotoUrl();
        }

        if(photoUrl != null){
            new DownloadImage().execute(photoUrl.toString());
        }else{
            new DownloadImage().execute("https://crackberry.com/sites/crackberry.com/files/styles/large/public/topic_images/2013/ANDROID.png");
        }



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

                    mAdapter = new RecyclerViewAdapter(listaEscolas, TelaPrincipalActivity.this);
                    recyclerView.setAdapter(mAdapter);

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
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            Intent intent = new Intent(TelaPrincipalActivity.this, EditUserActivity.class);
            startActivity(intent);
        } /*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setImage(Drawable drawable)
    {
        imageViewPhotoUser.setImageDrawable(drawable);
    }

    public class DownloadImage extends AsyncTask<String, Integer, Drawable> {

        @Override
        protected Drawable doInBackground(String... arg0) {
            return downloadImage(arg0[0]);
        }

        protected void onPostExecute(Drawable image)
        {
            setImage(image);
        }

        private Drawable downloadImage(String _url)
        {
            URL url;
            InputStream in;
            BufferedInputStream buf;
            try {
                url = new URL(_url);
                in = url.openStream();
                buf = new BufferedInputStream(in);
                Bitmap bMap = BitmapFactory.decodeStream(buf);
                if (in != null) {
                    in.close();
                }
                if (buf != null) {
                    buf.close();
                }
                return new BitmapDrawable(bMap);
            } catch (Exception e) {
                Log.e("Error reading file", e.toString());
            }
            return null;
        }
    }


}

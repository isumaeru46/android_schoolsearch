package br.iesb.schoolsearch.schoolsearch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.net.URL;

import br.iesb.schoolsearch.schoolsearch.activities.CreateUserActivity;
import br.iesb.schoolsearch.schoolsearch.activities.MainActivity;
import br.iesb.schoolsearch.schoolsearch.activities.TelaPrincipalActivity;

public class EditUserActivity extends AppCompatActivity {

    private EditText txtTelefoneUpdateUser;
    private EditText txtNomeUpdateUser;
    private ImageView downloadedImg;
    private Button btnUpdate;
    private String downloadUrl = "http://crackberry.com/sites/crackberry.com/files/styles/large/public/topic_images/2013/ANDROID.png?itok=xhm7jaxS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        txtTelefoneUpdateUser = (EditText) findViewById(R.id.txtTelefoneUpdateUser);
        txtNomeUpdateUser = (EditText) findViewById(R.id.txtNomeUpdateUser);
        downloadedImg = (ImageView) findViewById(R.id.imageView);
        btnUpdate = (Button) findViewById(R.id.btnRegister);


        new ImageDownloader().execute(downloadUrl);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(txtTelefoneUpdateUser != null && !txtTelefoneUpdateUser.getText().toString().isEmpty()
                        && txtNomeUpdateUser != null && !txtNomeUpdateUser.getText().toString().isEmpty()){

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(txtNomeUpdateUser.toString())
                            .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(EditUserActivity.this, TelaPrincipalActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });

                }else{
                    Toast.makeText(EditUserActivity.this, "Todos campos obrigatórios!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... param) {
            try {
                URL imageURL = new URL(param[0]);
                return BitmapFactory.decodeStream(imageURL.openStream());
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            Log.i("ASYNCTASK", "onPreExecute Called");
            //simpleWaitDialog = ProgressDialog.show(MainActivity.this, "Aguarde", "Baixando a imagem...");
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            Log.i("ASYNCTASK", "onPostExecute Called");
            downloadedImg.setImageBitmap(result);
            //simpleWaitDialog.dismiss();
            //sharedpreferences.edit().putBoolean("isDownloading", false);
        }

    }
}

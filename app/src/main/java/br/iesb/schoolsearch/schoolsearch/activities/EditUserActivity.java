package br.iesb.schoolsearch.schoolsearch.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import br.iesb.schoolsearch.schoolsearch.R;

public class EditUserActivity extends AppCompatActivity {

    private EditText txtTelefoneUpdateUser;
    private EditText txtNomeUpdateUser;
    private ImageView photoUpdateUser;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        txtTelefoneUpdateUser = (EditText) findViewById(R.id.txtTelefoneUpdateUser);
        txtNomeUpdateUser = (EditText) findViewById(R.id.txtNomeUpdateUser);
        photoUpdateUser = (ImageView) findViewById(R.id.imageViewUpdateUser);
        btnUpdate = (Button) findViewById(R.id.btnUpdateUser);
        Uri photoUrl = null;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            txtNomeUpdateUser.setText(user.getDisplayName(), TextView.BufferType.EDITABLE);
            String email = user.getEmail();
            String telefone = user.getPhoneNumber();
            photoUrl = user.getPhotoUrl();
        }

        if(photoUrl != null){
            new DownloadImage().execute(photoUrl.toString());
        }else{
            new DownloadImage().execute("https://crackberry.com/sites/crackberry.com/files/styles/large/public/topic_images/2013/ANDROID.png");
        }

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setImage(Drawable drawable)
    {
        photoUpdateUser.setImageDrawable(drawable);
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

package br.iesb.schoolsearch.schoolsearch.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.util.Calendar;

import br.iesb.schoolsearch.schoolsearch.R;
import br.iesb.schoolsearch.schoolsearch.models.EscolaModel;

public class ShowPhotosActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 111;
    EscolaModel escola;
    ImageView mImageLabel;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);

        mImageLabel = (ImageView) findViewById(R.id.photoTeste);
        button = (Button) findViewById(R.id.take_photo);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

        if(getIntent() != null){
            escola = (EscolaModel) getIntent().getSerializableExtra("escola");
        }

        /*StorageReference storageRef = storage.getReference().child("escolas").child(escola.getCodEscola()).child("imageUrl");
        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReferenceFromUrl("gs://<your-bucket-name>");

// Create a reference to "file"
        StorageReference mountainsRef = storageRef.child("file.jpg");

        Glide.with(this *//* context *//*)
                .using(new FirebaseImageLoader())
                .load(storageRef)
                .into(mImageLabel);*/

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageLabel.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }

    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("escolas")
                .child(escola.getCodEscola())
                .child(String.valueOf(Calendar.getInstance().getTimeInMillis()))
                .child("imageUrl");
        ref.setValue(imageEncoded);
    }
}

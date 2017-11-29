package br.iesb.schoolsearch.schoolsearch.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.iesb.schoolsearch.schoolsearch.R;
import br.iesb.schoolsearch.schoolsearch.models.EscolaModel;

public class ShowPhotosActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 111;
    EscolaModel escola;
    ImageView mImageLabel;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private Button button;
    private ListView listVierw;
    List<Bitmap> listaImagens = null;
    private ArrayAdapter<Bitmap> adapter;
    private String imagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);

        mImageLabel = (ImageView) findViewById(R.id.photoTeste);
        button = (Button) findViewById(R.id.take_photo);
        listVierw = (ListView) findViewById(R.id.list_images);

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

//        adapter = new ImageAdapter();
        adapter = new ImageAdapter(this, R.layout.layout_foto);
        listVierw.setAdapter(adapter);




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
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }

    public List<Bitmap> getListaImagens() {
        if(listaImagens == null){
            listaImagens = new ArrayList<>();
        }
        return listaImagens;
    }

    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        getListaImagens().add(bitmap);
        adapter.add(bitmap);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("escolas")
                .child(escola.getCodEscola())
                .child(String.valueOf(Calendar.getInstance().getTimeInMillis()))
                .child("imageUrl");
        ref.setValue(imageEncoded);
        adapter.notifyDataSetChanged();
    }

    class ImageAdapter extends ArrayAdapter<Bitmap>{
        private ViewHolder holder;

        public ImageAdapter(@NonNull Context context, int resource) {
            super(context, resource);
        }

        private class ViewHolder{
            private ImageView image;
        }

        @Override
        public int getCount() {
            return getListaImagens().size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Nullable
        @Override
        public Bitmap getItem(int position) {
            try{
                return getListaImagens().get(position);
            } catch (Exception e){
                return null;
            }
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.layout_foto, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image_view);
            holder.image.setImageBitmap(getItem(position));
            convertView.setTag(holder);
            return convertView;
        }
    }


}

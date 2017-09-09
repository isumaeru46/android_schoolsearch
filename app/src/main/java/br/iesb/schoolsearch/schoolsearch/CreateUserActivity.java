package br.iesb.schoolsearch.schoolsearch;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateUserActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnRegister;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);


        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        mAuth = FirebaseAuth.getInstance();

        btnRegister = (Button) findViewById(R.id.btnLogin);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())
                        .addOnCompleteListener(CreateUserActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(CreateUserActivity.this, "Erro ao cadastrar, tente novamente!", Toast.LENGTH_SHORT).show();
                                }else{
                                    FirebaseUser user = task.getResult().getUser();
                                    if (user != null) {
                                        Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                            }
                        });
            }
        });

    }
}

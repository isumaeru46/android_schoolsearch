package br.iesb.schoolsearch.schoolsearch.activities;

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

import br.iesb.schoolsearch.schoolsearch.R;

public class CreateUserActivity extends AppCompatActivity {

    private EditText txtEmailCreateUser;
    private EditText txtPasswordCreateUser;
    private EditText txtPasswordConfirmCreateUser;
    private Button btnRegister;
    private Button btnBackToLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        txtEmailCreateUser = (EditText) findViewById(R.id.txtEmailCreateUser);
        txtPasswordCreateUser = (EditText) findViewById(R.id.txtPasswordCreateUser);
        txtPasswordConfirmCreateUser = (EditText) findViewById(R.id.txtPasswordConfirmCreateUser);

        mAuth = FirebaseAuth.getInstance();

        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnBackToLogin = (Button) findViewById(R.id.btnBackToLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(txtEmailCreateUser != null && !txtEmailCreateUser.getText().toString().isEmpty()
                        && txtPasswordCreateUser != null && !txtPasswordCreateUser.getText().toString().isEmpty()
                        && txtPasswordConfirmCreateUser != null && !txtPasswordConfirmCreateUser.getText().toString().isEmpty()){

                    if(txtPasswordCreateUser.getText().toString().equals(txtPasswordConfirmCreateUser.getText().toString())){
                        mAuth.createUserWithEmailAndPassword(txtEmailCreateUser.getText().toString(), txtPasswordCreateUser.getText().toString())
                                .addOnCompleteListener(CreateUserActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(CreateUserActivity.this, "Erro ao cadastrar, tente novamente!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            FirebaseUser user = task.getResult().getUser();
                                            if (user != null) {
                                                Intent intent = new Intent(CreateUserActivity.this, TelaPrincipalActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }

                                    }
                                });
                    }else{
                        Toast.makeText(CreateUserActivity.this, "Senha e confirmação tem que ser iguais!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CreateUserActivity.this, "Todos campos obrigatórios!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CreateUserActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

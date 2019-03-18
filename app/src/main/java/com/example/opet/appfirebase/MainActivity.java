package com.example.opet.appfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editNome;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    void updateUI(FirebaseUser currentUser){
        if(currentUser != null){
            Toast.makeText(this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Usuário não logado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void  signIn(View v) {
        editNome = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editSenha);

        String login = editNome.getText().toString();
        String senha = editSenha.getText().toString();
                mAuth.signInWithEmailAndPassword(login,senha).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    updateUI(mAuth.getCurrentUser());
                                }else{
                                    Toast.makeText(MainActivity.
                                            this,"úsuario não encontrado ",Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
    }
}

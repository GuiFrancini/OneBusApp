package com.example.onebus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        databaseHelper = new DatabaseHelper(this); //instancia o bd para validar o user

        btnLogin.setOnClickListener(v -> {
            String user = edtUsername.getText().toString();
            String pass = edtPassword.getText().toString();

            String role = databaseHelper.validateUser(user, pass); //verifica se os user existem no bd
            if (role == null) {
                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent;
                switch (role) { //redireciona com base no papel
                    case "admin":
                        intent = new Intent(this, Admin.class);
                        break;
                    case "gestor":
                        intent = new Intent(this, Gestor.class);
                        break;
                    case "funcionario":
                        intent = new Intent(this, Funcionario.class);
                        break;
                    default:
                        Toast.makeText(this, "Fu não reconhecida", Toast.LENGTH_SHORT).show();
                        return;
                }
                intent.putExtra("usuario", user);
                startActivity(intent);
                finish();
            }
        });
    }
}

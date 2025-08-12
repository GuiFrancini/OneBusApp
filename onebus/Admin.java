package com.example.onebus;

import android.content.Intent; // <- IMPORTANTE! intent
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {

    DatabaseHelper DatabaseHelper;

    EditText edtNovoUsuario, edtSenhaUsuario, edtPapelUsuario;
    EditText edtNumeroLinha, edtNomeLinha;
    EditText edtModeloOnibus, edtChassiOnibus, edtPrefixoOnibus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        DatabaseHelper = new DatabaseHelper(this);

        // Cadastro de Usuários
        edtNovoUsuario = findViewById(R.id.edtNovoUsuario);
        edtSenhaUsuario = findViewById(R.id.edtSenhaUsuario);
        edtPapelUsuario = findViewById(R.id.edtPapelUsuario);
        Button btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);

        btnCadastrarUsuario.setOnClickListener(v -> {
            String u = edtNovoUsuario.getText().toString(); //pegao s dados inseridos e cadastra no bd
            String s = edtSenhaUsuario.getText().toString();
            String p = edtPapelUsuario.getText().toString();

            boolean success = DatabaseHelper.inserirUsuario(u, s, p);
            Toast.makeText(this, success ? "Usuário cadastrado!" : "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
        });

        // Cadastro de Linhas
        edtNumeroLinha = findViewById(R.id.edtNumeroLinha);
        edtNomeLinha = findViewById(R.id.edtNomeLinha);
        Button btnCadastrarLinha = findViewById(R.id.btnCadastrarLinha);

        btnCadastrarLinha.setOnClickListener(v -> {
            String num = edtNumeroLinha.getText().toString();
            String nome = edtNomeLinha.getText().toString();

            boolean success = DatabaseHelper.inserirLinha(num, nome);
            Toast.makeText(this, success ? "Linha cadastrada!" : "Erro ao cadastrar linha", Toast.LENGTH_SHORT).show();
        });//chama o metodo inseririnha para cadastrar no bd

        // Cadasttro de Ônibus
        edtModeloOnibus = findViewById(R.id.edtModeloOnibus);
        edtChassiOnibus = findViewById(R.id.edtChassiOnibus);
        edtPrefixoOnibus = findViewById(R.id.edtPrefixoOnibus);
        Button btnCadastrarOnibus = findViewById(R.id.btnCadastrarOnibus);

        btnCadastrarOnibus.setOnClickListener(v -> {
            String modelo = edtModeloOnibus.getText().toString();
            String chassi = edtChassiOnibus.getText().toString();
            String prefixo = edtPrefixoOnibus.getText().toString();

            boolean success = DatabaseHelper.inserirOnibus(modelo, chassi, prefixo);
            Toast.makeText(this, success ? "Ônibus cadastrado!" : "Erro ao cadastrar ônibus", Toast.LENGTH_SHORT).show();
        });// metodos

        // Voltar ao Login
        Button btnVoltarLogin = findViewById(R.id.btnVoltarLogin);
        btnVoltarLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Admin.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); //FLAG remove a tela da pilha de atividades
        });
    }
}

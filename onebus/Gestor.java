package com.example.onebus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Gestor extends AppCompatActivity {

    DatabaseHelper DatabaseHelper;
    TextView txtLinhas, txtOnibus;
    EditText edtResponsavel, edtLinha, edtSentido, edtHorario, edtPrefixo;
    Button btnCadastrarOperacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor);

        DatabaseHelper = new DatabaseHelper(this);

        txtLinhas = findViewById(R.id.txtLinhas);
        txtOnibus = findViewById(R.id.txtOnibus);

        edtResponsavel = findViewById(R.id.edtResponsavel);
        edtLinha = findViewById(R.id.edtLinha);
        edtSentido = findViewById(R.id.edtSentido);
        edtHorario = findViewById(R.id.edtHorario);
        edtPrefixo = findViewById(R.id.edtPrefixo);
        btnCadastrarOperacao = findViewById(R.id.btnCadastrarOperacao);

        carregarLinhas();
        carregarOnibus();

        btnCadastrarOperacao.setOnClickListener(v -> {
            String responsavel = edtResponsavel.getText().toString();
            String linha = edtLinha.getText().toString();
            String sentido = edtSentido.getText().toString();
            String horario = edtHorario.getText().toString();
            String prefixo = edtPrefixo.getText().toString();

            boolean sucesso = DatabaseHelper.inserirOperacao(responsavel, linha, sentido, horario, prefixo);
            Toast.makeText(this, sucesso ? "Operação cadastrada!" : "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        });

        // Botão voltar ao login
        Button btnVoltarLogin = findViewById(R.id.btnVoltarLogin);
        btnVoltarLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Gestor.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }


    //exibir as linhas
    private void carregarLinhas() {
        List<String> linhas = DatabaseHelper.getTodasLinhas();
        StringBuilder builder = new StringBuilder();
        for (String linha : linhas) builder.append(linha).append("\n");
        txtLinhas.setText(builder.toString());
    }

    //exibir
    private void carregarOnibus() {
        List<String> onibus = DatabaseHelper.getTodosOnibus();
        StringBuilder builder = new StringBuilder();
        for (String o : onibus) builder.append(o).append("\n");
        txtOnibus.setText(builder.toString());
    }
}

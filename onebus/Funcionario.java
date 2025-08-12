package com.example.onebus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Funcionario extends AppCompatActivity {

    DatabaseHelper dbHelper;
    TextView txtOperacoes;
    String nomeUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        dbHelper = new DatabaseHelper(this); //instancia o bd
        txtOperacoes = findViewById(R.id.txtOperacoes); //liga a label ao codigo

        Intent i = getIntent();
        nomeUsuarioLogado = i.getStringExtra("usuario"); // recebido da tela de login

        carregarMinhasOperacoes();

        // Botão voltar ao login
        Button btnVoltarLogin = findViewById(R.id.btnVoltarLogin);
        btnVoltarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Funcionario.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Finaliza a activity atual para evitar voltar com o botão "voltar"
            }
        });
    }

    private void carregarMinhasOperacoes() {
        List<String> operacoes = dbHelper.getOperacoesPorResponsavel(nomeUsuarioLogado); ///retorna um array representando cada operaçao
        StringBuilder sb = new StringBuilder(); //cria um obj do tipo stringbuilder q substitui o + para concatenar
        if (operacoes.isEmpty()) { //se tiver vazio o array  mostra a msg
            sb.append("Nenhuma operação atribuída a você.");
        } else {
            for (String op : operacoes) {
                sb.append(op).append("\n\n"); //se tiver cada elemento e separado por quebra de linha
            }
        }
        txtOperacoes.setText(sb.toString());
    }
}

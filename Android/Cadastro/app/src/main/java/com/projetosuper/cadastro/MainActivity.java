package com.projetosuper.cadastro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Registro prim, reg, ult, aux;
    EditText edtnome, edtprof, edtidade;
    int numreg, pos;
    Button btncadastrar, btnlistar;

    void carregarTelaPrincipal(){
        setContentView(R.layout.activity_main);
        btncadastrar = findViewById(R.id.btn_cadastrarpessoa);
        btnlistar = findViewById(R.id.btn_listarpessoas);

        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarTelaCadastro();
            }
        });

        btnlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarListaPessoas();
            }
        });
    }

    void carregarTelaCadastro(){
        setContentView(R.layout.activity_cadastro);
        Button btncadastrar = findViewById(R.id.btncadastrar);
        final Button btncancelar = findViewById(R.id.btncancelar);

        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    reg = new Registro();
                    edtnome = findViewById(R.id.edtnome);
                    edtprof = findViewById(R.id.edtprofissao);
                    edtidade = findViewById(R.id.edtidade);
                    reg.nome = edtnome.getText().toString();
                    reg.profissao = edtprof.getText().toString();
                    reg.idade = edtidade.getText().toString();

                    if(prim == null)
                        prim = reg;
                    reg.Ant = ult;
                    if(ult == null)
                        ult = reg;
                    else{
                        ult.Prox = reg;
                        ult = reg;
                    }
                    numreg++;
                    showMessage("Cadastro efetuado com sucesso","Aviso");
                    carregarTelaPrincipal();
                }
                catch(Exception e){
                    showMessage("Erro ao cadastrar!","Erro");
                }

                btncancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        carregarTelaPrincipal();
                    }
                });
            }
        });
    }
    void carregarListaPessoas(){
        if(numreg==0){
            showMessage("Nenhum registro cadastrado","Aviso");
            carregarTelaPrincipal();
            return;
        }
        setContentView(R.layout.listacadastrados);
        pos = 1;
        aux = prim;

        Button btnmenu = findViewById(R.id.btnmenu);
        Button btnavancar = findViewById(R.id.btnavancar);
        Button btnvoltar = findViewById(R.id.btnvoltar);

        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos == 1)
                    return;
                pos--;
                aux = aux.Ant;

                TextView txtnome = findViewById(R.id.txtnome);
                TextView txtprofissao = findViewById(R.id.txtprofissao);
                TextView txtidade = findViewById(R.id.txtidade);

                txtnome.setText(aux.nome);
                txtprofissao.setText(aux.profissao);
                txtidade.setText(aux.idade);
            }
        });

        btnavancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos == numreg)
                    return;
                pos++;
                aux = aux.Prox;

                TextView txtnome = findViewById(R.id.txtnome);
                TextView txtprofissao = findViewById(R.id.txtprofissao);
                TextView txtidade = findViewById(R.id.txtidade);

                txtnome.setText(aux.nome);
                txtprofissao.setText(aux.profissao);
                txtidade.setText(aux.idade);
            }
        });

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarTelaPrincipal();
            }
        });
    }
    public void showMessage(String caption, String title){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle(title);
        dialogo.setMessage(caption);
        dialogo.setNeutralButton("Ok",null);
        dialogo.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numreg = 0;
        prim = ult = null;
        carregarTelaPrincipal();
    }
}
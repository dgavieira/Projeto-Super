package com.projetosuper.exemplorest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txtid) EditText txtid;
    @BindView(R.id.txtNome) EditText txtnome;
    @BindView(R.id.txtCPF) EditText txtcpf;

    @BindView(R.id.btnbuscar) Button btnbuscar;
    @BindView(R.id.btnexcluir) Button btnexcluir;
    @BindView(R.id.btnsalvar) Button btnsalvar;

    @BindView(R.id.btnnovo) FloatingActionButton btnnovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnnovo)
    public void btnnovoOnClick(){
        txtid.setText("0");
        txtnome.setText("");
        txtcpf.setText("");
    }

    @OnClick(R.id.btnbuscar)
    public void btnBuscarOnClick(){
        int id = Integer.parseInt(txtid.getText().toString());
        //procura o cliente pelo id
        if(true){ //caso ele encontre preenche
            txtnome.setText("Nome do fulano");
            txtcpf.setText("123456789");
        }else
            Toast.makeText(this, "Nenhum cliente encontrado com o id" + id, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnexcluir)
    public void btnExcluirOnClick(){
        final int id = Integer.parseInt(txtid.getText().toString());

        //cria o dialogo de confirmacao
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Você tem certeza que deseja excluir esse cliente?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(true){
                            Toast.makeText(MainActivity.this, "Cliente excluído com sucesso!", Toast.LENGTH_SHORT).show();
                            txtid.setText("0");
                            txtnome.setText("");
                            txtcpf.setText("");
                        }else{
                            Toast.makeText(MainActivity.this, "Nenhum cliente encontrado com esse id", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        builder.create().show();
    }

    @OnClick(R.id.btnsalvar)
    public void btnsalvarOnClick(){
        String idStr = txtid.getText().toString();
        final int id = idStr.equals("") ? 0 : Integer.parseInt(idStr);
        boolean sucesso = true;
        if(id > 0){//edicao
            //PATCH na API enviando o id junto para editar
        }else{

        } //novo cadastro
        //POST na API sem enviar o id que será gerado no banco
        if(!sucesso){
            if(id > 0)
                Toast.makeText(this, "Não foi encontrado nenhum cliente com esse id para atualizar", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Não foi possível salvar esse cliente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Cliente salvo com sucesso", Toast.LENGTH_SHORT).show();
        }
    }
}
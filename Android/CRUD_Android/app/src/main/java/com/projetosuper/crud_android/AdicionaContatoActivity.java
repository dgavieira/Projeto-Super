package com.projetosuper.crud_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class AdicionaContatoActivity extends AppCompatActivity {
    EditText edt_nome, edt_empresa, edt_fone, edt_email;
    Button btn_cadastrar, btn_cancelar;

    Contato contatoEditado = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_contato);
        //instanciando elementos da tela
        edt_nome = findViewById(R.id.activity_adiciona_contato_edt_nome);
        edt_empresa = findViewById(R.id.activity_adiciona_contato_edt_empresa);
        edt_fone = findViewById(R.id.activity_adiciona_contato_edt_telefone);
        edt_email = findViewById(R.id.activity_adiciona_contato_edt_email);
        btn_cadastrar = findViewById(R.id.activity_adiciona_contato_btn_cadastrar);
        btn_cancelar = findViewById(R.id.activity_adiciona_contato_btn_cancelar);


        //chamando evento de click no botao
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pegando os dados
                String nome = edt_nome.getText().toString();
                String empresa = edt_empresa.getText().toString();
                String telefone = edt_fone.getText().toString();
                String email = edt_email.getText().toString();

                //salvando os dados
                ContatoDAO dao = new ContatoDAO(getBaseContext());
                boolean sucesso;
               // verifica se o contato foi editado ou não antes de salvar
                if(contatoEditado != null)
                    sucesso = dao.salvar(contatoEditado.getId(), nome, empresa, telefone, email);
                else
                sucesso = dao.salvar(nome, empresa, telefone, email);

                if(sucesso){
                    //limpa os campos
                    edt_nome.setText("");
                    edt_empresa.setText("");
                    edt_fone.setText("");
                    edt_email.setText("");

                    Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Intent go_to_main = new Intent(AdicionaContatoActivity.this, MainActivity.class);
                    startActivity(go_to_main);
                    finish();

                }else {
                    Snackbar.make(view, "Erro ao salvar!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_main = new Intent(AdicionaContatoActivity.this, MainActivity.class);
                startActivity(go_to_main);
                finish();
            }
        });
    }
}
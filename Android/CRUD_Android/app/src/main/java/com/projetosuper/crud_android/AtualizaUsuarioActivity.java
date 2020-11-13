package com.projetosuper.crud_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

public class AtualizaUsuarioActivity extends AppCompatActivity {
    EditText edt_atualiza_nome, edt_atualiza_empresa, edt_atualiza_fone, edt_atualiza_email;
    Button btn_atualiza, btn_atualiza_cancela;


    Contato contatoEditado = null;

    private int getIndex(Spinner spinner, String myString){
        int index = 0;
        for (int i = 0; i<spinner.getCount();i++){
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza_usuario);

        edt_atualiza_nome = findViewById(R.id.edt_atualiza_nome);
        edt_atualiza_empresa = findViewById(R.id.edt_atualiza_empresa);
        edt_atualiza_fone = findViewById(R.id.edt_atualiza_telefone);
        edt_atualiza_email = findViewById(R.id.edt_atualiza_email);
        btn_atualiza = findViewById(R.id.btn_atualiza_cadastrar);
        btn_atualiza_cancela = findViewById(R.id.btn_atualiza_cancelar);

        //verifica se começou agora ou veio de uma edição
        Intent intent = getIntent();
        if(intent.hasExtra("cliente")){
            contatoEditado = (Contato) intent.getSerializableExtra("contato");
            edt_atualiza_nome.setText(contatoEditado.getNome());
            edt_atualiza_empresa.setText(contatoEditado.getEmpresa());
            edt_atualiza_fone.setText(contatoEditado.getTelefone());
            edt_atualiza_email.setText(contatoEditado.getEmail());
        }

        btn_atualiza_cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_main = new Intent(AtualizaUsuarioActivity.this, MainActivity.class);
                startActivity(go_to_main);
                finish();
            }
        });

        btn_atualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pegando os dados
                String nome = edt_atualiza_nome.getText().toString();
                String empresa = edt_atualiza_empresa.getText().toString();
                String telefone = edt_atualiza_fone.getText().toString();
                String email = edt_atualiza_email.getText().toString();

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
                    edt_atualiza_nome.setText("");
                    edt_atualiza_empresa.setText("");
                    edt_atualiza_fone.setText("");
                    edt_atualiza_email.setText("");

                    Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Intent go_to_main = new Intent(AtualizaUsuarioActivity.this, MainActivity.class);
                    startActivity(go_to_main);
                    finish();

                }else {
                    Snackbar.make(view, "Erro ao salvar!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }
}
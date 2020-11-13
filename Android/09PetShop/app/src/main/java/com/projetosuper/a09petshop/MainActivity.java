package com.projetosuper.a09petshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Registro pri,reg,ult,aux;
    RegistroPets prip,regp,ultp,auxp;
    EditText ednome,edprof,edidade;
    EditText ednomepet, edespeciepet, edidadepet;
    int numreg,pos;
    int numregp,posp;

    //tela principal
    void carregaTelaPrincipal () {
        setContentView(R.layout.activity_main);
        //botoes referentes a cadastro de pessoas
        Button btcadpess = (Button) findViewById(R.id.activity_main_btn_cadastrar_pessoa);
        Button btlistapess = (Button) findViewById(R.id.activity_main_btn_listar_pessoas_cadastradas);

        //botoes referentes a cadastro de pets
        Button btncadastrarpet = (Button) findViewById(R.id.activity_main_btn_cadastrar_pets);
        Button btnlistarpet = (Button) findViewById(R.id.activity_main_btn_listar_pets_cadastrados);

        //evento para click de cadastro de pessoas
        btcadpess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaTelaCadastro();
            }
        });

        //evento para click de listar pessoas
        btlistapess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregarListaPessoas();
            }
        });

        //evento para click de cadastro de PETS
        btncadastrarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaTelaCadastroPet();
            }
        });

        //evento para click de listar PETS
        btnlistarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaTelaListarPet();
            }
        });
    }

    //metodo que faz o carregamento da tela de cadastro de pessoas
    void carregaTelaCadastro() {
        setContentView(R.layout.cadastro);
        Button btcadastrar = (Button)findViewById(R.id.cadastro_btn_cadastrar_pessoa);
        Button btcancelar = (Button)findViewById(R.id.cadastro_btn_cancelar);

        btcadastrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                try {
                    reg = new Registro();
                    ednome = (EditText)findViewById(R.id.cadastro_edtnome);
                    edprof = (EditText)findViewById(R.id.cadastro_edtprof);
                    edidade = (EditText)findViewById(R.id.cadastro_edtidade);
                    reg.nome = ednome.getText().toString();
                    reg.profissao = edprof.getText().toString();
                    reg.idade = edidade.getText().toString();
                    if(pri==null)
                        pri=reg;
                    reg.Ant = ult;
                    if(ult==null)
                        ult=reg;
                    else {
                        ult.Prox = reg;
                        ult=reg;
                    }
                    numreg++;
                    showMessage("Cadastro efetuado com sucesso", "Aviso");
                    carregaTelaPrincipal();
                }
                catch(Exception e) {
                    showMessage("Erro ao cadastrar", "Erro");
                }
            }
        });

        btcancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                carregaTelaPrincipal();
            }
        });
    }

    //metodo que faz o carregamento da tela de listagem de pessoas cadastradas
    void carregarListaPessoas() {
        if(numreg==0) {
            showMessage("Nenhum registro cadastrado", "Aviso");
            carregaTelaPrincipal();
            return;
        }
        setContentView(R.layout.listacadastrados);
        pos=1;
        aux=pri;
        TextView txtnome = (TextView)findViewById(R.id.listacadastrados_txt_nome);
        TextView txtidade = (TextView)findViewById(R.id.listacadastrados_txt_idade);
        TextView txtprofissao = (TextView)findViewById(R.id.listacadastrados_txt_profissao);

        Button btmenu = (Button) findViewById(R.id.listacadastrados_btn_menu_principal);
        Button btavancar = (Button) findViewById(R.id.listacadastrados_btn_avancar);
        Button btvoltar = (Button) findViewById(R.id.listacadastrados_btn_voltar);
        Button btnalterar = (Button) findViewById(R.id.listacadastrados_btn_alterar);

        txtnome.setText(aux.nome);
        txtidade.setText(aux.idade);
        txtprofissao.setText(aux.profissao);

        btmenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                carregaTelaPrincipal();
            }
        });

        btvoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (pos == 1)
                    return;
                pos--;
                aux = aux.Ant;
                TextView txtnome = (TextView) findViewById(R.id.listacadastrados_txt_nome);
                TextView txtidade = (TextView) findViewById(R.id.listacadastrados_txt_idade);
                TextView txtprofissao = (TextView) findViewById(R.id.listacadastrados_txt_profissao);
                txtnome.setText(aux.nome);
                txtidade.setText(aux.idade);
                txtprofissao.setText(aux.profissao);
            }
        });

        btavancar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                if(pos==numreg)
                    return;
                pos++;
                aux=aux.Prox;
                TextView txtnome = (TextView) findViewById(R.id.listacadastrados_txt_nome);
                TextView txtidade = (TextView) findViewById(R.id.listacadastrados_txt_idade);
                TextView txtprofissao = (TextView) findViewById(R.id.listacadastrados_txt_profissao);
                txtnome.setText(aux.nome);
                txtidade.setText(aux.idade);
                txtprofissao.setText(aux.profissao);
            }
        });

        btnalterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.atualiza_cadastro_pessoas);

                EditText nome_alterado = (EditText) findViewById(R.id.atualiza_cadastro_pessoa_edt_nome);
                EditText profissao_alterado = (EditText) findViewById(R.id.atualiza_cadastro_pessoa_edt_profissao);
                EditText idade_alterado = (EditText) findViewById(R.id.atualiza_cadastro_pessoa_edt_idade);
                Button altera_pessoa = (Button) findViewById(R.id.atualiza_cadastro_pessoas_btn_alterar);

                nome_alterado.setText(aux.nome);
                profissao_alterado.setText(aux.profissao);
                idade_alterado.setText(aux.idade);

                altera_pessoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            reg = new Registro();
                            ednome = (EditText)findViewById(R.id.cadastro_edtnome);
                            edprof = (EditText)findViewById(R.id.cadastro_edtprof);
                            edidade = (EditText)findViewById(R.id.cadastro_edtidade);
                            reg.nome = ednome.getText().toString();
                            reg.profissao = edprof.getText().toString();
                            reg.idade = edidade.getText().toString();
                            if(pri==null)
                                pri=reg;
                            reg.Ant = ult;
                            if(ult==null)
                                ult=reg;
                            else {
                                ult.Prox = reg;
                                ult=reg;
                            }
                            numreg++;
                            showMessage("Cadastro efetuado com sucesso", "Aviso");
                            carregaTelaPrincipal();
                        }
                        catch(Exception e) {
                            showMessage("Erro ao cadastrar", "Erro");
                        }
                    }
                });
            }
        });
    }

    //metodo que faz o carregamento da tela de cadastro de PETS
    void carregaTelaCadastroPet(){
        setContentView(R.layout.cadastro_pets);
        Button btncadastrarpets = (Button) findViewById(R.id.cadastro_pet_btn_cadastrar);
        Button btncancelarpets = (Button) findViewById(R.id.cadastro_pet_btn_cancelar);

        btncadastrarpets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    regp = new RegistroPets();
                    ednomepet = (EditText) findViewById(R.id.cadastro_pets_edt_nome);
                    edespeciepet = (EditText) findViewById(R.id.cadastro_pets_edt_especie);
                    edidadepet = (EditText) findViewById(R.id.cadastro_pets_edt_idade);

                    regp.Pet_Nome = ednomepet.getText().toString();
                    regp.Pet_Especie = edespeciepet.getText().toString();
                    regp.Pet_Idade = edidadepet.getText().toString();

                    if(prip==null)
                        prip=regp;
                    regp.Ant=ultp;
                    if(ultp==null)
                        ultp=regp;
                    else{
                        ultp.Prox = regp;
                        ultp = regp;
                    }
                    numregp++;
                    showMessage("Cadastro Efetuado com Sucesso","Aviso");
                    carregaTelaPrincipal();
                }
                catch(Exception e){
                    showMessage("Erro ao cadastrar","Erro");
                }
            }
        });
        btncancelarpets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaPrincipal();
            }
        });
    }

    //metodo que faz o carregamento da tela de listar Pets
    void carregaTelaListarPet(){
        if(numregp==0){
            showMessage("Nenhum registro cadastrado","Aviso");
            carregaTelaPrincipal();
            return;
        }
        setContentView(R.layout.lista_cadastro_pets);
        posp=1;
        auxp=prip;
        TextView txtnomepet = (TextView) findViewById(R.id.lista_cadastro_pets_txt_nome);
        TextView txtespeciepet = (TextView) findViewById(R.id.lista_cadastro_pets_txt_especie);
        TextView txtidadepet = (TextView) findViewById(R.id.lista_cadastro_pets_txt_idade);

        Button btnmenupet = (Button) findViewById(R.id.lista_cadastro_pets_btn_menu_principal);
        Button btnavancarpet = (Button) findViewById(R.id.lista_cadastro_pets_btn_avancar);
        Button btnvoltarpet = (Button) findViewById(R.id.lista_cadastro_pets_btn_voltar);
        Button btnatualizarpet = (Button) findViewById(R.id.lista_cadastro_pets_btn_alterar);

        txtnomepet.setText(auxp.Pet_Nome);
        txtespeciepet.setText(auxp.Pet_Especie);
        txtidadepet.setText(auxp.Pet_Idade);

        btnmenupet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaPrincipal();
            }
        });

        btnvoltarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posp==1)
                    return;
                posp--;
                auxp = auxp.Ant;

                TextView txtnomepet = (TextView) findViewById(R.id.lista_cadastro_pets_txt_nome);
                TextView txtespeciepet = (TextView) findViewById(R.id.lista_cadastro_pets_txt_especie);
                TextView txtidadepet = (TextView) findViewById(R.id.lista_cadastro_pets_txt_idade);

                txtnomepet.setText(auxp.Pet_Nome);
                txtespeciepet.setText(auxp.Pet_Especie);
                txtidadepet.setText(auxp.Pet_Idade);
            }
        });

        btnavancarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posp==numregp)
                    return;
                posp++;
                auxp=auxp.Prox;

                TextView txtnomepet = (TextView) findViewById(R.id.lista_cadastro_pets_txt_nome);
                TextView txtespeciepet = (TextView) findViewById(R.id.lista_cadastro_pets_txt_especie);
                TextView txtidadepet = (TextView) findViewById(R.id.lista_cadastro_pets_txt_idade);

                txtnomepet.setText(auxp.Pet_Nome);
                txtespeciepet.setText(auxp.Pet_Especie);
                txtidadepet.setText(auxp.Pet_Idade);
            }
        });

        btnatualizarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.atualiza_cadastro_pets);

                EditText nome_pet_alterado = (EditText) findViewById(R.id.atualiza_cadastro_pets_edt_nome);
                EditText especie_pet_alterado = (EditText) findViewById(R.id.atualiza_cadastro_pets_edt_especie);
                EditText idade_pet_alterado = (EditText) findViewById(R.id.atualiza_cadastro_pets_edt_idade);
                Button altera_pet = (Button) findViewById(R.id.atualiza_cadastro_pets_btn_alterar);

                nome_pet_alterado.setText(aux.nome);
                especie_pet_alterado.setText(aux.profissao);
                idade_pet_alterado.setText(aux.idade);

                altera_pet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            regp = new RegistroPets();
                            ednomepet = (EditText) findViewById(R.id.cadastro_pets_edt_nome);
                            edespeciepet = (EditText) findViewById(R.id.cadastro_pets_edt_especie);
                            edidadepet = (EditText) findViewById(R.id.cadastro_pets_edt_idade);

                            regp.Pet_Nome = ednomepet.getText().toString();
                            regp.Pet_Especie = edespeciepet.getText().toString();
                            regp.Pet_Idade = edidadepet.getText().toString();

                            if(prip==null)
                                prip=regp;
                            regp.Ant=ultp;
                            if(ultp==null)
                                ultp=regp;
                            else{
                                ultp.Prox = regp;
                                ultp = regp;
                            }
                            numregp++;
                            showMessage("Cadastro Efetuado com Sucesso","Aviso");
                            carregaTelaPrincipal();
                        }
                        catch(Exception e){
                            showMessage("Erro ao cadastrar","Erro");
                        }
                    }
                });
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numreg = 0;
        numregp = 0;
        pri=ult=null;
        prip = ultp = null;
        carregaTelaPrincipal();
    }
    public void showMessage(String Caption,String Title) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle(Title);
        dialogo.setMessage(Caption);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}

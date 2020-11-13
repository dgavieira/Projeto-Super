package com.projetosuper.appusaintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] listaNomes = {"Felpudo","Fofura","Lerdo","Bugado","Uruca",
            "Racing","IoS","Android","RealTime","Sound","3DS Max", "Games"};

    int[] listaIcones = {R.drawable.felpudo,R.drawable.fofura,R.drawable.lesmo,
            R.drawable.bugado,R.drawable.uruca,R.drawable.carrinho,
            R.drawable.ios,R.drawable.android,R.drawable.realidade_aumentada,
            R.drawable.sound_fx,R.drawable.max,R.drawable.games};

    String[] listaDescricoes = {"O protagonista dos nossos cursos de iOS e Android. Vive se metendo em altas aventuras para aprender a programar.",
            "É a namoradinha do Felpudo, a personagem que mostra que programação é sim coisa de meninas!",
            "Uma criatura asqueirosa que vive atrapalhando a vida do Felpudo e riando problemas no seu caminho.",
            "É o protagonista dos nossos cursos de iOS e Android. Vive se metendo em altas aventuras para aprender a programar.",
            "É a namoradinha do Felpudo, a personagem que mostra que programação é sim coisa de meninas!",
            "É a criatura asqueirosa que vive atrapalhando a vida do Felpudo e criando problemas no seu caminho.",
            "É o protagonista dos nossos cursos de iOS e Android. Vive se metendo em altas aventuras para aprender a programar.",
            "É a namoradinha do Felpudo, a personagem que mostra que programação é sim coisa de meninas!",
            "É a criatura asqueirosa que vive atrapalhando a vida do Felpudo e riando problemas no seu caminho.",
            "É o protagonista dos nossos cursos de iOS e Android. Vive se metendo em altas aventuras para aprender a programar.",
            "É a namoradinha do Felpudo, a personagem que mostra que programação é sim coisa de meninas!",
            "É a criatura asqueirosa que vive atrapalhando a vida do Felpudo e riando problemas no seu caminho."};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView minhaLista = findViewById(R.id.minhaLista);
        final MeuAdaptador meuAdaptador = new MeuAdaptador(getApplicationContext(),R.layout.minha_celula);

        int i = 0;
        for(String nome:listaNomes){
            DadosPersonagem dadosPersonagem = new DadosPersonagem(listaIcones[i], nome, listaDescricoes[i]);
            meuAdaptador.add(dadosPersonagem);
            i++;
        }

        minhaLista.setAdapter(meuAdaptador);

        minhaLista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DadosPersonagem dadosPersonagem;
                dadosPersonagem = (DadosPersonagem) meuAdaptador.getItem(i);

                Intent intent = new Intent(MainActivity.this, SegundaTela.class);
                intent.putExtra("nome",dadosPersonagem.getTitulo());
                intent.putExtra("descricao", dadosPersonagem.getDescricao());
                intent.putExtra("icone", dadosPersonagem.getIcone());
                startActivity(intent);

            }
        });
    }
}
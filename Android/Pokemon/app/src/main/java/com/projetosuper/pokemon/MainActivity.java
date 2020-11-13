package com.projetosuper.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] listaNomes = {"Bulbasaur","Charmander","Mewtwo","Pikachu","Squirtle"};

    int[] listaIcones = {R.drawable.bulbasaur,R.drawable.charmander,R.drawable.mewtwo,
    R.drawable.pikachu,R.drawable.skertlue};

    // int[] listaSons = {}

    String[] listaDescricoes = {
            "Type: Grass \t 60 HP \n Razor Leaf \t 30 \n Sleep Poison \n Flip a Coin, If heads, the Defending Pokémon is now Asleep and Poisoned.",
            "Type: Fire \t 70 HP \n Fire Fang \t 20 \n Your opponent's Active Pokémon is now Burned.",
            "Type: Psych \t 170 HP \n X Ball \t 20x \n Does 20 damage times the amount of Energy "+
                    "attached to this Pokémon and the Defending Pokémon \n" +
                    "Psydrive \t 120 \n Discard an Energy attached to this Polémon",
            "Type: Electro \t 60 HP \n Quick Attack \t 10+ \n Flip a coin. If heads, this attack does 10 more damage. \n" +
                    "Electro Ball \t 50.",
            "Type: Water \t 60 HP \n Shell Shield \n As long as this Pokémon is on your Bench, prevent all damage done to this Pokémon by attacks (both yours and your opponent's \n" +
                    "Water Splash \t 10+ \n Flip a Coin. If heads, this attack does 20 more damage."
    };

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
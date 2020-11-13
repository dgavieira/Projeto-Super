package meujokempo.titopetri.com.meujokempo;

import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView jogador1;
    ImageView jogador2;

    ImageButton botaoPedra;
    ImageButton botaoPapel;
    ImageButton botaoTesoura;
    ImageButton botaoSpock;
    ImageButton botaoLizard;

    Animation some;
    Animation aparece;

    TextView txt_score_1, txt_score_cpu;

    int jogada1 = 0;
    int jogada2 = 0;

    int pontuacao1 = 0;
    int pontuacao2 = 0;

    MediaPlayer mediaPlayer;
    MediaPlayer perdeu, ganhou, empatou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alex_play);
        perdeu = MediaPlayer.create(MainActivity.this,R.raw.perdeu);
        empatou = MediaPlayer.create(MainActivity.this, R.raw.empatou);
        ganhou = MediaPlayer.create(MainActivity.this,R.raw.ganhou);

        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);
        botaoPedra = findViewById(R.id.botaoPedra);
        botaoPapel = findViewById(R.id.botaoPapel);
        botaoTesoura = findViewById(R.id.botaoTesoura);
        botaoLizard = findViewById(R.id.botaoLizard);
        botaoSpock = findViewById(R.id.botaoSpock);

        txt_score_1 = findViewById(R.id.txt_score_1);
        txt_score_cpu = findViewById(R.id.txt_score_cpu);

        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);

        some.setDuration(1500);
        aparece.setDuration(250);

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.INVISIBLE);
                jogador2.startAnimation(aparece);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sorteiaJogadaInimigo();
                jogador2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                verificaJogada();
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void tocouBotao(View view){
        tocaSom();
        jogador1.setScaleX(-1);
        switch(view.getId()){
            case(R.id.botaoPedra):
                jogador1.setImageResource(R.drawable.pedra);
                jogada1 = 1;
                break;
            case(R.id.botaoPapel):
                jogador1.setImageResource(R.drawable.papel);
                jogada1 = 2;
                break;
            case(R.id.botaoTesoura):
                jogador1.setImageResource(R.drawable.tesoura);
                jogada1 = 3;
                break;
            case(R.id.botaoLizard):
                jogador1.setImageResource(R.drawable.lizard);
                jogada1 = 4;
                break;
            case(R.id.botaoSpock):
                jogador1.setImageResource(R.drawable.spock);
                jogada1 = 5;
                break;
        }
        jogador2.startAnimation(some);
        jogador2.setImageResource(R.drawable.interrogacao);

    }

    public void sorteiaJogadaInimigo(){
        Random r = new Random();
        int numRandom = r.nextInt(5);
        switch (numRandom){
            case 0:
                jogador2.setImageResource(R.drawable.pedra);
                jogada2 = 1;
                break;
            case 1:
                jogador2.setImageResource(R.drawable.papel);
                jogada2 = 2;
                break;
            case 2:
                jogador2.setImageResource(R.drawable.tesoura);
                jogada2 = 3;
                break;
            case 3:
                jogador2.setImageResource(R.drawable.lizard);
                jogada2 = 4;
                break;
            case 4:
                jogador2.setImageResource(R.drawable.spock);
                jogada2 = 5;
                break;
        }
    }

    public void verificaJogada(){
        if(jogada1==jogada2){
            Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show();
            tocaEmpatou();
        }
 
        if((jogada1==1&&jogada2==3)||(jogada1==2&&jogada2==1)||(jogada1==3&&jogada2==2)||
        (jogada1==1&&jogada2==4)||(jogada1==4&&jogada2==5)||(jogada1==5&&jogada2==3)||
        (jogada1==3&&jogada2==4)||(jogada1==4&&jogada2==2)||(jogada1==2&&jogada2==5)||
        (jogada1==5&&jogada2==1))
        {
            ponto_jogador();
            tocaGanhou();
        }

        if((jogada2==1&&jogada1==3)||(jogada2==2&&jogada1==1)||(jogada2==3&&jogada1==2)||
                (jogada2==1&&jogada1==4)||(jogada2==4&&jogada1==5)||(jogada2==5&&jogada1==3)||
                (jogada2==3&&jogada1==4)||(jogada2==4&&jogada1==2)||(jogada2==2&&jogada1==5)||
                (jogada2==5&&jogada1==1))
        {
            ponto_cpu();
            tocaPerdeu();
        }
    }

    public void tocaSom(){
        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }
    public void tocaGanhou(){
        if(ganhou != null){
            ganhou.start();
        }
    }
    public void tocaEmpatou(){
        if(empatou != null){
            empatou.start();
        }
    }
    public void tocaPerdeu(){
        if(perdeu != null){
            perdeu.start();
        }
    }

    public void ponto_jogador(){
        pontuacao1++;
        String pontuaco1_str = String.valueOf(pontuacao1);
        txt_score_1.setText(pontuaco1_str);
        Toast.makeText(this, "Ganhei!", Toast.LENGTH_SHORT).show();
    }
    public void ponto_cpu(){
        pontuacao2++;
        String pontuacao2_str = String.valueOf(pontuacao2);
        txt_score_cpu.setText(pontuacao2_str);
        Toast.makeText(this, "Perdi!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if(perdeu != null){
            perdeu.stop();
            perdeu.release();
            perdeu = null;
        }
        if(empatou != null){
            empatou.stop();
            empatou.release();
            empatou = null;
        }
        if(ganhou != null){
            ganhou.stop();
            ganhou.release();
            ganhou = null;
        }
        super.onDestroy();
    }


}

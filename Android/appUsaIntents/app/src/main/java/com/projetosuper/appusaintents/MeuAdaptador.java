package com.projetosuper.appusaintents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MeuAdaptador extends ArrayAdapter {

    public MeuAdaptador(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View minhaView;
        minhaView = convertView;
        ViewPersonagem viewPersonagem;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            minhaView = inflater.inflate(R.layout.minha_celula,parent, false);

            viewPersonagem = new ViewPersonagem();
            viewPersonagem.icone = (ImageView)minhaView.findViewById(R.id.meuIcone);
            viewPersonagem.titulo = (TextView) minhaView.findViewById(R.id.meuTitulo);
            viewPersonagem.descricao = (TextView) minhaView.findViewById(R.id.minhaDescricao);

            minhaView.setTag(viewPersonagem);
        }else{
            viewPersonagem = (ViewPersonagem)minhaView.getTag();
        }

        DadosPersonagem dadosPersonagem;
        dadosPersonagem = (DadosPersonagem)this.getItem(position);

        viewPersonagem.icone.setImageResource(dadosPersonagem.getIcone());
        viewPersonagem.titulo.setText(dadosPersonagem.getTitulo());
        viewPersonagem.descricao.setText(dadosPersonagem.getDescricao());

        //return super.getView(position, convertView, parent);
        return minhaView;
    }
}
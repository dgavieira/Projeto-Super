package com.projetosuper.animaisml;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AnimalAdapter extends ArrayAdapter {

    public AnimalAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View minhaView = convertView;
        final AnimalView animalView;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            minhaView = inflater.inflate(R.layout.celula,parent,false);

            animalView = new AnimalView();
            animalView.nome = minhaView.findViewById(R.id.celula_nome);
            animalView.categoria = minhaView.findViewById(R.id.celula_categoria);
            animalView.especie = minhaView.findViewById(R.id.celula_especie);
            animalView.idade = minhaView.findViewById(R.id.celula_idade);

            minhaView.setTag(animalView);;
        }else{
            animalView = (AnimalView) minhaView.getTag();
        }

        final Animal animal = (Animal) this.getItem(position);
        animalView.nome.setText(animal.getNome());
        animalView.categoria.setText(animal.getCategoria());
        animalView.especie.setText(animal.getEspecie());
        //animalView.idade.setText(animal.getIdade());

        return minhaView;
    }
}

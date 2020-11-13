package com.petshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.petshop.AtualizaAnimal;
import com.petshop.R;
import com.petshop.listener.RecyclerItemClickListener;
import com.petshop.listener.RecyclerItemLongClickListener;
import com.petshop.model.Animais;
import com.petshop.resource.CircleImageView;

import java.util.ArrayList;
import java.util.List;



public class AnimalViewAdapter extends RecyclerView.Adapter<AnimalViewAdapter.AnimalHolder> {

    private List<Animais> animaisList;
    Animais animais;
    Context context;
    View view;
    private RecyclerItemClickListener recyclerItemClickListener;
    private RecyclerItemLongClickListener recyclerItemLongClickListener;



    public AnimalViewAdapter(Context context){
        this.context = context;
        this.animaisList = new ArrayList<>();
    }

    private void add(Animais item) {
        animaisList.add(item);
        notifyItemInserted(animaisList.size());
    }

    public void addAll(List<Animais> animaisList) {
        for (Animais animais : animaisList) {
            add(animais);
        }
    }

    public void remove(Animais item) {
        int position = animaisList.indexOf(item);
        if (position > -1) {
            animaisList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Animais getItem(int position) {
        return animaisList.get(position);
    }


    public static class AnimalHolder extends RecyclerView.ViewHolder{

        public TextView textNome;
        public TextView textRaca;
        public TextView textCategoria;
        public CircleImageView fotoPet;

        public AnimalHolder(View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textNome);
            textRaca = itemView.findViewById(R.id.textRaca);
            textCategoria = itemView.findViewById(R.id.textCategoria);
            fotoPet = itemView.findViewById(R.id.icone);

        }
    }


    @NonNull
    @Override
    public AnimalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.item_lista_pet,parent,false);

        final AnimalHolder animalHolder = new AnimalHolder(view);

        animalHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = animalHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerItemClickListener != null) {
                        recyclerItemClickListener.onItemClick(adapterPos, animalHolder.itemView);
                    }
                }

            }
        });
        animalHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int adapterPos = animalHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerItemClickListener != null) {
                        recyclerItemLongClickListener.onItemLongClick(adapterPos, animalHolder.itemView);
                    }
                }

                return false;
            }
        });
        return animalHolder;
    }

    @Override
    public void onBindViewHolder(final AnimalHolder holder, final int position) {

        final Animais animais = animaisList.get(position);

        byte[] profileImagePet = animais.getFoto();
        Bitmap raw = BitmapFactory.decodeByteArray(profileImagePet, 0, profileImagePet.length);

        holder.textNome.setText(animais.getNome());
        holder.textRaca.setText(animais.getRaca());
        holder.textCategoria.setText(animais.getCategoria());
        holder.fotoPet.setImageBitmap(raw);


    }


    @Override
    public int getItemCount() {
        return animaisList.size();
    }

    public void setOnItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public  void setOnItemLongClickListener(RecyclerItemLongClickListener recyclerItemLongClickListener){
        this.recyclerItemLongClickListener = recyclerItemLongClickListener;
    }


    public int getSelectedItemID()
    {
        return animais.getId();
    }
}

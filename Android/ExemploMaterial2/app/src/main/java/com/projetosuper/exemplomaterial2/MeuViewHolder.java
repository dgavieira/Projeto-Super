package com.projetosuper.exemplomaterial2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MeuViewHolder extends RecyclerView.ViewHolder {
    ImageView imagem;
    TextView titulo;
    TextView data_exib;

    public MeuViewHolder(View itemView) {
        super(itemView);
        imagem = itemView.findViewById(R.id.imagemCelula);
        titulo = itemView.findViewById(R.id.texto1);
        data_exib = itemView.findViewById(R.id.texto2);
    }

    public void bind(final Episodio item, final MeuAdaptador.OnItemClickListener listener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(item);
            }
        });
    }
}

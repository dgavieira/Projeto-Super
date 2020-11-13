package com.projetosuper.crud_android;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContatoHolder extends RecyclerView.ViewHolder {
    public TextView celula_nome, celula_empresa, celula_telefone, celula_email;
    public ImageButton btn_editar, btn_deletar;

    public ContatoHolder(@NonNull View itemView) {
        super(itemView);
        celula_nome = itemView.findViewById(R.id.celula_nome);
        celula_empresa = itemView.findViewById(R.id.celula_empresa);
        celula_telefone = itemView.findViewById(R.id.celula_telefone);
        celula_email = itemView.findViewById(R.id.celula_email);
        btn_editar = itemView.findViewById(R.id.btn_editar);
        btn_deletar = itemView.findViewById(R.id.btn_deletar);
    }

}

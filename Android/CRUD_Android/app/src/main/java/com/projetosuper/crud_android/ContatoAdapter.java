package com.projetosuper.crud_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoHolder> {
    private final List<Contato> contatos;

    public ContatoAdapter(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @NonNull
    @Override
    public ContatoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContatoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.celula, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoHolder holder, int position) {
        holder.celula_nome.setText(contatos.get(position).getNome());
        holder.celula_empresa.setText(contatos.get(position).getEmpresa());
        holder.celula_telefone.setText(contatos.get(position).getTelefone());
        holder.celula_email.setText(contatos.get(position).getEmail());
        final Contato contato = contatos.get(position);

        holder.btn_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir esse contato?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ContatoDAO dao = new ContatoDAO(view.getContext());
                                boolean sucesso = dao.excluir(contato.getId());
                                if(sucesso){
                                    removerContato(contato);
                                    Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                            .setAction("Action",null).show();
                                }else{
                                    Snackbar.make(view, "Erro ao excluir o contato!", Snackbar.LENGTH_LONG)
                                            .setAction("Action",null).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar",null)
                        .create()
                        .show();
            }
        });

        holder.btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getActivity(view);
                Intent intent = activity.getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("contato", contato);
                activity.finish();
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contatos != null ? contatos.size() : 0;
    }
    public void adicionarContato(Contato contato){
        contatos.add(contato);
        notifyItemInserted(getItemCount());
    }
    private Activity getActivity(View view){
        Context context = view.getContext();
        while (context instanceof ContextWrapper){
            if(context instanceof Activity){
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    public void atualizarContato(Contato contato){
        contatos.set(contatos.indexOf(contato), contato);
        notifyItemChanged(contatos.indexOf(contato));
    }
    public void removerContato(Contato contato){
        int position = contatos.indexOf(contato);
        contatos.remove(position);
        notifyItemRemoved(position);
    }

}

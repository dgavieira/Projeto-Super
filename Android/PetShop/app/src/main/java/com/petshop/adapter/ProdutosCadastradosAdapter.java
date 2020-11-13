package com.petshop.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.petshop.R;
import com.petshop.listener.RecyclerItemClickListener;
import com.petshop.listener.RecyclerItemLongClickListener;
import com.petshop.model.DbBitmapUtility;
import com.petshop.model.Produto;
import com.petshop.resource.CircleImageView;

import java.util.ArrayList;
import java.util.List;


public class  ProdutosCadastradosAdapter extends RecyclerView.Adapter<ProdutosCadastradosAdapter.ContactHolder>{

    private List<Produto> produtoList;
    private Context context;
    private ProdutosCadastradosAdapter contactListAdapter;
    private RecyclerItemClickListener recyclerItemClickListener;
    private RecyclerItemLongClickListener recyclerItemLongClickListener;
    byte[] fotoArray;

    Produto produto;
    int position;

    public ProdutosCadastradosAdapter(Context context) {
        this.context = context;
        this.produtoList = new ArrayList<>();
    }

    private void add(Produto item) {
        produtoList.add(item);
        notifyItemInserted(produtoList.size());
    }

    public void addAll(List<Produto> produtoList) {
        for (Produto produto : produtoList) {
            add(produto);
        }
    }

    public void remove(Produto item) {
        int position = produtoList.indexOf(item);
        if (position > -1) {
            produtoList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Produto getItem(int position) {
        return produtoList.get(position);
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_produto, parent, false);

        final ContactHolder contactHolder = new ContactHolder(view);

        contactHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = contactHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerItemClickListener != null) {
                        recyclerItemClickListener.onItemClick(adapterPos, contactHolder.itemView);
                    }
                }

            }
        });
        contactHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                produto = (Produto) getItem(position);
                int adapterPos = contactHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerItemClickListener != null) {
                        recyclerItemLongClickListener.onItemLongClick(adapterPos, contactHolder.itemView);
                    }
                }

                return false;
            }
        });

        return contactHolder;
    }



    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {

        final Produto produto = produtoList.get(position);
        byte[] profileImage = produto.getImg();
        Bitmap raw = BitmapFactory.decodeByteArray(profileImage, 0, profileImage.length);

        holder.nome.setText(produto.getNome());
        holder.descricao.setText(produto.getDescricao());
        holder.valor.setText(String.valueOf(produto.getPreco()));
        holder.foto.setImageBitmap(raw);

    }


    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    public void setOnItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public  void setOnItemLongClickListener(RecyclerItemLongClickListener recyclerItemLongClickListener){
        this.recyclerItemLongClickListener = recyclerItemLongClickListener;
    }

    static class ContactHolder extends RecyclerView.ViewHolder{

        CircleImageView foto;
        TextView nome;
        TextView descricao;
        TextView valor;

        public ContactHolder(final View itemView) {
            super(itemView);

            foto = (CircleImageView) itemView.findViewById(R.id.foto);
            nome = (TextView) itemView.findViewById(R.id.nome);
            descricao = (TextView) itemView.findViewById(R.id.descricao);
            valor = (TextView) itemView.findViewById(R.id.valor);


        }
    }

    public int getSelectedItemID()
    {
        return produto.getId();
    }
    public Bitmap getSelectedItemFoto() {
        return DbBitmapUtility.getImage(produto.getImg());
    }

    public String getSelectedItemName() {
        return produto.getNome();
    }
    public String getSelectedItemDescricao() {
        return produto.getDescricao();
    }
    public double getSelectedItemValor() {
        return produto.getPreco();
    }
}

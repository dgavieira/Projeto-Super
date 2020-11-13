package com.petshop.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.petshop.R;
import com.petshop.db.ProdutoDAO;
import com.petshop.model.DbBitmapUtility;
import com.petshop.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    private OnCheckBoxClickListener onCheckBoxClickListener;
    private OnSpinnerItemSelectedListener onSpinnerItemSelectedListener;

    public void setOnCheckBoxClickListener(OnCheckBoxClickListener onCheckBoxClickListener){
        this.onCheckBoxClickListener = onCheckBoxClickListener;
    }

    public void setOnSpinnerItemSelectedListener(OnSpinnerItemSelectedListener onSpinnerItemSelectedListener){
        this.onSpinnerItemSelectedListener = onSpinnerItemSelectedListener;
    }

    private final Context mContext;
    private ArrayList<Object> produtoList;
    private ArrayList<Object> mArrayList;
    private OnItemClickListener onItemClickListener;
    private ProdutoDAO data;



    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public Object getItem(int position) {
        return  produtoList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, AdapterView.OnItemSelectedListener {
        AppCompatTextView nome, preco;
        ImageView imgView;
        final CheckBox produtoSelected;
        TextView buttonDetails;
        MaterialCardView card;
        Spinner spinnerQuantidade;
        OnItemClickListener onItemClickListener;
        private String spinnerSelected;

        public MyViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
           // itemView.setOnClickListener((View.OnClickListener) this);
            this.nome = (AppCompatTextView) itemView.findViewById(R.id.produto_nome);
            this.preco = (AppCompatTextView) itemView.findViewById(R.id.produto_preco);
            this.card = (MaterialCardView) itemView.findViewById(R.id.card);
            this.imgView = (ImageView) itemView.findViewById(R.id.produto_image);
            this.produtoSelected = (CheckBox) itemView.findViewById(R.id.produto_selected);
            this.buttonDetails = (TextView) itemView.findViewById(R.id.produto_details);
            this.spinnerQuantidade = (Spinner) itemView.findViewById(R.id.produto_qtd);
           // this.onItemClickListener = onItemClickListener;
            this.spinnerQuantidade.setOnItemSelectedListener(this);

        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            spinnerSelected = adapterView.getItemAtPosition(i).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    public ProdutoAdapter(Context mContext, ArrayList<Object> produtoList) {
        this.mContext = mContext;
        this.produtoList = produtoList;
        this.mArrayList = produtoList;
        data = new ProdutoDAO(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_shop, parent,
                        false);
        MyViewHolder myViewHolder = new MyViewHolder(view,  onItemClickListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        List<Integer> list = new ArrayList<>();
        for ( int i = 1; i < 101; i++ ){
            list.add(i);
        }

        final Produto produto = (Produto) produtoList.get(position);
        holder.nome.setText(produto.getNome());
        holder.preco.setText(String.format("R$ %.2f", produto.getPreco()));
        holder.imgView.setImageBitmap(DbBitmapUtility.getImage(produto.getImg()));
        holder.spinnerQuantidade.setAdapter(new ArrayAdapter<Integer>(this.mContext, android.R.layout.simple_dropdown_item_1line, list));

        holder.spinnerQuantidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onSpinnerItemSelectedListener.onSpinnerItemSelectedListener(holder.card.isChecked(), position, (int) holder.spinnerQuantidade.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        holder.produtoSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.produtoSelected.isChecked()){
                    Toast.makeText(mContext, "Item Selecionado" + position, Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.card.toggle();
                onCheckBoxClickListener.onCheckBoxClickListener(holder.card.isChecked(), position, (int) holder.spinnerQuantidade.getSelectedItem());
            }
        });

        holder.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog builder = new Dialog(mContext);
                builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                builder.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        //nothing;
                    }
                });

                View detalhesView;
                ViewGroup parent = null;
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                detalhesView = inflater.inflate(R.layout.item_detalhes, parent, false);
                ProdutoDetalhesView produtoDetalhesV = new ProdutoDetalhesView();
                produtoDetalhesV.card = detalhesView.findViewById(R.id.cardDetalhes);
                produtoDetalhesV.descricao = detalhesView.findViewById(R.id.txtDetalhes);
                produtoDetalhesV.nome = detalhesView.findViewById(R.id.txtDetalhesNome);
                produtoDetalhesV.img = detalhesView.findViewById(R.id.imgDetalhes);
                detalhesView.setTag(produtoDetalhesV);
                produtoDetalhesV.descricao.setText(produto.getDescricao());
                produtoDetalhesV.nome.setText(produto.getNome());
                produtoDetalhesV.img.setImageBitmap(DbBitmapUtility.getImage(produto.getImg()));

                // Cria Layout
                // Adiciona no card

                // Adiciona card View ao dialog
                builder.addContentView(detalhesView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    public interface OnCheckBoxClickListener{
        void onCheckBoxClickListener(boolean isChecked, int position, int qnt);
    }

    public interface OnSpinnerItemSelectedListener{
        void onSpinnerItemSelectedListener(boolean isChecked, int position, int qnt);
    }
}
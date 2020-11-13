package com.petshop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.petshop.adapter.ItensCompradosAdapter;
import com.petshop.adapter.ProdutoAdapter;
import com.petshop.db.Database;
import com.petshop.db.ProdutoDAO;
import com.petshop.db.ShopDAO;
import com.petshop.model.Usuario;
import com.petshop.model.Produto;
import com.petshop.model.Shop;
import com.petshop.payment.CardType;
import com.petshop.payment.PaymentCardView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.GONE;

public class ActivityShop extends AppCompatActivity {

    ProdutoDAO produtoDAO;
    RecyclerView rv;
    TextView txtValorTotal;
    HashMap<Integer, Integer> itensComprados;
    ArrayList<Object> produtos = new ArrayList<>();
    ProdutoAdapter produtosAdapter;
    ItensCompradosAdapter itensCompradosAdapter;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        txtValorTotal = findViewById(R.id.txtValorTotal);
        produtoDAO = new ProdutoDAO(ActivityShop.this);
        produtos = produtoDAO.getList();
        itensComprados = new HashMap<>();
        produtosAdapter = new ProdutoAdapter(ActivityShop.this, produtos);
        rv = findViewById(R.id.recyclerViewProduto);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setHasFixedSize(true);
        rv.setAdapter(produtosAdapter);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");


        produtosAdapter.setOnCheckBoxClickListener(new ProdutoAdapter.OnCheckBoxClickListener() {
                                                       @Override
                                                       public void onCheckBoxClickListener(boolean isChecked, int position, int qnt) {
                                                           Produto p = (Produto) produtosAdapter.getItem(position);
                                                           if ( isChecked ){
                                                               itensComprados.put(p.getId(), qnt);
                                                           } else {
                                                               itensComprados.remove(p.getId());
                                                           }
                                                           txtValorTotal.setText(String.format("R$ %.2f", somaPrecos()));
                                                       }
                                                   });

        produtosAdapter.setOnSpinnerItemSelectedListener(new ProdutoAdapter.OnSpinnerItemSelectedListener() {
            @Override
            public void onSpinnerItemSelectedListener(boolean isChecked, int position, int qnt) {
                Produto p = (Produto) produtosAdapter.getItem(position);
                if ( isChecked ){
                    itensComprados.put(p.getId(),qnt);
                    txtValorTotal.setText(String.format("R$ %.2f", somaPrecos()));
                }

            }
        });


                FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarCompra();
            }
        });
    }

    private double somaPrecos(){
        double res = 0;

        for ( Object obj : produtos ){
            Produto p = (Produto) obj;
            if ( itensComprados.containsKey(p.getId()) ){
                res += itensComprados.get(p.getId()) * p.getPreco();
            }
        }
        return res;
    }

    public void realizarCompra(){

        // Verifica se nenhum item foi selecionado
        if ( itensComprados.isEmpty() ){
            Toast.makeText(this, "Nenhum item foi selecionado!", Toast.LENGTH_SHORT).show();
            return;
        }

        new MaterialAlertDialogBuilder(this)
                .setTitle("Deseja efetuar a compra?")
                .setMessage(String.format("Total a pagar: R$ %.2f", somaPrecos()))
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing
                    }
                })
                .setPositiveButton("Realizar Pagamento", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Dialog dialog = new Dialog(ActivityShop.this);
                        dialog.setContentView(R.layout.activity_pagamento);

                        PaymentCardView mPaymentCard = (PaymentCardView) dialog.findViewById(R.id.card);

                        mPaymentCard.setOnPaymentCardEventListener(new PaymentCardView.OnPaymentCardEventListener() {
                            @Override
                            public void onCardDetailsSubmit(String month, String year, String cardNumber, String cvv) {
                                new MaterialAlertDialogBuilder(ActivityShop.this)
                                        .setTitle("Confirmar Compra?")
                                        .setMessage(String.format("Total a pagar: R$ %.2f", somaPrecos()))
                                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                // Do nothing
                                            }
                                        })
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                // Efetucar compra
                                                ShopDAO shopDAO = new ShopDAO(ActivityShop.this);
                                                for ( Integer idProduto : itensComprados.keySet() ){
                                                    // Cria um novo objeto Shop para inserir no banco de dados
                                                    shopDAO.create(new Shop(usuario.getId(), idProduto, itensComprados.get(idProduto)));
                                                }
                                                // Exibe mensage de conclusão
                                                Toast.makeText(ActivityShop.this, "Compra efetuada com sucesso!", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                                rv.setAdapter(produtosAdapter);
                                                itensComprados.clear();
                                                txtValorTotal.setText(String.format("R$ %.2f", somaPrecos()));

                                            }
                                        })
                                        .show();
                            }

                            @Override
                            public void onError(String error) {
                                Toast.makeText(ActivityShop.this, error, Toast.LENGTH_SHORT).show();
                            }
                        });

                        dialog.show();

                    }
                })
                .show();




        // Pedir confirmação
        /*
        new MaterialAlertDialogBuilder(this)
                .setTitle("Deseja efetuar a compra?")
                .setMessage(String.format("Total a pagar: R$ %.2f", somaPrecos()))
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Efetucar compra
                        ShopDAO shopDAO = new ShopDAO(ActivityShop.this);
                        for ( Integer idProduto : itensComprados.keySet() ){
                            // Cria um novo objeto Shop para inserir no banco de dados
                            shopDAO.create(new Shop(usuario.getId(), idProduto, itensComprados.get(idProduto)));
                        }
                        // Exibe mensage de conclusão
                        Toast.makeText(ActivityShop.this, "Compra efetuada com sucesso!", Toast.LENGTH_SHORT).show();
                        // Limpa todos os dados
                        //produtosAdapter.clear();
                        //produtosAdapter.addAll(produtos);
                        rv.setAdapter(produtosAdapter);
                        itensComprados.clear();
                        txtValorTotal.setText(String.format("R$ %.2f", somaPrecos()));

                    }
                })
                .show(); */

    }
}
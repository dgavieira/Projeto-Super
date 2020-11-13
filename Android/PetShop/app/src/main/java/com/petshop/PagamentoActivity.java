package com.petshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.petshop.payment.PaymentCardView;

public class PagamentoActivity extends AppCompatActivity {

    private PaymentCardView mPaymentCard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        mPaymentCard = (PaymentCardView) findViewById(R.id.card);

        mPaymentCard.setOnPaymentCardEventListener(new PaymentCardView.OnPaymentCardEventListener() {
            @Override
            public void onCardDetailsSubmit(String month, String year, String cardNumber, String cvv) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(PagamentoActivity.this);
                dialog.setTitle("Dados de Pagamento");
                dialog.setMessage(month + "\n" + year + "\n" + cardNumber + "\n" + cvv);
                dialog.show();
                //Toast.makeText(PagamentoActivity.this, "Card details submitted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(PagamentoActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
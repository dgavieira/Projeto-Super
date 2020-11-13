package com.petshop.ui.loja;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.petshop.ActivityLoja;
import com.petshop.R;
import com.petshop.resource.VideoLayout;

public class LoginLojaFragment extends Fragment {

    VideoLayout videoLayout;
    EditText editEmail, editSenha;
    Button login;
    String senha = "root";
    String email = "root";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_login_loja, container, false);
        videoLayout = root.findViewById(R.id.videoLayout);
        videoLayout.setPathOrUrl("video02.mp4");

        editEmail = root.findViewById(R.id.et_login_email_adress);
        editSenha = root.findViewById(R.id.et_login_email_password);
        login = root.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = editEmail.getText().toString();
                String senhaString = editSenha.getText().toString();

                if (emailString.isEmpty() || senhaString.isEmpty()){
                    Toast.makeText(getContext(), "Entre com o email e senha", Toast.LENGTH_SHORT).show();
                }else{
                    if(emailString.equals(email) && senhaString.equals(senha)){
                        Intent intent = new Intent(getActivity(), ActivityLoja.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getContext(), "Email e Senha incorretos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return root;
    }
}
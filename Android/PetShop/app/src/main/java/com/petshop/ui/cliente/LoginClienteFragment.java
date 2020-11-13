package com.petshop.ui.cliente;

import android.content.Context;
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
import androidx.fragment.app.Fragment;

import com.petshop.ActivityCliente;
import com.petshop.CadastroCliente;
import com.petshop.R;
import com.petshop.db.SQLiteDBCliente;
import com.petshop.db.UsuarioDAO;
import com.petshop.model.Cliente;
import com.petshop.model.Usuario;
import com.petshop.resource.VideoLayout;

public class LoginClienteFragment extends Fragment {

    VideoLayout videoLayout;
    TextView textView;
    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;
    private Button appCompatButtonLogin;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_login_cliente, container, false);
        //root.getFitsSystemWindows();

        textView = root.findViewById(R.id.txtCadastro);
        textInputEditTextEmail = root.findViewById(R.id.email);
        textInputEditTextPassword = root.findViewById(R.id.senha);
        appCompatButtonLogin = root.findViewById(R.id.btnLogin);
        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =  textInputEditTextEmail.getText().toString();
                String senha =  textInputEditTextPassword.getText().toString();

                if ( email.isEmpty() || senha.isEmpty() ){
                    Toast.makeText(getContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    UsuarioDAO userDAO = new UsuarioDAO(getContext());
                    Usuario user = (Usuario) userDAO.get(email);

                    // Verifica se há um usuário com esse email
                    if ( user == null ){
                        Toast.makeText(getContext(), "Email não cadastrado.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Verifica se a senha está correta
                    if ( user.getSenha().equals(senha) ){

                        Toast.makeText(getContext(), "Seja bem vindo, " + user.getNome(), Toast.LENGTH_SHORT).show();



                        // Envia o objeto usuário para a próxima activity
                        Intent intent = new Intent(getActivity(), ActivityCliente.class);
                        intent.putExtra("Usuario",user);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getContext(), "Senha incorreta!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadastroCliente.class);
                startActivity(intent);
            }
        });
        videoLayout = root.findViewById(R.id.videoLayout);
        videoLayout.setPathOrUrl("video01.mp4");
        return root;
    }
}
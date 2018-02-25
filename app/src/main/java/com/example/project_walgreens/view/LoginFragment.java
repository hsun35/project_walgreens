package com.example.project_walgreens.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project_walgreens.R;
import com.example.project_walgreens.presenter.INetPresenter;
import com.example.project_walgreens.presenter.NetPresenter;
import com.example.project_walgreens.utils.SendMessage;

/**
 * Created by hefen on 2/24/2018.
 */

public class LoginFragment extends Fragment implements ILoginFragment{
    SendMessage sendMessage;
    View rootView;//used to init listview
    Button loginButton;
    //EditText usernameText;
    EditText passwordText;
    EditText mobileText;
    TextView forgetPasswordText;

    Context context;

    INetPresenter iNetPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login,container,false);
        //Log.i("mylog", "on create fragment");
        initLogin();

        return rootView;
    }

    private void initLogin() {
        context = rootView.getContext();
        loginButton = rootView.findViewById(R.id.buttonLogin);
        //usernameText = rootView.findViewById(R.id.editTextUsername);
        passwordText = rootView.findViewById(R.id.editTextPassword);
        mobileText = rootView.findViewById(R.id.editTextMobileConfirm);
        forgetPasswordText = rootView.findViewById(R.id.textViewForget);

        iNetPresenter = new NetPresenter(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String username = usernameText.getText().toString();
                String mobile = mobileText.getText().toString();
                String password = passwordText.getText().toString();
                if (mobile == null || mobile.length() == 0 || password == null || password.length() == 0) {
                    sendMessage.showMessage("Please don't leave any blanket empty.");
                } else {
                    iNetPresenter.login(mobile, password);
                    Log.i("mylog", mobile + " " + password);
                }
            }
        });

        forgetPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = mobileText.getText().toString();
                if (mobile == null || mobile.length() == 0) {
                    sendMessage.showMessage("Please input your phone number.");
                } else {
                    iNetPresenter.getPassword(mobile);
                }
            }
        });
    }

    public void setSendMessage(SendMessage sendMessage){
        this.sendMessage = sendMessage;
    }

    @Override
    public void login() {
        //Log.i("mylog", "loginFragment log in");
        sendMessage.sendCommand("login");
    }

    @Override
    public void showLoginMessage(String msg) {
        sendMessage.showMessage(msg);
    }
}

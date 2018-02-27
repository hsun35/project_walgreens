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
import android.widget.TextView;

import com.example.project_walgreens.R;
import com.example.project_walgreens.presenter.INetPresenter;
import com.example.project_walgreens.presenter.NetPresenter;
import com.example.project_walgreens.utils.SendMessage;

//import org.apache.commons.validator.routines.EmailValidator;

/**
 * Created by hefen on 2/27/2018.
 */

public class RegisterFragment extends Fragment implements IRegisterFragment{
    SendMessage sendMessage;
    View rootView;//used to init listview
    Context context;

    INetPresenter iNetPresenter;//Net

    EditText usernameText;
    EditText mobileText;
    EditText passwordText;
    EditText emailText;
    TextView loginText;
    Button registerButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_register,container,false);
        //Log.i("mylog", "on create fragment");
        initRegister();

        return rootView;
    }

    private void initRegister() {
        context = rootView.getContext();
        usernameText = rootView.findViewById(R.id.editText);
        emailText = rootView.findViewById(R.id.editText2);
        mobileText = rootView.findViewById(R.id.editText3);
        passwordText = rootView.findViewById(R.id.editText4);
        registerButton = rootView.findViewById(R.id.buttonSignup);
        loginText = rootView.findViewById(R.id.textView4);

        iNetPresenter = new NetPresenter(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String email = emailText.getText().toString();
                String mobil = mobileText.getText().toString();
                String password = passwordText.getText().toString();

                if (username == null || username.length() == 0) {
                    sendMessage.showMessage("Please put in your username.");
                    return;
                }
                if (email == null || email.length() == 0) {
                    sendMessage.showMessage("Please put in your Email.");
                    return;
                } else if (!isValidEmailAddress(email)) {
                    sendMessage.showMessage("Invalid Email.");
                    return;
                }

                if (mobil == null || mobil.length() == 0) {
                    sendMessage.showMessage("Please put in your phone number.");
                    return;
                } else if (!mobil.matches("[0-9]+") || mobil.length() < 8) {
                    sendMessage.showMessage("Invalid phone number.");
                    return;
                }

                if (password == null || password.length() < 4) {
                    sendMessage.showMessage("Invalid password.");
                    return;
                }
                iNetPresenter.getRegister(username, email, mobil, password);
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage.sendCommand("signin");
            }
        });
    }

    boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public void setSendMessage(SendMessage sendMessage){
        this.sendMessage = sendMessage;
    }

    @Override
    public void showRegisterMessage(String msg) {
        Log.i("mylog", "register msg: " + msg);
        if (msg.equals("successfully")) {
            sendMessage.showMessage("Successfully registered");
            sendMessage.sendCommand("signin");
        } else if (msg.equals("Mobile")) {
            sendMessage.showMessage("Mobile Number already exsist");
        }
    }


}

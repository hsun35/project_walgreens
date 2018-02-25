package com.example.project_walgreens.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.project_walgreens.R;
import com.example.project_walgreens.network.AccountDescription;
import com.example.project_walgreens.utils.SendMessage;

/**
 * Created by hefen on 2/24/2018.
 */

public class AccountFragment extends Fragment {
    SendMessage sendMessage;
    View rootView;
    Context context;
    TextView usernameText;
    TextView emailText;
    TextView mobileText;
    Button logoutButton;
    CheckBox resetpasswordButton;
    EditText oldpasswordText;
    EditText newpasswordText;
    Button submitButton;
    Animation animation;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_account,container,false);
        //Log.i("mylog", "on create fragment");
        initAccount();

        return rootView;
    }

    private void initAccount() {
        context = rootView.getContext();
        usernameText = rootView.findViewById(R.id.textView7);
        emailText = rootView.findViewById(R.id.textView8);
        mobileText = rootView.findViewById(R.id.textView9);
        logoutButton = rootView.findViewById(R.id.buttonLogout);
        resetpasswordButton = rootView.findViewById(R.id.radioButton);
        submitButton = rootView.findViewById(R.id.buttonSubmitPW);
        oldpasswordText = rootView.findViewById(R.id.editText5);
        newpasswordText = rootView.findViewById(R.id.editText6);

        usernameText.setText("Username: " + AccountDescription.UserName);
        emailText.setText("Email: " + AccountDescription.UserEmail);
        mobileText.setText("Mobile: " + AccountDescription.UserMobile);

        submitButton.setAlpha(0.0f);
        oldpasswordText.setAlpha(0.0f);
        newpasswordText.setAlpha(0.0f);

        resetpasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //resetpasswordButton.toggle();
                if (resetpasswordButton.isChecked()) {
                    animation = AnimationUtils.loadAnimation(context, R.anim.fadein);
                    submitButton.setAlpha(1.0f);
                    oldpasswordText.setAlpha(1.0f);
                    newpasswordText.setAlpha(1.0f);
                    submitButton.startAnimation(animation);
                    oldpasswordText.startAnimation(animation);
                    newpasswordText.startAnimation(animation);
                } else {
                    animation = AnimationUtils.loadAnimation(context, R.anim.fadeout);

                    submitButton.startAnimation(animation);
                    oldpasswordText.startAnimation(animation);
                    newpasswordText.startAnimation(animation);
                    //submitButton.setAlpha(0.0f);
                    //oldpasswordText.setAlpha(0.0f);
                    //newpasswordText.setAlpha(0.0f);
                    oldpasswordText.setText("");
                    newpasswordText.setText("");
                }
            }
        });
    }

    public void setSendMessage(SendMessage sendMessage){
        this.sendMessage = sendMessage;
    }
}

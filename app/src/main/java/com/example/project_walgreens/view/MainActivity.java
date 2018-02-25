package com.example.project_walgreens.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.widget.Toolbar;//rather than android.widget.Toolbar
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project_walgreens.R;
import com.example.project_walgreens.network.AccountDescription;
import com.example.project_walgreens.utils.SendMessage;

public class MainActivity extends AppCompatActivity implements SendMessage, IMainActivity{
    Toolbar myToolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();//!!

        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        addFrontpageFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.account_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.list) {
            Toast.makeText(MainActivity.this, "my account", Toast.LENGTH_SHORT).show();
            if (AccountDescription.msg.equals("success")) {
                addAccountFragment();
            } else {
                addLoginFragment();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void addFrontpageFragment() {
        FrontpageFragment frontpageFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        frontpageFragment = new FrontpageFragment();
        frontpageFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, frontpageFragment);//in CountriesFragment import android.support.v4.app.Fragment;
        fragmentTransaction.commit();
        //Log.i("mylog", "add list");
    }

    private void addLoginFragment() {
        LoginFragment loginFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        loginFragment = new LoginFragment();
        loginFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, loginFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addAccountFragment() {
        AccountFragment accountFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        accountFragment = new AccountFragment();
        accountFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, accountFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addCategoryFragment() {
        CategoryFragment categoryFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        categoryFragment = new CategoryFragment();
        categoryFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, categoryFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void sendData(int item_index) {
        Object f = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (f instanceof FrontpageFragment) {
            if (AccountDescription.msg.equals("success")) {
                if (item_index == 1) {
                    Toast.makeText(MainActivity.this, "Category List", Toast.LENGTH_SHORT).show();
                    addCategoryFragment();
                } else {
                    Toast.makeText(MainActivity.this, "item #" + item_index, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Please log in.", Toast.LENGTH_SHORT).show();
            }
        } else if (f instanceof CategoryFragment) {
            Toast.makeText(MainActivity.this, "Category #" + item_index, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void sendCommand(String command) {
        if (command.equals("login")) {
            Toast.makeText(MainActivity.this, "Log in", Toast.LENGTH_SHORT).show();
            addFrontpageFragment();
        }
    }

    /*@Override
    public void login() {
        Toast.makeText(MainActivity.this, "Log in", Toast.LENGTH_SHORT).show();
        addFrontpageFragment();
    }*/
}

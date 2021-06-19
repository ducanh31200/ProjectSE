package com.example.monitoring_app.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monitoring_app.R;
import com.example.monitoring_app.Models.SQLiteHandler;
import com.example.monitoring_app.ui.account.Account;
import com.example.monitoring_app.ui.account.AccountFragment;
import com.example.monitoring_app.ui.account.AccountPresenter;
import com.example.monitoring_app.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    AccountPresenter accountPresenter;
    private AppBarConfiguration mAppBarConfiguration;
    private SQLiteHandler db;
    String Username;
    Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        /*
        AccountFragment accountFragment = new AccountFragment();
        accountFragment.setArguments(bundle);
        */
        accountPresenter = new AccountPresenter(getApplicationContext());
        Username = accountPresenter.getCurrent();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        View header = navigationView.getHeaderView(0);
        TextView tv_name = header.findViewById(R.id.textViewName);
        TextView tv_email = header.findViewById(R.id.textViewEmail);

        account = accountPresenter.getAccount(Username);
        tv_name.setText(account.getName());
        tv_email.setText(account.getMail());

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,  R.id.nav_notify, R.id.nav_physical, R.id.nav_account, R.id.nav_changepassword)
                .setDrawerLayout(drawer)
                .build();

        /*
        Bundle bundle = new Bundle();
        bundle.putString("username", Username);
        */
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_Logout) {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void dialog_edit_account() {
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
    }
}
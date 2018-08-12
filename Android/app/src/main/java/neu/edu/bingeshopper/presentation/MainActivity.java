package neu.edu.bingeshopper.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.UserType;
import neu.edu.bingeshopper.presentation.home.HomeFragment;
import neu.edu.bingeshopper.presentation.login.LoginFragment;

import static neu.edu.bingeshopper.R.layout.activity_main;

public class MainActivity extends DaggerAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Inject
    neu.edu.bingeshopper.common.UserManager userManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        updateNavBar();
        init();
    }


    private void init() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, HomeFragment.newInstance());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                navigate(HomeFragment.newInstance());
                return true;

            case R.id.login:
                navigate(LoginFragment.newInstance());
                return true;

        }
        return false;
    }

    public void updateNavBar() {

        if (userManager.getUser() != null && userManager.getUser().getUserType() == UserType.BUYER) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.drawer_view_logout);
        } else if (userManager.getUser() != null && userManager.getUser().getUserType() == UserType.SELLER) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.drawer_view_seller_logout);
        }

    }


    private void navigate(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
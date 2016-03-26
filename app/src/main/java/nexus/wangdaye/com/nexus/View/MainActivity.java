package nexus.wangdaye.com.nexus.View;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import nexus.wangdaye.com.nexus.R;
import nexus.wangdaye.com.nexus.Widget.BitmapHelper;
import nexus.wangdaye.com.nexus.Widget.CircleImageView;
import nexus.wangdaye.com.nexus.Widget.MyFloatingActionButton;
import nexus.wangdaye.com.nexus.Widget.ThemeActivity;

public class MainActivity extends ThemeActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {
    // widget
    private FrameLayout statusBar;
    private MyFloatingActionButton fab;

    // data
    public static boolean isDay;
    private int fragmentNow;

    private final int SETTINGS_ACTIVITY = 1;
    private final int SHARE_ACTIVITY = 2;

    private final int HOME_FRAGMENT = 7;
    private final int MESSAGE_FRAGMENT = 8;
    private final int FIND_FRAGMENT = 9;
    private final int ACCOUNT_FRAGMENT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStatusBarTransparent();
        setContentView(R.layout.activity_main);
        isDay = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.initWidget();
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) statusBar.getLayoutParams();
        this.initColorTheme(true, getString(R.string.app_name), statusBar, layoutParams);
        this.changeFragment(HOME_FRAGMENT);
    }

    private void initWidget() {
        this.statusBar = (FrameLayout) findViewById(R.id.activity_main_statusBar);

        this.fab = (MyFloatingActionButton) findViewById(R.id.activity_main_fab);
        if (fab != null) {
            fab.setOnClickListener(this);
            fab.showing = false;
            fab.show(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_main_navView);
        if (navigationView != null) {
            View navHeader = navigationView.getHeaderView(0);
            ImageView navHeaderBackground = (ImageView) navHeader.findViewById(R.id.nav_header_background);
            int width = BitmapHelper.getSize(500, getResources().getDisplayMetrics().widthPixels);
            navHeaderBackground.setImageBitmap(BitmapHelper.readBitMap(this, R.drawable.nav_header, width, (float) (width * (9.0 / 16))));
            CircleImageView personalIcon = (CircleImageView) navHeader.findViewById(R.id.personal_icon);
            width = BitmapHelper.getSize(100, getResources().getDisplayMetrics().widthPixels);
            personalIcon.setImageBitmap(BitmapHelper.readBitMap(this, R.drawable.ic_launcher, width, width));
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    public void changeFragment(int fragmentTo) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment newFragment;
        this.fragmentNow = fragmentTo;
        NavigationView navView = (NavigationView) findViewById(R.id.activity_main_navView);
        switch (fragmentTo) {
            case HOME_FRAGMENT:
                this.setWindowTopColor(getString(R.string.app_name));
                if (navView != null) {
                    navView.setCheckedItem(R.id.nav_home);
                }
                newFragment = new HomeFragment();
                break;
            case MESSAGE_FRAGMENT:
                this.setWindowTopColor(getString(R.string.nav_message));
                if (navView != null) {
                    navView.setCheckedItem(R.id.nav_message);
                }
                newFragment = new HomeFragment();
                break;
            case FIND_FRAGMENT:
                this.setWindowTopColor(getString(R.string.nav_find));
                if (navView != null) {
                    navView.setCheckedItem(R.id.nav_find);
                }
                newFragment = new HomeFragment();
                break;
            case ACCOUNT_FRAGMENT:
                this.setWindowTopColor(getString(R.string.nav_account));
                if (navView != null) {
                    navView.setCheckedItem(R.id.nav_account);
                }
                newFragment = new HomeFragment();
                break;
            default:
                newFragment = new HomeFragment();
                break;
        }
        transaction.replace(R.id.activity_main_container, newFragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SETTINGS_ACTIVITY:/*
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) statusBar.getLayoutParams();
                this.initColorTheme(false, getString(R.string.app_name), statusBar, layoutParams);*/
                break;
            case SHARE_ACTIVITY:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else if (fragmentNow != HOME_FRAGMENT) {
                this.changeFragment(HOME_FRAGMENT);
            } else  {
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            this.changeFragment(HOME_FRAGMENT);
        } else if (id == R.id.nav_message) {
            this.changeFragment(MESSAGE_FRAGMENT);
        } else if (id == R.id.nav_find) {
            this.changeFragment(FIND_FRAGMENT);
        } else if (id == R.id.nav_account) {
            this.changeFragment(ACCOUNT_FRAGMENT);
        } else if (id == R.id.nav_model) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int widgetId = v.getId();

        if (widgetId == R.id.activity_main_fab) {
            if (fab.opening) {
                fab.close(this);
            } else {
                fab.open(this);
            }
        }
    }
}

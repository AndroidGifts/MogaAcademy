package com.android.gifts.moga.views.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.gifts.moga.R;
import com.android.gifts.moga.gcm.GcmIntentService;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.helpers.UIHelper;
import com.android.gifts.moga.helpers.prefs.ComplexPreferences;
import com.android.gifts.moga.helpers.prefs.ObjectPreference;
import com.android.gifts.moga.presenter.main.MainPresenter;
import com.android.gifts.moga.presenter.main.MainPresenterImp;
import com.android.gifts.moga.views.adapters.NewsFragmentPagerAdapter;
import com.android.gifts.moga.views.fragments.staticFragments.AboutUsFragment;
import com.android.gifts.moga.views.fragments.staticFragments.HighStudiesFragment;
import com.android.gifts.moga.views.fragments.NewsFragment;
import com.android.gifts.moga.views.fragments.staticFragments.OpenLearningFragment;
import com.android.gifts.moga.views.fragments.SettingsFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;

    private MaterialDialog progressDialog;

    private MainPresenter presenter;

    List<Fragment> fragments = new ArrayList<>();
    private long userYear;
    long selectedItem;

    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;

    // Used for GCM
    private String TAG = MainActivity.class.getSimpleName();
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private ComplexPreferences complexPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Getting shared Prefs object to save user device ID
        ObjectPreference preference = (ObjectPreference) getApplicationContext();
        complexPreferences = preference.getComplexPreference();

        ButterKnife.bind(this);
        UIHelper uiHelper = new UIHelper(this);
        progressDialog = uiHelper.getSpinnerProgressDialog("جارى تحميل الأخبار");

        presenter = new MainPresenterImp(this);
        userYear = presenter.getUserYear();
        selectedItem = userYear;

        // Kill the launcher activity, we no longer need it.
        if (LauncherActivity.instance != null) {
            try {
                LauncherActivity.instance.finish();
            } catch (Exception e) { }
        }

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(Constants.REGULAR_FONT)
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        setUpNavigation();
        setUpTabs();

        setUpFragments((int) userYear);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.e("MYLOG", "Intent Received, intent action: " + intent.getAction());

                // checking for type intent filter
                if (intent.getAction().equals(Constants.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    String token = intent.getStringExtra("token");
                    complexPreferences.putObject(Constants.USER_DEVICE_ID, token);

                    Log.e("MYLOG", "GCM registration token: " + token);

                } else if (intent.getAction().equals(Constants.SENT_TOKEN_TO_SERVER)) {
                    // gcm registration id is stored in our server's MySQL

                    Log.e("MYLOG", "GCM registration token is stored in server!");

                } else if (intent.getAction().equals(Constants.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    Toast.makeText(getApplicationContext(), "Push notification is received!", Toast.LENGTH_LONG).show();
                }
            }
        };

        if (checkPlayServices()) {
            registerGCM();
        }
    }

    // starting the service to register with GCM
    private void registerGCM() {
        Intent intent = new Intent(this, GcmIntentService.class);
        intent.putExtra("key", "register");
        startService(intent);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported. Google Play Services not installed!");
                Toast.makeText(getApplicationContext(), "This device is not supported. Google Play Services not installed!", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Constants.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Constants.PUSH_NOTIFICATION));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.first_year) {
            if (selectedItem != 1) {
                setUpFragments(1);
            } else {
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }
            selectedItem = 1;
        } else if (id == R.id.second_year) {
            if (selectedItem != 2) {
                setUpFragments(2);
            } else {
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }
            selectedItem = 2;
        } else if (id == R.id.third_year) {
            if (selectedItem != 3) {
                setUpFragments(3);
            } else {
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }
            selectedItem = 3;
        } else if (id == R.id.fourth_year) {
            if (selectedItem != 4) {
                setUpFragments(4);
            } else {
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }
            selectedItem = 4;
        } else if (id == R.id.settings) {
            setupSingleFragment("الإعدادات");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity_layout, SettingsFragment.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.contact_us) {

        } else if (id == R.id.about_us) {
            setupSingleFragment("من نحن");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity_layout, AboutUsFragment.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.open_learning) {
            setupSingleFragment("التعليم المفتوح");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity_layout, OpenLearningFragment.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.high_studies) {
            setupSingleFragment("الدراسات العليا");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity_layout, HighStudiesFragment.newInstance());
            ft.addToBackStack(null);
            ft.commit();
        }

        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    private void setupSingleFragment(String toolbarTitle){
        viewPager.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
        this.toolbarTitle.setText(toolbarTitle);
    }

    @Override
    public void setUpFragments(int yearId) {
        viewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        this.toolbarTitle.setText("أخر الأخبار");

        tabLayout.removeAllTabs();

        for (int i = 0; i < fragments.size(); i++) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove(fragments.get(i));
            trans.commit();
            manager.popBackStack();
        }
        fragments = new ArrayList<>();

        if (yearId <= 2) {
            tabLayout.addTab(tabLayout.newTab().setText("إنتساب"));
            tabLayout.addTab(tabLayout.newTab().setText("إنتظام"));

            fragments.add(NewsFragment.newInstance(yearId, 2));
            fragments.add(NewsFragment.newInstance(yearId, 1));
        } else {
            tabLayout.addTab(tabLayout.newTab().setText("إدارة"));
            tabLayout.addTab(tabLayout.newTab().setText("خارجية"));
            tabLayout.addTab(tabLayout.newTab().setText("محاسبة"));

            fragments.add(NewsFragment.newInstance(yearId, 3));
            fragments.add(NewsFragment.newInstance(yearId, 5));
            fragments.add(NewsFragment.newInstance(yearId, 4));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        changeTabsFont();


        NewsFragmentPagerAdapter fragmentPagerAdapter = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem((yearId <= 2) ? 1 : 2);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            finish();
            super.onBackPressed();
        }
    }

    private void changeTabsFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    Typeface font = Typeface.createFromAsset(getAssets(), Constants.BOLD_FONT);
                    ((TextView) tabViewChild).setTypeface(font);
                }
            }
        }
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void navigateToNextActivity() {

    }

    @Override
    public void showNetworkError() {
        Snackbar.make(coordinatorLayout, "تحقق من اتصال الإنترنت الخاص بك وحاول مرة أخرى", Snackbar.LENGTH_LONG);
    }

    private void setUpTabs() {
        // 1. Setup Tabs
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // 2. Setup ViewPager and Fragment Adapter
        viewPager = (ViewPager) findViewById(R.id.pager);

        // 3. Monitor Tab Changes
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // 4. Monitor ViewPager Changes, User Swipes
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setUpNavigation() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView logoutText = (TextView) header.findViewById(R.id.log_out);

        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logOutUser();
                startActivity(new Intent(MainActivity.this, LauncherActivity.class));
                finish();
                return;
            }
        });

        // Set the selected Item
        switch ((int) userYear) {
            case 1:
                navigationView.setCheckedItem(R.id.first_year); break;
            case 2:
                navigationView.setCheckedItem(R.id.second_year); break;
            case 3:
                navigationView.setCheckedItem(R.id.third_year); break;
            case 4:
                navigationView.setCheckedItem(R.id.fourth_year); break;
        }

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (drawer.isDrawerOpen(GravityCompat.END)) {
                        drawer.closeDrawer(GravityCompat.END);
                    } else {
                        drawer.openDrawer(GravityCompat.END);
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.clearNews();
    }
}

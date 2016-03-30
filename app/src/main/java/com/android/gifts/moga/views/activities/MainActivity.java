package com.android.gifts.moga.views.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
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

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.R;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.helpers.UIHelper;
import com.android.gifts.moga.presenter.news.NewsPresenter;
import com.android.gifts.moga.presenter.news.NewsPresenterImp;
import com.android.gifts.moga.views.adapters.TwoTypesNewsFragmentAdapter;
import com.android.gifts.moga.views.adapters.ThreeTypesNewsFragmentAdapter;

import java.util.List;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private NavigationView navigationView;

    private MaterialDialog progressDialog;

    private NewsPresenter presenter;

    private long userYear;
    long selectedItem;

    private TwoTypesNewsFragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ButterKnife.bind(this);
        UIHelper uiHelper = new UIHelper(this);
        progressDialog = uiHelper.getSpinnerProgressDialog("جارى تحميل الأخبار");

        presenter = new NewsPresenterImp(this, this);
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

        presenter.getNews(0, 30, (int) userYear, 0);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Log.e("FOF", "OLD selected item : " + selectedItem);

        if (id == R.id.first_year) {
            if (selectedItem != 1) {
                // Load Year 2 News
                Log.e("FOF", "Load 1");
            } else {
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }
            selectedItem = 1;
        } else if (id == R.id.second_year) {
            if (selectedItem != 2) {
                presenter.getNews(0, 30, 2, 0);
                Log.e("FOF", "Load 2");
            } else {
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }
            selectedItem = 2;
        } else if (id == R.id.third_year) {
            if (selectedItem != 3) {
                presenter.getNews(0, 30, 3, 0);
                Log.e("FOF", "Load 3");
            } else {
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }
            selectedItem = 3;
        } else if (id == R.id.fourth_year) {
            if (selectedItem != 4) {
                // Load Year 2 News
                Log.e("FOF", "Load 4");
            } else {
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }
            selectedItem = 4;
        }

        Log.e("FOF", "NEW selected item : " + selectedItem);
        //presenter.getNews(0, 30, (int) userYear, 0);
        drawer.closeDrawer(GravityCompat.END);
        return true;
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
                Log.e("FOF", "Tab Position" + tab.getPosition());
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
                Log.e("FOF", "Page position: " + position);
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

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

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
    public void setUpTwoTabs(List<News> entesabNews, List<News> entezamNews) {
        tabLayout.removeAllTabs();

        tabLayout.addTab(tabLayout.newTab().setText("إنتساب"));
        tabLayout.addTab(tabLayout.newTab().setText("إنتظام"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        changeTabsFont();

        fragmentAdapter = new TwoTypesNewsFragmentAdapter(getSupportFragmentManager(), entesabNews, entezamNews);

        viewPager.setAdapter(fragmentAdapter);

    }

    @Override
    public void setUpThreeTabs(List<News> khargyaNews, List<News> edaraNews, List<News> mohasbaNews) {
        //tabLayout.removeAllTabs();

        tabLayout.addTab(tabLayout.newTab().setText("خارجية"));
        tabLayout.addTab(tabLayout.newTab().setText("إدارة"));
        tabLayout.addTab(tabLayout.newTab().setText("محاسبة"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        changeTabsFont();

        viewPager.setAdapter(new ThreeTypesNewsFragmentAdapter(getSupportFragmentManager(),
                khargyaNews, edaraNews, mohasbaNews));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
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
}

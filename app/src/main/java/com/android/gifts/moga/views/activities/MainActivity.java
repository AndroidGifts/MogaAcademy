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

import com.android.gifts.moga.R;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.model.News;
import com.android.gifts.moga.views.adapters.ThreeTypesNewsFragmentAdapter;
import com.android.gifts.moga.views.adapters.TwoTypesNewsFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TwoTypesNewsFragmentAdapter fragmentAdapter;

    List<News> type1 = new ArrayList<>();
    List<News> type2 = new ArrayList<>();
    List<News> type3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ButterKnife.bind(this);

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

        type1.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        type1.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        type1.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        type1.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        type1.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        type1.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));

        type2.add(new News(1, "اد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائهنصائح قبل الإمتحان.", "4/2/2016"));
        type2.add(new News(1, "تصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد توى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        type2.add(new News(1, "حلود الثالث", "يمكنك الإن تنزيل شيتالعديد من النصائح قبل الإمتحان.", "4/2/2016"));

        type3.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        type3.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        type3.add(new News(1, "اد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائهنصائح قبل الإمتحان.", "4/2/2016"));
        type3.add(new News(1, "تصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد توى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));


        List<News> news = new ArrayList<>();
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news.add(new News(1, "حلول شيت الإقتصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائه من مكنبة موجه حيث انه يحتوى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));

        List<News> news2 = new ArrayList<>();
        news2.add(new News(1, "اد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد أو شرائهنصائح قبل الإمتحان.", "4/2/2016"));
        news2.add(new News(1, "تصاد الثالث", "يمكنك الإن تنزيل شيت الإقتصاد توى على العديد من النصائح قبل الإمتحان.", "4/2/2016"));
        news2.add(new News(1, "حلود الثالث", "يمكنك الإن تنزيل شيتالعديد من النصائح قبل الإمتحان.", "4/2/2016"));


        //setUpTwoTabs(news, news2);

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

        setUpTwoTabs(news, news2);
    }

    private void setUpNavigation() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    private void setUpTwoTabs(List<News> entesabNews, List<News> entezamNews) {
        tabLayout.removeAllTabs();

        tabLayout.addTab(tabLayout.newTab().setText("إنتساب"));
        tabLayout.addTab(tabLayout.newTab().setText("إنتظام"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        changeTabsFont();

        viewPager.setAdapter(new TwoTypesNewsFragmentAdapter(getSupportFragmentManager(), entesabNews, entezamNews));
    }

    private void setUpThreeTabs(List<News> khargyaNews, List<News> edaraNews, List<News> mohasbaNews) {
        tabLayout.removeAllTabs();

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
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            setUpThreeTabs(type1, type2, type3);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.END);
        return true;
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
}

package dulife.clps.com.dulife_version_01.main.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.about.AboutFragment;
import dulife.clps.com.dulife_version_01.image.widget.ImageFragment;
import dulife.clps.com.dulife_version_01.main.presenter.MainPresenter;
import dulife.clps.com.dulife_version_01.main.presenter.MainPresenterImpl;
import dulife.clps.com.dulife_version_01.main.view.MainView;
import dulife.clps.com.dulife_version_01.news.widget.NewsFragment;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mainPresenter;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "你想发邮件还是想试试该功能?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);
        mainPresenter = new MainPresenterImpl(this);
        switchNews();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mainPresenter.switchNavigation(item.getItemId());
                item.setChecked(true);
                drawer.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public void switchNews() {
        getSupportFragmentManager().
                beginTransaction().replace(R.id.frame_content, new NewsFragment()).commit();
        toolbar.setTitle("新闻");
    }

    @Override
    public void switchImage() {
        getSupportFragmentManager().
                beginTransaction().replace(R.id.frame_content, new ImageFragment()).commit();
        toolbar.setTitle("图片");
    }

    @Override
    public void switchAbout() {
        getSupportFragmentManager().
                beginTransaction().replace(R.id.frame_content, new AboutFragment()).commit();
        toolbar.setTitle("关于");
    }
}

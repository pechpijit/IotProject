package com.pechydev.iotproject01;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.pechydev.iotproject01.fragment.HistoryFragment;
import com.pechydev.iotproject01.fragment.TimelineFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Timeline");
        setFram(1);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.tab_timeline:
                        toolbar.setTitle("Timeline");
                        toolbar.setBackgroundColor(getResources().getColor(R.color.menu1));
                        setFram(1);
                        break;
                    case R.id.tab_history:
                        toolbar.setTitle("Histort");
                        toolbar.setBackgroundColor(getResources().getColor(R.color.menu2));
                        setFram(2);
                        break;
                    case R.id.tab_warning:
                        toolbar.setTitle("Warning");
                        toolbar.setBackgroundColor(getResources().getColor(R.color.menu3));
                        setFram(3);
                        break;
                }
            }
        });
    }

    private void setFram(int page) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment selectFragment = null;

        switch (page) {
            case 1:
                selectFragment = new TimelineFragment();
                break;
            case 2:
                selectFragment = new HistoryFragment();
                break;
            case 3:

                break;
        }

        if (selectFragment != null) {
            ft.replace(R.id.content, selectFragment);
            ft.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

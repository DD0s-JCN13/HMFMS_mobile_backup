package cathay.hospital.hmfmsmobile.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import cathay.hospital.hmfmsmobile.activity.R;
import cathay.hospital.hmfmsmobile.util.UtilTools;

public class ScanningActivity extends AppCompatActivity {

    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private boolean sysCondition = Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        findView();
        ActionBarSet();
        NavigationDrawerSet();
        BottomNavigationSet();
        menuSetChecked();
        barcodeScannerView = findViewById(R.id.zxing_scanner);

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    protected void findView(){
        toolbar = findViewById(R.id.toolbar_scanner);
        drawerLayout = findViewById(R.id.scanner_drawer);
        navigationView = findViewById(R.id.nav_view_scanner);
        bottomNavigationView = findViewById(R.id.bottom_nav_scanner);
    }

    protected void ActionBarSet(){
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    protected void menuSetChecked(){
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
    }

    protected void NavigationDrawerSet(){
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);

            int id = item.getItemId();
            switch (id){
                case R.id.nav_home:
                    UtilTools.goActivity(this,HomepageActivity.class);
                    if(sysCondition){fadeSwitchAnimation();}
                    return true;
                case R.id.nav_checklist:
                    UtilTools.goActivity(this,CheckListActivity.class);
                    if(sysCondition){fadeSwitchAnimation();}
                    return true;
                case R.id.nav_err_loc:
                    Toast.makeText(ScanningActivity.this, "Error Location direction",
                            Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.nav_del_loc:
                    Toast.makeText(ScanningActivity.this, "Delete Location direction",
                            Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });
    }

    protected void BottomNavigationSet(){
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id  = item.getItemId();

            switch (id){
                case R.id.action_homeapge:
                    UtilTools.goActivity(this, HomepageActivity.class);
                    if(sysCondition) {fadeSwitchAnimation();}
                    return true;
                case R.id.action_scanner:
                    Toast.makeText(ScanningActivity.this, "Already on this page!!",
                            Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.list_item:
                    UtilTools.goActivity(this, CheckListActivity.class);
                    if(sysCondition) {fadeSwitchAnimation();}
                    return true;
            }
            return false;
        });
    }

    protected void fadeSwitchAnimation(){
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}

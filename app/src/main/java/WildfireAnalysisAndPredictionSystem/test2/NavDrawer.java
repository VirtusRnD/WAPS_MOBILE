package WildfireAnalysisAndPredictionSystem.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class NavDrawer extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    private AccountSettingsFragment accountSettingsFragment;
    private InformationFragment informationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        accountSettingsFragment = new AccountSettingsFragment();
        informationFragment = new InformationFragment();


        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.account_settings:
                        Toast.makeText(getApplicationContext(),"f1",Toast.LENGTH_SHORT).show();
                        setFragment(accountSettingsFragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return  true;

                    case R.id.info:
                        Toast.makeText(getApplicationContext(),"f2",Toast.LENGTH_SHORT).show();
                        setFragment(informationFragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return  true;

                    case R.id.help_support:
                        Toast.makeText(getApplicationContext(),"f3",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return  true;

                    case R.id.rate_us:
                        Toast.makeText(getApplicationContext(),"f4",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return  true;

                    case R.id.share:
                        Toast.makeText(getApplicationContext(),"f5",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return  true;

                    default:
                        return false;
                }
            }
        });
    }

    private  void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_menu_content,fragment);
        transaction.commit();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

}
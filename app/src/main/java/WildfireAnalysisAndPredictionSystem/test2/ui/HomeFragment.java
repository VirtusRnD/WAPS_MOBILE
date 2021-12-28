package WildfireAnalysisAndPredictionSystem.test2.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import WildfireAnalysisAndPredictionSystem.test2.R;
import WildfireAnalysisAndPredictionSystem.test2.SettingsPageActivity;


public class HomeFragment extends Fragment {

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_menu,container,false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,  MenuInflater inflater) {
        inflater.inflate(R.menu.settings_menu,menu);
        MenuItem goto_settings = menu.findItem(R.id.settings);

        goto_settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getContext(), SettingsPageActivity.class);
                startActivity(intent);

                //TODO buraya bir tane fragment ile yandan kayan menu eklenecek.
                //TODO buradaki diğer settings buttonu değişecek.
                return false;
            }
        });
    }
}
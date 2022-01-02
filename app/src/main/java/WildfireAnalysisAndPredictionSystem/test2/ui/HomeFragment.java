package WildfireAnalysisAndPredictionSystem.test2.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import WildfireAnalysisAndPredictionSystem.test2.R;
import WildfireAnalysisAndPredictionSystem.test2.SettingsPageActivity;
import WildfireAnalysisAndPredictionSystem.test2.TempNotificationRecyclerViewAdapter;
import WildfireAnalysisAndPredictionSystem.test2.TempNotifications;

/**
 * @author hasanaliozkan
 * **/
public class HomeFragment extends Fragment {

    private View view;
    private ArrayList<TempNotifications> notifications;
    private RecyclerView recyclerView;
    private TempNotificationRecyclerViewAdapter tempNotificationRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_menu,container,false);
        setHasOptionsMenu(true);
        viewSettings();
        fillTheArray();
        tempNotificationRecyclerViewAdapter.notifyDataSetChanged();

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

                return false;
            }
        });
    }

    private void fillTheArray() {
        String firstPart = "There is a fire at ";
        String secondPart = "county and at ";
        notifications.add(new TempNotifications(firstPart+"Ula"+secondPart+"14:53"));
        notifications.add(new TempNotifications(firstPart+"Mentese"+secondPart+"16:26"));
        notifications.add(new TempNotifications(firstPart+"Marmaris"+secondPart+"11:14"));
        notifications.add(new TempNotifications(firstPart+"Yatagan"+secondPart+"17:48"));
        notifications.add(new TempNotifications(firstPart+"Kavaklıdere"+secondPart+"13:05"));
        notifications.add(new TempNotifications(firstPart+"Bodrum"+secondPart+"09:56"));
        notifications.add(new TempNotifications(firstPart+"Dalaman"+secondPart+"12:41"));

    }

    private void viewSettings() {
        recyclerView = view.findViewById(R.id.not_hist);
        notifications = new ArrayList<>();
        tempNotificationRecyclerViewAdapter = new TempNotificationRecyclerViewAdapter(notifications);
        recyclerView.setAdapter(tempNotificationRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


}
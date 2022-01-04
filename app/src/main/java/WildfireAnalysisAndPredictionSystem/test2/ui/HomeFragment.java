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
import WildfireAnalysisAndPredictionSystem.test2.TempDailyWildfires;
import WildfireAnalysisAndPredictionSystem.test2.TempDailyWildfiresRecyclerViewAdapter;
import WildfireAnalysisAndPredictionSystem.test2.TempNotificationRecyclerViewAdapter;
import WildfireAnalysisAndPredictionSystem.test2.TempNotifications;

/**
 * @author hasanaliozkan
 * **/
public class HomeFragment extends Fragment {

    private View view;
    private ArrayList<TempNotifications> notifications;
    private ArrayList<TempDailyWildfires> dailyWildfires;
    private RecyclerView recyclerViewNotifications;
    private RecyclerView recyclerViewDailyWildfires;
    private TempNotificationRecyclerViewAdapter tempNotificationRecyclerViewAdapter;
    private TempDailyWildfiresRecyclerViewAdapter tempDailyWildfiresRecyclerViewAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_menu,container,false);
        setHasOptionsMenu(true);
        viewSettingsNotifications();
        fillTheArrayNotifications();
        tempNotificationRecyclerViewAdapter.notifyDataSetChanged();

        viewSettingsDailyWildFires();
        fillTheArrayDailyWildfires();
        tempDailyWildfiresRecyclerViewAdapter.notifyDataSetChanged();
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

    private void fillTheArrayNotifications() {
        String firstPart = "There was a fire in \n";
        String secondPart = " at ";
        notifications.add(new TempNotifications(firstPart+"Ula"+secondPart+"14:53"));
        notifications.add(new TempNotifications(firstPart+"Menteşe"+secondPart+"16:26"));
        notifications.add(new TempNotifications(firstPart+"Marmaris"+secondPart+"11:14"));
        notifications.add(new TempNotifications(firstPart+"Yatağan"+secondPart+"17:48"));
        notifications.add(new TempNotifications(firstPart+"Kavaklıdere"+secondPart+"13:05"));
        notifications.add(new TempNotifications(firstPart+"Bodrum"+secondPart+"09:56"));
        notifications.add(new TempNotifications(firstPart+"Dalaman"+secondPart+"12:41"));



    }

    private void viewSettingsNotifications() {
        recyclerViewNotifications = view.findViewById(R.id.not_hist);
        notifications = new ArrayList<>();
        tempNotificationRecyclerViewAdapter = new TempNotificationRecyclerViewAdapter(notifications);
        recyclerViewNotifications.setAdapter(tempNotificationRecyclerViewAdapter);
        recyclerViewNotifications.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void viewSettingsDailyWildFires() {
        recyclerViewDailyWildfires = view.findViewById(R.id.daily_wildf);
        dailyWildfires = new ArrayList<>();
        tempDailyWildfiresRecyclerViewAdapter = new TempDailyWildfiresRecyclerViewAdapter(dailyWildfires);
        recyclerViewDailyWildfires.setAdapter(tempDailyWildfiresRecyclerViewAdapter);
        recyclerViewDailyWildfires.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void fillTheArrayDailyWildfires() {
        String firstPart = "There was a fire in \n";
        dailyWildfires.add(new TempDailyWildfires(firstPart+"Ula"));
        dailyWildfires.add(new TempDailyWildfires(firstPart+"Menteşe"));
        dailyWildfires.add(new TempDailyWildfires(firstPart+"Marmaris"));
        dailyWildfires.add(new TempDailyWildfires(firstPart+"Bodrum"));
        dailyWildfires.add(new TempDailyWildfires(firstPart+"Dalaman"));
        dailyWildfires.add(new TempDailyWildfires(firstPart+"Datça"));



    }

}
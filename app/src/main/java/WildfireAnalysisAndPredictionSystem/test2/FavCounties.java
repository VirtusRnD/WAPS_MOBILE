package WildfireAnalysisAndPredictionSystem.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
/**
 * @author hasanaliozkan
 * **/
public class FavCounties extends AppCompatActivity implements CountyRecyclerViewAdapter.OnCountyListener {
    private CountyRecyclerViewAdapter countyRecyclerViewAdapter;
    private ArrayList<County> counties;
    private ArrayList<String> countieArrayList;
    private RecyclerView recyclerView;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private String TAG = "FAV";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_counties);
        recyclerView = findViewById(R.id.firends_list);
        ActionBar actionBar = getSupportActionBar();
        setTitle("Favorite Counties");
        actionBar.setDisplayHomeAsUpEnabled(true);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        viewSettingsFav();
        countyRecyclerViewAdapter.notifyDataSetChanged();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void viewSettingsFav() {
        recyclerView = findViewById(R.id.county_list);
        counties = new ArrayList<>();
        countieArrayList = new ArrayList<>();
        fillTheArrayFav();
        countyRecyclerViewAdapter = new CountyRecyclerViewAdapter(counties, this);
        recyclerView.setAdapter(countyRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fillTheArrayFav() {


        counties.add(new County("Ula", true));
        counties.add(new County("Menteşe", true));
        counties.add(new County("Köyceğiz", true));
        counties.add(new County("Marmaris", true));
        counties.add(new County("Bodrum", true));
        counties.add(new County("Dalaman", true));
        counties.add(new County("Datça", true));
        counties.add(new County("Milas", true));
        counties.add(new County("Fethiye", true));
        counties.add(new County("Kavaklıdere", true));
        counties.add(new County("Yatağan", true));
        searchForUser();
        for (String county_name : countieArrayList) {
            Log.d(TAG, county_name);
            counties.add(new County(county_name, true));
        }

    }

    private void searchForUser() {
        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail().toString();

        Query query = db.collection("users");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult()) {
                        if (doc.getString("email").equals(email)) {
                            countieArrayList = (ArrayList<String>) doc.get("counties");
                            Log.d(TAG, countieArrayList.toString());
                            break;
                        }
                    }
                }
            }
        });

    }

    @Override
    public void onCountyClick(int position) {
        //TODO When the button clicked the star will be changed with an inverse one.
        Log.d("SEARCH PAGE", "Clicked" + position);
    }

}
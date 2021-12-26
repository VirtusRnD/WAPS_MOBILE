package WildfireAnalysisAndPredictionSystem.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class SearchPageActivity extends AppCompatActivity implements CountyRecyclerViewAdapter.OnCountyListener {

    private ArrayList<County> counties;
    private RecyclerView recyclerView;
    private CountyRecyclerViewAdapter countyRecyclerViewAdapter;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_page);


        viewSettings();
        countyRecyclerViewAdapter.notifyDataSetChanged();

        RecyclerView recyclerView = findViewById(R.id.county_list);



        ImageView article_menu_director = findViewById(R.id.menu_articles);
        article_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPageActivity.this, ArticlesPageActivity.class);
                startActivity(intent);
                finish();

            }
        });
        ImageView main_menu_director = findViewById(R.id.menu_main);
        main_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPageActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();

            }
        });
        ImageView friends_menu_director = findViewById(R.id.menu_friends);
        friends_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPageActivity.this, FriendsPageActivity.class);
                startActivity(intent);
                finish();

            }
        });
        ImageView add_wildfire_menu_director = findViewById(R.id.menu_wildfire_adding);
        add_wildfire_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPageActivity.this, WildfireAddingPageActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.county_list);
        counties = new ArrayList<>();
        fillTheArray();
        countyRecyclerViewAdapter = new CountyRecyclerViewAdapter(counties,this);
        recyclerView.setAdapter(countyRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fillTheArray() {
        counties.add(new County("Ula",true));
        counties.add(new County("Menteşe",true));
        counties.add(new County("Köyceğiz",true));
        counties.add(new County("Marmaris",true));
        counties.add(new County("Bodrum",true));
        counties.add(new County("Dalaman",true));
        counties.add(new County("Datça",true));
        counties.add(new County("Milas",true));
        counties.add(new County("Fethiye",true));
        counties.add(new County("Kavaklıdere",true));
        counties.add(new County("Yatağan",true));
    }

    @Override
    public void onCountyClick(int position) {
        //TODO When the button clicked the star will be changed with an inverse one.
        Log.d("SEARCH PAGE","Clicked" + position);
    }
}
package WildfireAnalysisAndPredictionSystem.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class FriendsPageActivity extends AppCompatActivity {
    private ArrayList<Friend> friends;
    private RecyclerView recyclerView;
    private FriendRecyclerViewAdapter friendRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_page);

        viewSettings();
        fillTheArray();
        friendRecyclerViewAdapter.notifyDataSetChanged();
        ImageView article_menu_director = findViewById(R.id.menu_articles);
        article_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FriendsPageActivity.this, ArticlesPageActivity.class);
                startActivity(intent);
            }
        });
        ImageView search_menu_director = findViewById(R.id.menu_search);
        search_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FriendsPageActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });
        ImageView main_menu_director = findViewById(R.id.menu_main);
        main_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FriendsPageActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
        ImageView add_wildfire_menu_director = findViewById(R.id.menu_wildfire_adding);
        add_wildfire_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FriendsPageActivity.this, WildfireAddingPageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fillTheArray() {


        //TODO solve the problem in the Friends adapter class then fill this code block by getting data from the firebase.
    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.friend_list);
        friends = new ArrayList<>();
        friendRecyclerViewAdapter = new FriendRecyclerViewAdapter(friends);
        recyclerView.setAdapter(friendRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
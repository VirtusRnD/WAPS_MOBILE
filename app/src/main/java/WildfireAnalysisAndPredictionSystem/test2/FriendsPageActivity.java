package WildfireAnalysisAndPredictionSystem.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FriendsPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_page);

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
}
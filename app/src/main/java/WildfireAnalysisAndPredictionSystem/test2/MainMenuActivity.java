package WildfireAnalysisAndPredictionSystem.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ImageView article_menu_director = findViewById(R.id.menu_articles);
        article_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ArticlesPageActivity.class);
                startActivity(intent);
            }
        });
        ImageView search_menu_director = findViewById(R.id.menu_search);
        search_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });
        ImageView friends_menu_director = findViewById(R.id.menu_friends);
        friends_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, FriendsPageActivity.class);
                startActivity(intent);
            }
        });
        ImageView add_wildfire_menu_director = findViewById(R.id.menu_wildfire_adding);
        add_wildfire_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, WildfireAddingPageActivity.class);
                startActivity(intent);
            }
        });
        ImageView settings_director = findViewById(R.id.button_settings);
        settings_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, SettingsPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
package WildfireAnalysisAndPredictionSystem.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ArticlesPageActivity extends AppCompatActivity {

    private ArrayList<Article> articles;
    private RecyclerView recyclerView;
    private ArticleRecyclerViewAdapter articleRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_page);

        viewSettings();
        fillTheArray();
        articleRecyclerViewAdapter.notifyDataSetChanged();

        ImageView add_wildfire_menu_director = findViewById(R.id.menu_wildfire_adding);
        add_wildfire_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesPageActivity.this, WildfireAddingPageActivity.class);
                startActivity(intent);
            }
        });
        ImageView search_menu_director = findViewById(R.id.menu_search);
        search_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesPageActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });
        ImageView friends_menu_director = findViewById(R.id.menu_friends);
        friends_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesPageActivity.this, FriendsPageActivity.class);
                startActivity(intent);
            }
        });
        ImageView main_menu_director = findViewById(R.id.menu_main);
        main_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesPageActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fillTheArray() {
        articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr"));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr"));
    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.article_list);
        articles = new ArrayList<>();
        articleRecyclerViewAdapter = new ArticleRecyclerViewAdapter(articles);
        recyclerView.setAdapter(articleRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
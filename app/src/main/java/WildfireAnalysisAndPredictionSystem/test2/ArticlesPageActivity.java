package WildfireAnalysisAndPredictionSystem.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import java.util.ArrayList;

public class ArticlesPageActivity extends AppCompatActivity implements ArticleRecyclerViewAdapter.OnArticleListener{

    private ArrayList<Article> articles;
    private RecyclerView recyclerView;
    private ArticleRecyclerViewAdapter articleRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_articles_page);
        viewSettings();

        articleRecyclerViewAdapter.notifyDataSetChanged();

        RecyclerView recyclerView = findViewById(R.id.article_list);
        



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
        articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));articles.add(new Article("Mehmet Kadri Gofralılar","Orman yangınlarını tahminleme","https://mkgofralilar.com.tr",2006));
        articles.add(new Article("Hasan Ali Özkan","Orman yangınlarını tahminleme","https://haozkan.com.tr",2004));

    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.article_list);
        articles = new ArrayList<>();
        fillTheArray();
        articleRecyclerViewAdapter = new ArticleRecyclerViewAdapter(articles,this);
        recyclerView.setAdapter(articleRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onArticleClick(int position) {
            Log.d("ARTICLE PAGE","Clicked" + position);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(this.articles.get(position).getLink()));
            startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.search_action);
        androidx.appcompat.widget.SearchView searchView = (SearchView) searchItem.getActionView();
        //TODO firstly the latest 25 or 50 article shown ins recycler-view searching actually will be done in firebase.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                articleRecyclerViewAdapter.getFilter().filter(newText);
                Log.d("ARTICLE PAGE","ONQUERYTEXTCHANGE" );
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
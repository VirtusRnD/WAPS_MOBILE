package WildfireAnalysisAndPredictionSystem.test2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ArticlesPageActivity extends AppCompatActivity implements ArticleRecyclerViewAdapter.OnArticleListener {

    private ArrayList<Article> articles;
    private RecyclerView recyclerView;
    private ArticleRecyclerViewAdapter articleRecyclerViewAdapter;
    private FirebaseFirestore db;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_articles_page);
        viewSettings();
        recyclerView = findViewById(R.id.article_list);
        articleRecyclerViewAdapter.notifyDataSetChanged();


        ImageView add_wildfire_menu_director = findViewById(R.id.menu_wildfire_adding);
        add_wildfire_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesPageActivity.this, WildfireAddingPageActivity.class);

                startActivity(intent);
                finish();
            }
        });
        ImageView search_menu_director = findViewById(R.id.menu_search);
        search_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesPageActivity.this, SearchPageActivity.class);
                startActivity(intent);
                finish();

            }
        });
        ImageView friends_menu_director = findViewById(R.id.menu_friends);
        friends_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesPageActivity.this, FriendsPageActivity.class);
                startActivity(intent);
                finish();

            }
        });
        ImageView main_menu_director = findViewById(R.id.menu_main);
        main_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlesPageActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void fillTheArray() {


        db.collection("articles").orderBy("year", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(ArticlesPageActivity.this, "There is a connection problem", Toast.LENGTH_SHORT).show();
                        }
                        articles.clear();
                        for (QueryDocumentSnapshot doc : value) {
                            articles.add(doc.toObject(Article.class));
                            articleRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                });


/*
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d("Articles", task.isSuccessful() + "");
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {

                        Log.d("Articles", documentSnapshot.getString("title"));
                        Article art = new Article(documentSnapshot.getString("authors"),
                                documentSnapshot.getString("title"),
                                documentSnapshot.getString("link"),
                                documentSnapshot.getString("year"));

                        articles.add(art);
                        Log.d("Size", articles.size() + "");

                    }
                }
            }
        });*/

    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.article_list);
        articles = new ArrayList<>();
        fillTheArray();

        Log.d("view ",articles.size()+"");
        articleRecyclerViewAdapter = new ArticleRecyclerViewAdapter(articles, this);

        recyclerView.setAdapter(articleRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onArticleClick(int position) {
        Log.d("ARTICLE PAGE", "Clicked" + position);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(this.articles.get(position).getLink()));
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
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
                Log.d("ARTICLE PAGE", "ONQUERYTEXTCHANGE");
                if (articles.isEmpty()) {
                    Toast.makeText(ArticlesPageActivity.this, "We couldn't find any related article", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
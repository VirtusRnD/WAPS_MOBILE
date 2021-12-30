package WildfireAnalysisAndPredictionSystem.test2.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import WildfireAnalysisAndPredictionSystem.test2.Article;
import WildfireAnalysisAndPredictionSystem.test2.ArticleRecyclerViewAdapter;
import WildfireAnalysisAndPredictionSystem.test2.BottomMenuActivity;
import WildfireAnalysisAndPredictionSystem.test2.R;


public class ArticleFragment extends Fragment implements ArticleRecyclerViewAdapter.OnArticleListener{


    private ArrayList<Article> articles = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArticleRecyclerViewAdapter articleRecyclerViewAdapter;
    private FirebaseFirestore db;
    private View view;
    private EditText article_search;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_article,container,false);
        db = FirebaseFirestore.getInstance();
        viewSettings();
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.article_list);
       // article_search = view.findViewById(R.id.article_search_bar);
        /*article_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(article_search.getText().toString().equals(""))
                    viewSettings();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
        return view;
    }



    private void viewSettings() {
        recyclerView = view.findViewById(R.id.article_list);
        AsyncTask loadTask = new LoadTask();
        Integer[] list=new Integer[1];
        list[0] = 0;
        loadTask.execute(list);

        Log.d("view ",articles.size()+"");
        articleRecyclerViewAdapter = new ArticleRecyclerViewAdapter(articles, this);

        recyclerView.setAdapter(articleRecyclerViewAdapter);
        articleRecyclerViewAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onArticleClick(int position) {
        Log.d("ARTICLE PAGE", "Clicked" + position);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(this.articles.get(position).getLink().replace(",//","://")));
        startActivity(intent);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.search_menu, menu);
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

                Log.d("ARTICLE PAGE", "ONQUERYTEXTCHANGE");

                    articleRecyclerViewAdapter.getFilter().filter(newText);
                    Toast.makeText(getContext(), "We couldn't find any related article", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public class LoadTask extends AsyncTask<Integer,Integer,Integer>{

        @Override
        protected Integer doInBackground(Integer... integers) {
            db.collection("articles").orderBy("year", Query.Direction.DESCENDING)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (error != null) {
                                Toast.makeText(getContext(), "There is a connection problem", Toast.LENGTH_SHORT).show();
                            }
                            articles.clear();
                            for (QueryDocumentSnapshot doc : value) {
                                articles.add(doc.toObject(Article.class));
                                articleRecyclerViewAdapter.notifyDataSetChanged();
                            }
                        }
                    });
            return null;
        }
    }


}
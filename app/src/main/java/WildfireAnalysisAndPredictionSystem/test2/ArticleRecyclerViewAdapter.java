package WildfireAnalysisAndPredictionSystem.test2;



import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * @author hasanaliozkan
 * **/
public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.MyViewHolder> implements Filterable {
    private List<Article> articles;
    private OnArticleListener onArticleListener;
    private List<Article> allArticle;

    public ArticleRecyclerViewAdapter(ArrayList<Article> articles,OnArticleListener onArticleListener) {
        this.articles = articles;
        this.onArticleListener = onArticleListener;
        this.allArticle = new ArrayList<Article>(articles);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_article,parent,false);

        return new MyViewHolder(view,onArticleListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(articles.get(position).getTitle());
        holder.authors.setText(articles.get(position).getAuthors());
        holder.date.setText(articles.get(position).getYear());


    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            Log.d("ARTICLE PAGE","F1" +articles.size() );
            List<Article> filteredArticle = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filteredArticle.addAll(allArticle);
                Log.d("SIZE","Freturn " + allArticle.size() );
                Log.d("ARTICLE PAGE","Fif  " +articles.size()  );
            }else{
                Log.d("ARTICLE PAGE","Felse" +articles.size() );
                for (Article article: allArticle) {
                    Log.d("ARTICLE PAGE","Ffor" +articles.size() );
                    if((article.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase()))
                            ||(article.getAuthors().toLowerCase().contains(charSequence.toString().toLowerCase()))
                            ||(article.getYear().toLowerCase().contains(charSequence.toString().toLowerCase()))){
                        Log.d("ARTICLE PAGE","Fforif" +articles.size() );
                        filteredArticle.add(article);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            Log.d("ARTICLE PAGE","Freturn" +filteredArticle );
            filterResults.values = filteredArticle;
            Log.d("ARTICLE PAGE","Freturn" +filterResults.values );
            Log.d("ARTICLE PAGE","Freturn" +articles.size() );
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            Log.d("ARTICLE PAGE","Pbc" +articles.size() );
            articles.clear();
            Log.d("ARTICLE PAGE","Pac" +articles.size() );
            articles.addAll((Collection<? extends Article>) filterResults.values);

            Log.d("ARTICLE PAGE","PaddAll" +articles.size() );
            notifyDataSetChanged();
            Log.d("ARTICLE PAGE","Pnotify" +articles.size() );

        }
    };

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView authors,title,date;
        OnArticleListener onArticleListener;

        public MyViewHolder(@NonNull View itemView ,OnArticleListener onArticleListener) {
            super(itemView);
            authors = itemView.findViewById(R.id.txtAuthors);
            title = itemView.findViewById(R.id.txtTitle);
            date = itemView.findViewById(R.id.txtDate);

            this.onArticleListener=onArticleListener;

            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onArticleListener.onArticleClick(getAdapterPosition());
        }
    }
    public interface OnArticleListener{
        void onArticleClick(int position);
    }
}

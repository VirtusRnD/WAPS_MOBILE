package WildfireAnalysisAndPredictionSystem.test2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<Article> articles;
    private OnArticleListener onArticleListener;
    public ArticleRecyclerViewAdapter(ArrayList<Article> articles,OnArticleListener onArticleListener) {
        this.articles = articles;
        this.onArticleListener = onArticleListener;
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


    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView authors,title;
        OnArticleListener onArticleListener;

        public MyViewHolder(@NonNull View itemView ,OnArticleListener onArticleListener) {
            super(itemView);
            authors = itemView.findViewById(R.id.txtAuthors);
            title = itemView.findViewById(R.id.txtTitle);
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

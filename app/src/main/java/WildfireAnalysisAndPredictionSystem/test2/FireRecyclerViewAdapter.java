package WildfireAnalysisAndPredictionSystem.test2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FireRecyclerViewAdapter extends RecyclerView.Adapter<FireRecyclerViewAdapter.FireViewHolder> {

    private ArrayList<Fire> fires;

    public FireRecyclerViewAdapter(ArrayList<Fire> fires) {
        this.fires = fires;
    }

    @NonNull
    @Override
    public FireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_fire, parent, false);

        return new FireViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FireViewHolder holder, int position) {
        holder.county.setText(fires.get(position).getCountyName());
        holder.date.setText(fires.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return fires.size();
    }

    public class FireViewHolder extends RecyclerView.ViewHolder {
        TextView county;
        TextView date;

        public FireViewHolder(@NonNull View itemView) {
            super(itemView);
            county = itemView.findViewById(R.id.county);
            date = itemView.findViewById(R.id.date);
        }
    }
}

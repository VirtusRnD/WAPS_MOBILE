package WildfireAnalysisAndPredictionSystem.test2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author hasanaliozkan from 48 to 105
 * **/
public class TempDailyWildfiresRecyclerViewAdapter extends RecyclerView.Adapter<TempDailyWildfiresRecyclerViewAdapter.TempDailyWildFireViewHolder> {
    ArrayList<TempDailyWildfires> dailyWildfires;

    public TempDailyWildfiresRecyclerViewAdapter(ArrayList<TempDailyWildfires> dailyWildfires) {
        this.dailyWildfires = dailyWildfires;
    }

    @NonNull
    @Override
    public TempDailyWildFireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_daily_wildfires,parent,false);

        return new TempDailyWildFireViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempDailyWildFireViewHolder holder, int position) {

        holder.sentence.setText(dailyWildfires.get(position).getSentence());
    }

    @Override
    public int getItemCount() {
        return dailyWildfires.size();
    }

    public class TempDailyWildFireViewHolder extends RecyclerView.ViewHolder{
        TextView sentence;

        public TempDailyWildFireViewHolder(@NonNull View itemView) {
            super(itemView);
            sentence = itemView.findViewById(R.id.daily_wild_fire);
        }
    }
}

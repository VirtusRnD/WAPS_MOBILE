package WildfireAnalysisAndPredictionSystem.test2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountyRecyclerViewAdapter extends RecyclerView.Adapter<CountyRecyclerViewAdapter.CountyHolder> {
    private List<County> counties;
    private CountyRecyclerViewAdapter.OnCountyListener onCountyListener;
    private List<County> allCounty;

    public CountyRecyclerViewAdapter(List<County> counties, OnCountyListener onCountyListener) {
        this.counties = counties;
        this.onCountyListener = onCountyListener;
        this.allCounty = new ArrayList<>(counties);
    }

    @NonNull
    @Override
    public CountyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_county, parent, false);

        return new CountyHolder(view, onCountyListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountyHolder holder, int position) {
        holder.countyName.setText(counties.get(position).getCountyName());
        //TODO first image icon will set by looking isFav att.

    }

    @Override
    public int getItemCount() {
        return counties.size();
    }

    public class CountyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView countyName;
        ImageButton isFav;
        OnCountyListener onCountyListener;

        public CountyHolder(@NonNull View itemView, OnCountyListener onCountyListener) {
            super(itemView);
            this.countyName = itemView.findViewById(R.id.county_name);
            this.isFav = itemView.findViewById(R.id.is_fav);
            this.onCountyListener = onCountyListener;

            isFav.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            onCountyListener.onCountyClick(getAdapterPosition());
            Log.d("Adapter","Clicked" +getAdapterPosition());
        }
    }

    public interface OnCountyListener {
        void onCountyClick(int position);
    }
}

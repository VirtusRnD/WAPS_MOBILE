package WildfireAnalysisAndPredictionSystem.test2.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import WildfireAnalysisAndPredictionSystem.test2.County;
import WildfireAnalysisAndPredictionSystem.test2.CountyRecyclerViewAdapter;
import WildfireAnalysisAndPredictionSystem.test2.R;

public class FireSearchFragment extends Fragment implements CountyRecyclerViewAdapter.OnCountyListener {

    private ArrayList<County> counties;
    private RecyclerView recyclerView;
    private CountyRecyclerViewAdapter countyRecyclerViewAdapter;
    private FirebaseFirestore firebaseFirestore;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fire_search, container, false);

        viewSettings();
        countyRecyclerViewAdapter.notifyDataSetChanged();

        RecyclerView recyclerView = view.findViewById(R.id.county_list);
        return view;
    }

    private void viewSettings() {
        recyclerView = view.findViewById(R.id.county_list);
        counties = new ArrayList<>();
        fillTheArray();
        countyRecyclerViewAdapter = new CountyRecyclerViewAdapter(counties,this);
        recyclerView.setAdapter(countyRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void fillTheArray() {
        counties.add(new County("Ula",true));
        counties.add(new County("Menteşe",true));
        counties.add(new County("Köyceğiz",true));
        counties.add(new County("Marmaris",true));
        counties.add(new County("Bodrum",true));
        counties.add(new County("Dalaman",true));
        counties.add(new County("Datça",true));
        counties.add(new County("Milas",true));
        counties.add(new County("Fethiye",true));
        counties.add(new County("Kavaklıdere",true));
        counties.add(new County("Yatağan",true));
    }

    @Override
    public void onCountyClick(int position) {
        //TODO When the button clicked the star will be changed with an inverse one.
        Log.d("SEARCH PAGE","Clicked" + position);
    }
}
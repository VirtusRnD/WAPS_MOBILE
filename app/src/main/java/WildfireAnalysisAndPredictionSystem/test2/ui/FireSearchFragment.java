package WildfireAnalysisAndPredictionSystem.test2.ui;

import WildfireAnalysisAndPredictionSystem.test2.County;
import WildfireAnalysisAndPredictionSystem.test2.CountyRecyclerViewAdapter;
import WildfireAnalysisAndPredictionSystem.test2.Fire;
import WildfireAnalysisAndPredictionSystem.test2.R;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;



public class FireSearchFragment extends Fragment implements CountyRecyclerViewAdapter.OnCountyListener {

    private ArrayList<County> counties;
    private RecyclerView recyclerView;
    private CountyRecyclerViewAdapter countyRecyclerViewAdapter;
    private FirebaseFirestore firebaseFirestore;
    private View view;
    private EditText county_name;
    private EditText date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fire_search, container, false);

        county_name = view.findViewById(R.id.search_county_input);
        date = view.findViewById(R.id.date_input);
        viewSettings();
        countyRecyclerViewAdapter.notifyDataSetChanged();

       recyclerView = view.findViewById(R.id.county_list);

       ArrayList<Fire> fires = new ArrayList<Fire>();
       firebaseFirestore = FirebaseFirestore.getInstance();
        Query query =firebaseFirestore.collection("fires");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(DocumentSnapshot doc : task.getResult()){
                        if (doc.getString("county").trim().toLowerCase().equals(county_name.getText().toString().trim().toLowerCase())){
                            Fire tempFire = new Fire();
                            tempFire.setCountyName(doc.getString("county"));
                            tempFire.setDate(doc.getDate("date"));
                            fires.add(tempFire);
                        }
                    }
                }
            }
        });
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
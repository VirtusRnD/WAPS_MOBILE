package WildfireAnalysisAndPredictionSystem.test2.ui;

import WildfireAnalysisAndPredictionSystem.test2.County;
import WildfireAnalysisAndPredictionSystem.test2.CountyRecyclerViewAdapter;
import WildfireAnalysisAndPredictionSystem.test2.Fire;
import WildfireAnalysisAndPredictionSystem.test2.FireRecyclerViewAdapter;
import WildfireAnalysisAndPredictionSystem.test2.R;
import WildfireAnalysisAndPredictionSystem.test2.SaveSharedPreference;
import WildfireAnalysisAndPredictionSystem.test2.SignInPageActivity;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;


import java.util.ArrayList;



public class FireSearchFragment extends Fragment implements CountyRecyclerViewAdapter.OnCountyListener {
    String TAG = "FIRESEARCH";
    private ArrayList<County> counties;
    private ArrayList<Fire> fires;
    private RecyclerView recyclerView;
    private RecyclerView result;
    private CountyRecyclerViewAdapter countyRecyclerViewAdapter;
    private FireRecyclerViewAdapter fireRecyclerViewAdapter;
    private FirebaseFirestore firebaseFirestore;
    private View view;
    private EditText county_name;
    private EditText date;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fire_search, container, false);
        db= FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        county_name = view.findViewById(R.id.search_county_input);
        date = view.findViewById(R.id.date_input);
        viewSettingsFav();
        countyRecyclerViewAdapter.notifyDataSetChanged();


        recyclerView = view.findViewById(R.id.county_list);


        Button search = view.findViewById(R.id.add_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSettingsSearch();
            }
        });


        return view;
    }
    @Override
    public void onCountyClick(int position) {
        //TODO When the button clicked the star will be changed with an inverse one.
        Log.d("SEARCH PAGE","Clicked" + position);

    }

    private void fillTheArraySearch() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query =firebaseFirestore.collection("fires");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(DocumentSnapshot doc : task.getResult()){
                        Log.d(TAG,doc.getString("county"));
                        if (doc.getString("county").trim().toLowerCase().equals(county_name.getText().toString().trim().toLowerCase())){
                            Log.d(TAG,doc.getString("county"));
                            Fire tempFire = new Fire();
                            tempFire.setCountyName(doc.getString("county"));
                            tempFire.setDate(doc.getDate("date"));
                            fires.add(tempFire);
                            fireRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });

    }

    private void viewSettingsSearch() {
        Log.d(TAG,"viewSeattingsSearch");
        result = view.findViewById(R.id.result_list);
        fires = new ArrayList<>();
        fillTheArraySearch();

        fireRecyclerViewAdapter = new FireRecyclerViewAdapter(fires);
        result.setAdapter(fireRecyclerViewAdapter);
        result.setLayoutManager(new LinearLayoutManager(getContext()));



    }

    private void viewSettingsFav() {
        recyclerView = view.findViewById(R.id.county_list);
        counties = new ArrayList<>();
        fillTheArrayFav();
        countyRecyclerViewAdapter = new CountyRecyclerViewAdapter(counties,this);
        recyclerView.setAdapter(countyRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void fillTheArrayFav() {
        FirebaseUser user = auth.getCurrentUser();
        Log.d(TAG,user.getEmail().toString());

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




}
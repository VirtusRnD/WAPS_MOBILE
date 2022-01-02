package WildfireAnalysisAndPredictionSystem.test2.ui;


import WildfireAnalysisAndPredictionSystem.test2.Fire;
import WildfireAnalysisAndPredictionSystem.test2.FireRecyclerViewAdapter;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;


/**
 * @author hasanaliozkan
 * **/
public class FireSearchFragment extends Fragment {
    String TAG = "FIRESEARCH";
    private ArrayList<Fire> fires;
    private RecyclerView result;
    private FireRecyclerViewAdapter fireRecyclerViewAdapter;
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

        Button search = view.findViewById(R.id.add_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSettings();
            }
        });
        return view;
    }

    private void fillTheArray() {

        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query =firebaseFirestore.collection("fires");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(DocumentSnapshot doc : task.getResult()){
                      //  Log.d(TAG,doc.getString("county"));
                        if (doc.getString("county").trim().toLowerCase().equals(county_name.getText().toString().trim().toLowerCase())){
                           // Log.d(TAG,doc.getString("county"));
                            Fire tempFire = new Fire(doc.getString("county"),
                                    doc.getString("date")+" - "+doc.getString("time").replace('-',':'));
                            fires.add(tempFire);
                            fireRecyclerViewAdapter.notifyDataSetChanged();

                        }

                    }
                }else{
                    Toast.makeText(getContext(),"There is a problem please try again",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void viewSettings() {
        Log.d(TAG,"viewSeattingsSearch");
        result = view.findViewById(R.id.result_list);
        fires = new ArrayList<>();
        fillTheArray();
        fireRecyclerViewAdapter = new FireRecyclerViewAdapter(fires);
        result.setAdapter(fireRecyclerViewAdapter);
        result.setLayoutManager(new LinearLayoutManager(getContext()));
    }




}
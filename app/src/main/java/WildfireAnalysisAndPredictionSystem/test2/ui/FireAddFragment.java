package WildfireAnalysisAndPredictionSystem.test2.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;

import WildfireAnalysisAndPredictionSystem.test2.R;

/**
 * @author hasanaliozkan
 * **/
public class FireAddFragment extends Fragment {
    private FirebaseFirestore db;
    private View view;
    private EditText date_input;
    private EditText county_input;
    private EditText time_input;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fire_add, container, false);


        county_input = view.findViewById(R.id.search_county_input);
        date_input = view.findViewById(R.id.date_input);
        time_input = view.findViewById(R.id.time_input);
        Button add_button = view.findViewById(R.id.add_button);


        db = FirebaseFirestore.getInstance();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFire();
            }
        });


        return view;
    }

    public boolean addFire() {
        final boolean isAdded[] = new boolean[1];
        isAdded[0]=false;
        db = FirebaseFirestore.getInstance();
        if (county_input.getText().toString().isEmpty()
                || date_input.getText().toString().isEmpty()
                || time_input.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please fill all places", Toast.LENGTH_SHORT).show();
            isAdded[0]=false;
        } else {
            HashMap<String, String> fire = new HashMap<>();
            fire.put("county", county_input.getText().toString());
            fire.put("date", date_input.getText().toString());
            fire.put("time", time_input.getText().toString());
            db.collection("fires").add(fire).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getContext(), "Thank you for your contributions", Toast.LENGTH_SHORT).show();

                    county_input.setText("");
                    date_input.setText("");
                    time_input.setText("");
                    isAdded[0]=true;
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Please try again!!!", Toast.LENGTH_SHORT).show();
                    isAdded[0]=false;
                }
            });


        }
        return isAdded[0];
    }
}
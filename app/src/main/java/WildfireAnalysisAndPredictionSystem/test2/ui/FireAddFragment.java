package WildfireAnalysisAndPredictionSystem.test2.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

import WildfireAnalysisAndPredictionSystem.test2.R;


public class FireAddFragment extends Fragment{
    private FirebaseFirestore db;
    private View view;
    private EditText date_input;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fire_add, container, false);


        ImageButton button_calendar = view.findViewById(R.id.button_calendar);

        EditText county_input = view.findViewById(R.id.search_county_input);
        date_input = view.findViewById(R.id.date_input);
        EditText time_input = view.findViewById(R.id.time_input);
        Button add_button = view.findViewById(R.id.add_button);


        db = FirebaseFirestore.getInstance();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "";
                if (county_input.getText().toString().isEmpty()
                        || date_input.getText().toString().isEmpty()
                        || time_input.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please fill all places", Toast.LENGTH_SHORT).show();
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
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Please try again!!!", Toast.LENGTH_SHORT).show();

                        }
                    });


                }
            }
        });


    return view;
    }
}
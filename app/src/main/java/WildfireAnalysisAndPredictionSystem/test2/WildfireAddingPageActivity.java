package WildfireAnalysisAndPredictionSystem.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.PrimitiveIterator;

public class WildfireAddingPageActivity extends AppCompatActivity {
private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wildfire_adding_page);


        EditText county_input = findViewById(R.id.search_county_input);
        EditText date_input = findViewById(R.id.date_input);
        EditText time_input = findViewById(R.id.time_input);
        Button  add_button = findViewById(R.id.add_button);

        ImageView article_menu_director = findViewById(R.id.menu_articles);
        article_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WildfireAddingPageActivity.this, ArticlesPageActivity.class);
                startActivity(intent);
                finish();

            }
        });
        ImageView search_menu_director = findViewById(R.id.menu_search);
        search_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WildfireAddingPageActivity.this, SearchPageActivity.class);
                startActivity(intent);
                finish();

            }
        });
        ImageView friends_menu_director = findViewById(R.id.menu_friends);
        friends_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WildfireAddingPageActivity.this, FriendsPageActivity.class);
                startActivity(intent);
                finish();

            }
        });
        ImageView main_menu_director = findViewById(R.id.menu_main);
        main_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WildfireAddingPageActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();

            }
        });


        db = FirebaseFirestore.getInstance();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(county_input.getText().toString().isEmpty()
                        ||date_input.getText().toString().isEmpty()
                        ||time_input.getText().toString().isEmpty()){
                    Toast.makeText(WildfireAddingPageActivity.this,"Please fill all places",Toast.LENGTH_SHORT).show();
                }else{
                    HashMap<String,String> fire = new HashMap<>();
                    fire.put("county",county_input.getText().toString());
                    fire.put("date",date_input.getText().toString());
                    fire.put("time",time_input.getText().toString());
                    db.collection("fires").add(fire).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(WildfireAddingPageActivity.this,"Thank you for your contributions",Toast.LENGTH_SHORT).show();
                            county_input.setText("");
                            date_input.setText("");
                            time_input.setText("");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(WildfireAddingPageActivity.this,"Please try again!!!",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }
}
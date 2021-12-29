package WildfireAnalysisAndPredictionSystem.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class SignInPageActivity extends AppCompatActivity {
    public String user_name;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        db = FirebaseFirestore.getInstance();

        setTitle("Sign In");

        if(SaveSharedPreference.getUserName(SignInPageActivity.this).length() == 0)
        {
            EditText username = findViewById(R.id.input_username_sing_in);
            EditText password = findViewById(R.id.password_input_sign_in);
            CheckBox remember_user_name = findViewById(R.id.remember_user_name);

            TextView sign_up_director = findViewById(R.id.signup_3);

            SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("USERNAME","");
            user_name = username.getText().toString();
            sign_up_director.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SignInPageActivity.this, SignUpPageActivity.class);
                    startActivity(intent);

                }
            });

            Button main_menu_director = findViewById(R.id.button_sign_in);
            main_menu_director.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final boolean[] exists = new boolean[1];
                    Query query = db.collection("users");
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot: task.getResult()){
                                    if (documentSnapshot.getString("username").equals(username.getText().toString())
                                            && documentSnapshot.getString("password").equals(password.getText().toString())){
                                        exists[0] = true;
                                        break;
                                    }
                                }
                            }
                        }
                    });

                    if (!exists[0]){
                        Toast.makeText(SignInPageActivity.this,"Please enter valid username and password",Toast.LENGTH_SHORT).show();
                    }

                    if (true) {
                        if (remember_user_name.isChecked()) {
                            SaveSharedPreference.setUserName(SignInPageActivity.this, name);
                        }
                        Intent intent = new Intent(SignInPageActivity.this, BottomMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
        else
        {
            Intent intent = new Intent(SignInPageActivity.this, BottomMenuActivity.class);
            startActivity(intent);
            finish();
        }



    }
}
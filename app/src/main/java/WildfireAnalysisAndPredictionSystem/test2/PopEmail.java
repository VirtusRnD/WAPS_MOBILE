package WildfireAnalysisAndPredictionSystem.test2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class PopEmail extends Activity {
    private EditText newEmail;
    private EditText confirmPassword;

    private FirebaseAuth auth ;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop_email);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.4));

        db= FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        newEmail = findViewById(R.id.newEmailInput);
        confirmPassword = findViewById(R.id.confirmPasswordInput);
        findViewById(R.id.changeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(newEmail.getText().toString())
                        ||TextUtils.isEmpty(confirmPassword.getText().toString())){
                    Toast.makeText(PopEmail.this,"Please fill all places",Toast.LENGTH_SHORT).show();

                }else{
                    Query query =  db.collection("users");
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot doc: task.getResult()){
                                    if(doc.getId().equals(user.getUid())){
                                        email = doc.getString("email");

                                        if(email.equals(newEmail.getText().toString())){
                                            Toast.makeText(PopEmail.this,"New email must be different",Toast.LENGTH_SHORT).show();
                                        }else if(!SignUpPageActivity.isValidEmail(newEmail.getText().toString())){
                                            Toast.makeText(PopEmail.this,"It is not a valid email",Toast.LENGTH_SHORT).show();
                                        }else{
                                            HashMap<String,String> addEmail= new HashMap<>();
                                            addEmail.put("email",newEmail.getText().toString());
                                            user.updateEmail(newEmail.getText().toString());
                                            db.collection("users").document(user.getUid()).set(addEmail, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(PopEmail.this,"Email successfully changed ",Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });

    }
}
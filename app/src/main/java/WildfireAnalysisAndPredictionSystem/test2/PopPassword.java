package WildfireAnalysisAndPredictionSystem.test2;

import android.app.Activity;
import android.app.DownloadManager;
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

import java.util.ArrayList;
import java.util.HashMap;

public class PopPassword extends Activity {
    private EditText new_password;
    private EditText confirm_new_password;
    private EditText old_password;
    FirebaseAuth auth ;
    FirebaseUser user;
    FirebaseFirestore db;
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop_password);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.4));



        db= FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        new_password = findViewById(R.id.newPasswordInput);
        confirm_new_password = findViewById(R.id.confirmNewPasswordInput);
        old_password = findViewById(R.id.confirmPasswordInput);
        findViewById(R.id.changeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(new_password.getText().toString())
                        ||TextUtils.isEmpty(confirm_new_password.getText().toString())
                        ||TextUtils.isEmpty(old_password.getText().toString())){
                    Toast.makeText(PopPassword.this,"Please fill all places",Toast.LENGTH_SHORT).show();
                }else{
                    Query query =  db.collection("users");
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot doc: task.getResult()){
                                    if (doc.getId().equals(user.getUid())){
                                        username = doc.getString("username");
                                        password = doc.getString("password");

                                        if (!new_password.getText().toString().equals(confirm_new_password.getText().toString())){
                                            Toast.makeText(PopPassword.this,"New Passwords should match",Toast.LENGTH_SHORT).show();
                                        }else if(!old_password.getText().toString().equals(password)){
                                            Toast.makeText(PopPassword.this,"Old password is wrong!",Toast.LENGTH_SHORT).show();

                                        }else{
                                            HashMap<String,String> addPass= new HashMap<>();
                                            addPass.put("password",new_password.getText().toString());
                                            db.collection("users").document(user.getUid()).set(addPass, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(PopPassword.this,"Password successfully changed ",Toast.LENGTH_SHORT).show();
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
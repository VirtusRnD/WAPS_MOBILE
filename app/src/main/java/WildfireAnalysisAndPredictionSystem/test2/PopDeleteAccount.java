package WildfireAnalysisAndPredictionSystem.test2;

import android.app.Activity;
import android.content.Intent;
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

public class PopDeleteAccount extends Activity {
    private FirebaseAuth auth ;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private EditText confirmPassword;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop_delete_account);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.25));

        db= FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        confirmPassword = findViewById(R.id.confirmPasswordInput);

        Button delete = findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(TextUtils.isEmpty(confirmPassword.getText().toString())){
                  Toast.makeText(PopDeleteAccount.this,"Please fill all places",Toast.LENGTH_SHORT).show();

              }else{
                  Query query =  db.collection("users");
                  query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                      @Override
                      public void onComplete(@NonNull Task<QuerySnapshot> task) {
                          if (task.isSuccessful()){
                              for(DocumentSnapshot doc: task.getResult()){
                                  if (doc.getId().equals(user.getUid())){
                                      password = doc.getString("password");

                                      if(!confirmPassword.getText().toString().equals(password)){
                                          Toast.makeText(PopDeleteAccount.this,"Password is wrong",Toast.LENGTH_SHORT).show();
                                      }else{
                                          user.delete();

                                          db.collection("users").document(user.getUid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                              @Override
                                              public void onComplete(@NonNull Task<Void> task) {
                                                  Toast.makeText(PopDeleteAccount.this,"Account successfully deleted.",Toast.LENGTH_SHORT).show();

                                                  SaveSharedPreference.clearUserName(PopDeleteAccount.this);
                                                  Intent intent = new Intent(PopDeleteAccount.this, SignInPageActivity.class);
                                                  startActivity(intent);

                                                  finishAffinity();
                                              }
                                          });


                                      }
                                  }
                              }
                          }
                      }
                  });

              }


                //TODO somehow kill settingsPage for the user not being able to sign in by back button
            }
        });

    }
}
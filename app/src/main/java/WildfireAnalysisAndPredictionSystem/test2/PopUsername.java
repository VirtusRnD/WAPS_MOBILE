package WildfireAnalysisAndPredictionSystem.test2;

import android.app.Activity;
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


public class PopUsername extends Activity {
    EditText new_user_name;
    EditText confirm_password;
    Button change_button;
    FirebaseAuth auth ;
    FirebaseUser user;
    FirebaseFirestore db;
    String username = "";
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_username);

        /** @author Mehmet Kadri Gofralılar**/
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.33));
        /** @author Mehmet Kadri Gofralılar**/

        /**
         * @author hasanaliozkan from 54 to 111
         * **/
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        new_user_name = findViewById(R.id.newEmailInput);
        confirm_password = findViewById(R.id.confirmPasswordInput);
        change_button = findViewById(R.id.changeButton);

       change_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(TextUtils.isEmpty(new_user_name.getText().toString())||TextUtils.isEmpty(confirm_password.getText().toString())){
                   Toast.makeText(PopUsername.this,"Please fill all places",Toast.LENGTH_SHORT).show();
               }else{
                   Query query = db.collection("users");

                   query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                       @Override
                       public  void onComplete(@NonNull Task<QuerySnapshot> task) {
                           if(task.isSuccessful()){
                               for (DocumentSnapshot doc : task.getResult()){
                                    if(doc.getId().equals(user.getUid())){
                                        username = doc.getString("username");
                                        password = doc.getString("password");

                                        if(new_user_name.getText().toString().equals(username)){
                                            Toast.makeText(PopUsername.this,"New name must be different",Toast.LENGTH_SHORT).show();
                                            new_user_name.setText("");
                                        }
                                        else if (!(confirm_password.getText().toString().equals(password))){
                                            Toast.makeText(PopUsername.this,"Password is wrong",Toast.LENGTH_SHORT).show();
                                            confirm_password.setText("");
                                        }else{
                                            HashMap<String,String> addUsername = new HashMap<>();
                                            addUsername.put("username",new_user_name.getText().toString());
                                            db.collection("users").document(user.getUid()).set(addUsername, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(PopUsername.this,"Username successfully changed ",Toast.LENGTH_SHORT).show();
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

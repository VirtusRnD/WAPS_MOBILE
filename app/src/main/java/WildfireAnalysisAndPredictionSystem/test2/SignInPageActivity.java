package WildfireAnalysisAndPredictionSystem.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import WildfireAnalysisAndPredictionSystem.test2.ui.HomeFragment;

public class SignInPageActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        db = FirebaseFirestore.getInstance();
        FirebaseAuth auth;

        setTitle("Sign In");

        if(SaveSharedPreference.getUserName(SignInPageActivity.this).length() == 0)
        {
            EditText username = findViewById(R.id.input_username_sing_in);
            EditText password = findViewById(R.id.password_input_sign_in);
            CheckBox remember_user_name = findViewById(R.id.remember_user_name);
            auth=FirebaseAuth.getInstance();
            TextView sign_up_director = findViewById(R.id.signup_3);

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
                    String str_username = username.getText().toString();
                    String str_password = password.getText().toString();
                    if (TextUtils.isEmpty(str_username)||TextUtils.isEmpty(str_password)){
                        Toast.makeText(SignInPageActivity.this,"Please fill all places",Toast.LENGTH_SHORT).show();
                    }else{
                        auth.signInWithEmailAndPassword(str_username,str_password).addOnCompleteListener(SignInPageActivity.this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    if (remember_user_name.isChecked()) {
                                        SaveSharedPreference.setUserName(SignInPageActivity.this, "true");
                                    }
                                    Intent intent = new Intent(SignInPageActivity.this, BottomMenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(SignInPageActivity.this,"Please try again",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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
package WildfireAnalysisAndPredictionSystem.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPageActivity extends AppCompatActivity {
    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
    FirebaseFirestore db;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        ActionBar actionBar = getSupportActionBar();
        setTitle("Sign Up");
        actionBar.setDisplayHomeAsUpEnabled(true);

        EditText userName = findViewById(R.id.input_username);
        EditText e_mail = findViewById(R.id.input_email);
        EditText password = findViewById(R.id.password_input);
        EditText confirm_password = findViewById(R.id.password_confirm_input);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        Log.d("Ins", db.toString());



        TextView guide_director = findViewById(R.id.signup_3);
        guide_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpPageActivity.this, GuidePageActivity.class);
                startActivity(intent);
            }
        });

        Button sign_in_director = findViewById(R.id.button_sign_up);
        sign_in_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence inputStr = e_mail.getText().toString();
                Matcher matcher = pattern.matcher(inputStr);
                dialog = new ProgressDialog(SignUpPageActivity.this);
                dialog.setMessage("Please wait...");
                dialog.show();
                String str_username = userName.getText().toString();
                String str_password = password.getText().toString();
                String str_email = e_mail.getText().toString();
                String str_confirm_password = confirm_password.getText().toString();
                if (TextUtils.isEmpty(str_username)||TextUtils.isEmpty(str_password)
                        ||TextUtils.isEmpty(str_email)||TextUtils.isEmpty(str_confirm_password)){
                    Toast.makeText(SignUpPageActivity.this, "Please fill all places", Toast.LENGTH_SHORT).show();
                }else if (userName.getText().toString().length() < 8) {
                        Toast.makeText(SignUpPageActivity.this, "Username should contain 8 character", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!matcher.matches()) {
                        Toast.makeText(SignUpPageActivity.this, "Please enter a valid e-mail", Toast.LENGTH_SHORT).show();
                        return;

                    } else if (password.getText().toString().length() < 8) {
                        Toast.makeText(SignUpPageActivity.this, "Password should contain 8 character", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!(password.getText().toString().equals(confirm_password.getText().toString()))) {
                        Toast.makeText(SignUpPageActivity.this, "Passwords should match", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        signup(str_username,str_password,str_email);

                }


            }
        });
    }
    private void signup(String username,String password,String email){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(SignUpPageActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            User addingUser = new User(username,password,email,new ArrayList<>(),new ArrayList<>());

                           db.collection("users").document(auth.getUid()).set(addingUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()){
                                       dialog.dismiss();
                                       Toast.makeText(SignUpPageActivity.this,"Welcome to the WAPS",Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(SignUpPageActivity.this,SignInPageActivity.class);
                                       startActivity(intent);
                                   }
                               }
                           });
                        }
                        else{
                            dialog.dismiss();
                            Toast.makeText(SignUpPageActivity.this,"There is a problem try again",Toast.LENGTH_SHORT).show();

                        }

                    }
                });



    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
package WildfireAnalysisAndPredictionSystem.test2;

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

import com.google.firebase.firestore.FirebaseFirestore;

public class SignInPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        FirebaseFirestore db = FirebaseFirestore.getInstance();



        EditText username = findViewById(R.id.input_username_sing_in);
        EditText password = findViewById(R.id.password_input_sign_in);
        CheckBox remember_user_name = findViewById(R.id.remember_user_name);

        TextView sign_up_director = findViewById(R.id.signup_3);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("USERNAME","");
        username.setText(name);

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

                //TODO checking username and password from the firebase.




                if (remember_user_name.isChecked()){
                    SharedPreferences saveNameSharedPreferences = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = saveNameSharedPreferences.edit();
                    editor.putString("USERNAME",username.getText().toString());
                    editor.commit();
                }
                Intent intent = new Intent(SignInPageActivity.this, BottomMenuActivity.class);
                startActivity(intent);
                finish();



            }
        });
    }
}
package WildfireAnalysisAndPredictionSystem.test2;

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

public class SignInPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        setTitle("Sign In");

        if(SaveSharedPreference.getUserName(SignInPageActivity.this).length() == 0)
        {
            EditText username = findViewById(R.id.input_username_sing_in);
            EditText password = findViewById(R.id.password_input_sign_in);
            CheckBox remember_user_name = findViewById(R.id.remember_user_name);

            TextView sign_up_director = findViewById(R.id.signup_3);

            SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("USERNAME","");

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
package WildfireAnalysisAndPredictionSystem.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText username = findViewById(R.id.input_username_sing_in);
        EditText password = findViewById(R.id.password_input_sign_in);
        CheckBox remember_user_name = findViewById(R.id.remember_user_name);

        TextView sign_up_director = findViewById(R.id.signup_3);
        sign_up_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpPageActivity.class);
                startActivity(intent);
                finish();

            }
        });

        Button main_menu_director = findViewById(R.id.button_sign_in);
        main_menu_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO checking username and password from the firebase.

                Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();



            }
        });
    }
}
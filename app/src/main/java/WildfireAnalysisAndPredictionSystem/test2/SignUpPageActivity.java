package WildfireAnalysisAndPredictionSystem.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUpPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

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
                Intent intent = new Intent(SignUpPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
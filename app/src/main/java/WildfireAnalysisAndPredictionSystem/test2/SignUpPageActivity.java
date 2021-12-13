package WildfireAnalysisAndPredictionSystem.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPageActivity extends AppCompatActivity {
    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        EditText userName = findViewById(R.id.input_username);
        EditText e_mail = findViewById(R.id.input_email);
        EditText password = findViewById(R.id.password_input);
        EditText confirm_password = findViewById(R.id.password_confirm_input);


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
                //TODO when the button clicked username, mail, password will be writing on firebase.
                char[] digits = new char[10];
                for (int i = 0; i < 10; i++) {
                    digits[i] = (char) i;
                }
                if(userName.getText().toString().isEmpty()
                        ||e_mail.getText().toString().isEmpty()
                        ||password.getText().toString().isEmpty()
                        ||confirm_password.getText().toString().isEmpty()){
                    Toast.makeText(SignUpPageActivity.this,"Please fill all places",Toast.LENGTH_SHORT).show();
                    return;

                }else{
                    if(userName.getText().toString().length()<8){
                        Toast.makeText(SignUpPageActivity.this,"Username should contain 8 character",Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!matcher.matches()){
                          Toast.makeText(SignUpPageActivity.this,"Please enter a valid e-mail",Toast.LENGTH_SHORT).show();
                        return;

                    }else if(password.getText().toString().length()<8){
                        Toast.makeText(SignUpPageActivity.this,"Password should contain 8 character",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if(!(password.getText().toString().equals(confirm_password.getText().toString()))){
                        Toast.makeText(SignUpPageActivity.this,"Passwords should match",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }





                Intent intent = new Intent(SignUpPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
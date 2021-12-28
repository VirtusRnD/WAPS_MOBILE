package WildfireAnalysisAndPredictionSystem.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        TextView change_username = findViewById(R.id.settings_username);
        change_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsPageActivity.this, PopUsername.class);
                startActivity(intent);
            }
        });

        TextView change_email = findViewById(R.id.settings_email);
        change_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsPageActivity.this, PopEmail.class);
                startActivity(intent);
            }
        });

        TextView change_password = findViewById(R.id.settings_password);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsPageActivity.this, PopPassword.class);
                startActivity(intent);
            }
        });


        TextView textView = findViewById(R.id.radius);
        SeekBar radius_bar = findViewById(R.id.radiusBar);
        radius_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView.setText(i+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });




        TextView info = findViewById(R.id.settings_info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsPageActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });


        TextView go_to_guide = findViewById(R.id.guide_director);
        go_to_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsPageActivity.this, GuidePageActivity.class);
                startActivity(intent);
            }
        });


        Button log_out_director = findViewById(R.id.button_log_out);
        log_out_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsPageActivity.this, SignInPageActivity.class);
                startActivity(intent);
            }
        });


        Button delete_account = findViewById(R.id.button_delete_account);
        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO ask for user's password, delete account, go to sign in page

                Intent intent = new Intent(SettingsPageActivity.this, PopDeleteAccount.class);
                startActivity(intent);

            }
        });
    }
}
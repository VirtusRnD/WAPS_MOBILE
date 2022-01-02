package WildfireAnalysisAndPredictionSystem.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class PopDeleteAccount extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop_delete_account);
        /** @author Mehmet Kadri Gofralılar**/
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.25));
        /** @author Mehmet Kadri Gofralılar**/
        //TODO add checking condition

        Button delete = findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreference.clearUserName(PopDeleteAccount.this);
                Intent intent = new Intent(PopDeleteAccount.this, SignInPageActivity.class);
                startActivity(intent);
                finishAffinity();
                //TODO somehow kill settingsPage for the user not being able to sign in by back button
            }
        });

    }
}
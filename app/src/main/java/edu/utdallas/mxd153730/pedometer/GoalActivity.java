package edu.utdallas.mxd153730.pedometer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
    }

    public void sendMessage(View mainView)
    {
        // Create an intent to tell the stats view to open
        Intent intent = new Intent(this, MainActivity.class);

        // Bring up the stats view
        startActivity(intent);

    }
}

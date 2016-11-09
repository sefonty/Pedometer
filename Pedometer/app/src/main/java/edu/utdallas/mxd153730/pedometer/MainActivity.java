package edu.utdallas.mxd153730.pedometer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View detailsView)
    {
        // Create an intent to tell the stats view to open
        Intent intent = new Intent(this, StatsActivity.class);

        // Bring up the stats view
        startActivity(intent);

    }
}

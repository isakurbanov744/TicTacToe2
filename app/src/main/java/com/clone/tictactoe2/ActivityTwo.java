package com.clone.tictactoe2;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ActivityTwo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Button button = (Button) findViewById(R.id.buttonGame1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
    });
}

    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

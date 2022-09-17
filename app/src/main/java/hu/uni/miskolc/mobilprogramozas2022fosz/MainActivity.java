package hu.uni.miskolc.mobilprogramozas2022fosz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button kuldesGomb;
    EditText iranyitoszamBevitel;
    EditText varosBevitel;
    EditText utcaBevitel;
    EditText hazszamBevitel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iranyitoszamBevitel = findViewById(R.id.iranyitoszamBevitel);
        varosBevitel = findViewById(R.id.varosBevitel);
        utcaBevitel = findViewById(R.id.utcaBevitel);
        hazszamBevitel = findViewById(R.id.hazszamBevitel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        kuldesGomb = findViewById(R.id.kuldesGomb);
        kuldesGomb.setOnClickListener(view -> {
            //TODO
        });
    }
}
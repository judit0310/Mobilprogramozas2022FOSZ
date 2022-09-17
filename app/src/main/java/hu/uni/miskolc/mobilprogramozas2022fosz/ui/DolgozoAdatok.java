package hu.uni.miskolc.mobilprogramozas2022fosz.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import hu.uni.miskolc.mobilprogramozas2022fosz.R;
import hu.uni.miskolc.mobilprogramozas2022fosz.service.Dolgozo;

public class DolgozoAdatok extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolgozo_adatok);
        Intent intent = getIntent();
        Dolgozo dolgozo = (Dolgozo) intent.getSerializableExtra("valasztott");
        Toast.makeText(this, "A választott dolgozó: "+dolgozo, Toast.LENGTH_LONG).show();
    }
}
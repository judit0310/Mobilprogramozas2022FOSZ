package hu.uni.miskolc.mobilprogramozas2022fosz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import hu.uni.miskolc.mobilprogramozas2022fosz.service.Dolgozo;
import hu.uni.miskolc.mobilprogramozas2022fosz.service.DolgozoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

                int result = this.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                if (result != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);
                }
    }

    @Override
    protected void onStart() {
        super.onStart();
        kuldesGomb = findViewById(R.id.kuldesGomb);
        kuldesGomb.setOnClickListener(view -> {
            ViewGroup layout = findViewById(R.id.alap);
            if(kivanToltve(layout)) {
               Intent intent = new Intent(MainActivity.this, CimKiir.class);
               Cim cim = new Cim();
               cim.setIranyitoszam(iranyitoszamBevitel.getText().toString());
               cim.setVaros(varosBevitel.getText().toString());
               cim.setUtca(utcaBevitel.getText().toString());
               cim.setHazszam(hazszamBevitel.getText().toString());
               intent.putExtra("cim",cim);
               startActivity(intent);
            } });

        Button adatGomb = findViewById(R.id.adatGomb);
        adatGomb.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Adatok.class);
            startActivity(intent);
        });


    }

    private boolean kivanToltve(ViewGroup viewGroup) {
        boolean result = true;
        int count = viewGroup.getChildCount();
        for (int i = 0; i< count; i++){
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup){
                kivanToltve((ViewGroup) view);
            }
            else if(view instanceof EditText){
                EditText editText = (EditText) view;
                String ertek = editText.getText().toString();
                if (ertek.trim().isEmpty()){
                    result = false;
                    editText.setError("Kötelező kitölteni");
                }
            }

        }
        return result;
    }
}
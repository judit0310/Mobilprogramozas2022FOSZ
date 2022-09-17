package hu.uni.miskolc.mobilprogramozas2022fosz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

import hu.uni.miskolc.mobilprogramozas2022fosz.databinding.ActivityCimKiirBinding;

public class CimKiir extends AppCompatActivity {

    private ActivityCimKiirBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCimKiirBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_cim_kiir);
        Intent intent = getIntent();
        Cim cim = (Cim) intent.getSerializableExtra("cim");
        binding.iranyitoszamKiir.setText(cim.getIranyitoszam());
        binding.varosKiir.setText(cim.getVaros());
        binding.utcaKiir.setText(cim.getUtca());
        binding.hazszamKiir.setText(cim.getHazszam());

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    @Override
    protected void onStart() {
        super.onStart();

        ActivityResultLauncher<Intent> kamerakep = registerForActivityResult
                (new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        if (result.getData().getExtras() != null) {
                            Bitmap img = (Bitmap) result.getData().getExtras().get("data");
                            binding.tableLayout.setBackground(new BitmapDrawable(getResources(), img));
                        }
                     else {
                            SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
                            String uri = prefs.getString("uri", null);
                            if (uri != null) {
                                Uri uriform = Uri.fromFile(new File(uri));
                                Bitmap img = null;
                                try {
                                    img = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriform);
                                    binding.tableLayout.setBackground(new BitmapDrawable(getResources(), img));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                  }

                        }
                    }
                });

        binding.kameraGomb.setOnClickListener(view ->

        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            final File storage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            final Uri uri = Uri.fromFile(new File(storage, System.currentTimeMillis() + ".jpg"));
            SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
            prefs.edit().putString("uri", uri.getPath()).apply();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            kamerakep.launch(intent);
        });
    }
}
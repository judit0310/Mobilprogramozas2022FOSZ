package hu.uni.miskolc.mobilprogramozas2022fosz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import hu.uni.miskolc.mobilprogramozas2022fosz.service.Dolgozo;
import hu.uni.miskolc.mobilprogramozas2022fosz.ui.DolgozoAdapter;
import hu.uni.miskolc.mobilprogramozas2022fosz.ui.DolgozoAdatok;
import hu.uni.miskolc.mobilprogramozas2022fosz.ui.DolgozoViewHolder;
import hu.uni.miskolc.mobilprogramozas2022fosz.ui.DolgozoViewModel;

public class Adatok extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adatok);
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        DolgozoViewModel vm = new ViewModelProvider(this).get(DolgozoViewModel.class);

        DolgozoAdapter adapter = new DolgozoAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        vm.getDolgozok().observe(this, dolgozos -> {
            adapter.setDolgozok(dolgozos);
            adapter.setListener((position, v) -> {
                Dolgozo dolgozo = dolgozos.get(position);
                System.out.println("A következő dolgozóra kattintottam: "+ dolgozo);
                Intent intent = new Intent(Adatok.this, DolgozoAdatok.class);
                intent.putExtra("valasztott",dolgozo);
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        });
    }
}
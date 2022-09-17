package hu.uni.miskolc.mobilprogramozas2022fosz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import hu.uni.miskolc.mobilprogramozas2022fosz.ui.DolgozoAdapter;
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
            recyclerView.setAdapter(adapter);
        });
    }
}
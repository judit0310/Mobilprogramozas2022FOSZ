package hu.uni.miskolc.mobilprogramozas2022fosz.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.uni.miskolc.mobilprogramozas2022fosz.R;
import hu.uni.miskolc.mobilprogramozas2022fosz.service.Dolgozo;

public class DolgozoAdapter extends RecyclerView.Adapter<DolgozoViewHolder> {

    private List<Dolgozo> dolgozok;

    private DolgozoKivalasztListener listener;



    public DolgozoAdapter(List<Dolgozo> dolgozok) {
        this.dolgozok = dolgozok;
    }

    public void setDolgozok(List<Dolgozo> dolgozok) {
        this.dolgozok = dolgozok;
    }

    public void setListener(DolgozoKivalasztListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DolgozoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.dolgozo_sor, parent, false);
        DolgozoViewHolder vh = new DolgozoViewHolder(layout, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DolgozoViewHolder holder, int position) {
        Dolgozo d = dolgozok.get(position);
        holder.id.setText(String.valueOf(d.getId()).trim());
        holder.keresztNev.setText(d.getKeresztNev().trim());
        holder.vezetekNev.setText(d.getVezetekNev().trim());

    }
    @Override
    public int getItemCount() {
        return dolgozok.size();
    }

}

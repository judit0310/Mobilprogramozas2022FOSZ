package hu.uni.miskolc.mobilprogramozas2022fosz.ui;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import hu.uni.miskolc.mobilprogramozas2022fosz.R;

public class DolgozoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView id;
    protected TextView keresztNev;
    protected TextView vezetekNev;

    protected DolgozoKivalasztListener listener;

    public DolgozoViewHolder(View v, DolgozoKivalasztListener listener){
        super(v);
        this.id = v.findViewById(R.id.idMezo);
        this.keresztNev = v.findViewById(R.id.keresztnevMezo);
        this.vezetekNev = v.findViewById(R.id.vezeteknevMezo);
        v.setOnClickListener(this);
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onDolgozoClick(getAdapterPosition(), view);
    }
}

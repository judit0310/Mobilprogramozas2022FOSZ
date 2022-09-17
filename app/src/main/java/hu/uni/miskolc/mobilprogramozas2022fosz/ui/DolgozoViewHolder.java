package hu.uni.miskolc.mobilprogramozas2022fosz.ui;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import hu.uni.miskolc.mobilprogramozas2022fosz.R;

public class DolgozoViewHolder extends RecyclerView.ViewHolder {
    protected TextView id;
    protected TextView keresztNev;
    protected TextView vezetekNev;

    public DolgozoViewHolder(View v){
        super(v);
        this.id = v.findViewById(R.id.idMezo);
        this.keresztNev = v.findViewById(R.id.keresztnevMezo);
        this.vezetekNev = v.findViewById(R.id.vezeteknevMezo);
    }

}

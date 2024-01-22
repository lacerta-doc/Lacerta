package one.nem.lacerta.feature.library;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import one.nem.lacerta.model.VcsRevModel;

public class RevAdapter extends RecyclerView.Adapter<RevAdapter.RevViewHolder>{

    private ArrayList<VcsRevModel> revModels;

    public RevAdapter(ArrayList<VcsRevModel> revModels) {
        this.revModels = revModels;
    }

    public RevAdapter() {
    }

    public void setRevModels(ArrayList<VcsRevModel> revModels) {
        this.revModels = revModels;
    }


    @NonNull
    @Override
    public RevAdapter.RevViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RevAdapter.RevViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RevViewHolder extends RecyclerView.ViewHolder {

        public RevViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

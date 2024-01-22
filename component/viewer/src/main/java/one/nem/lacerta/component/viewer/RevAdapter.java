package one.nem.lacerta.component.viewer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import one.nem.lacerta.model.VcsRevModel;
import one.nem.lacerta.utils.FeatureSwitch;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewer_rev_list_item, parent, false);
        return new RevAdapter.RevViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RevAdapter.RevViewHolder holder, int position) {
        VcsRevModel revModel = revModels.get(position);
        holder.title.setText(revModel.getCommitMessage());
        if (FeatureSwitch.Vcs.disableBranchDisplay) {
//            holder.detail.setText(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(revModel.getCreatedAt().toInstant()));
            holder.detail.setText("DateTimePlaceholder");
        } else {
            //        holder.detail.setText(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(revModel.getCreatedAt().toInstant()) + " " + revModel.getBranchName());
            holder.detail.setText("DateTimePlaceholder" + " " + revModel.getBranchName());
        }
        holder.revId.setText("RevID: " + revModel.getId());

    }

    @Override
    public int getItemCount() {
        if (revModels == null) {
            return 0;
        } else {
            return revModels.size();
        }
    }

    class RevViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        TextView detail;

        TextView revId;

        public RevViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.rev_item_title);
            detail = itemView.findViewById(R.id.rev_item_detail);
            revId = itemView.findViewById(R.id.rev_item_id);
        }
    }
}
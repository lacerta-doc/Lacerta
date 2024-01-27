package one.nem.lacerta.component.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import one.nem.lacerta.model.VcsRevModel;

public class SelectRevDialogItemAdapter extends RecyclerView.Adapter<SelectRevDialogItemAdapter.SelectRevDialogItemViewHolder>{

    ArrayList<VcsRevModel> revList;

    LacertaSelectRevDialogInternalListener listener;

    public SelectRevDialogItemAdapter(LacertaSelectRevDialogInternalListener listener) {
        this.listener = listener;
    }

    public void setRevList(ArrayList<VcsRevModel> revList) {
        this.revList = revList;
    }

    @NonNull
    @Override
    public SelectRevDialogItemAdapter.SelectRevDialogItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_rev_list_item, parent, false);
        return new SelectRevDialogItemAdapter.SelectRevDialogItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectRevDialogItemAdapter.SelectRevDialogItemViewHolder holder, int position) {
        VcsRevModel rev = revList.get(position);
        holder.title.setText(rev.getCommitMessage());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        holder.description.setText(simpleDateFormat.format(rev.getCreatedAt()));
        holder.revId.setText("RevID: " + rev.getId());

        holder.itemView.setOnClickListener(v -> listener.onItemSelected(rev.getId()));
    }

    @Override
    public int getItemCount() {
        return this.revList == null ? 0 : this.revList.size();
    }

    public class SelectRevDialogItemViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView description;
    TextView revId;

        public SelectRevDialogItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rev_item_title);
            description = itemView.findViewById(R.id.rev_item_detail);
            revId = itemView.findViewById(R.id.rev_item_id);
        }
    }
}

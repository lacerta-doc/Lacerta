package one.nem.lacerta.shared.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectDirDialogItemAdapter extends RecyclerView.Adapter<SelectDirDialogItemAdapter.ViewHolder> {

        @Override
        public SelectDirDialogItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_list_item, parent, false);
            return new SelectDirDialogItemAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SelectDirDialogItemAdapter.ViewHolder holder, int position) {
            holder.title.setText("title");
            holder.description.setText("description");
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView title;
            TextView description;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.item_title);
                description = itemView.findViewById(R.id.item_description);

            }
        }
}

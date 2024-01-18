package one.nem.lacerta.feature.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.document.DocumentMeta;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {

    ArrayList<ListItem> listItems;

    public MyAdapter(ArrayList<ListItem> ListItem) {
//        this.listItem = listItem;
    }

    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_document, parent, false);
        return new MyAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {


        holder.title.setText(listItems.get(position).getTitle());
        holder.description.setText(listItems.get(position).getDescription());








    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        TextView description;

        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.debug_menu_item_title);
            description = itemView.findViewById(R.id.debug_menu_item_description);


        }
    }
}


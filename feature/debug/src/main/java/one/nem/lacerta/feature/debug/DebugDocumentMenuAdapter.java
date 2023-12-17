package one.nem.lacerta.feature.debug;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import one.nem.lacerta.feature.debug.common.model.SettingMenuItem;

public class DebugDocumentMenuAdapter extends RecyclerView.Adapter<DebugDocumentMenuAdapter.ViewHolder> {

    private List<SettingMenuItem> menuItems;

    public DebugDocumentMenuAdapter(List<SettingMenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SettingMenuItem menuItem = menuItems.get(position);
        holder.itemView.setOnClickListener( v -> {
            Navigation.findNavController(v).navigate(menuItem.getDestinationId());
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

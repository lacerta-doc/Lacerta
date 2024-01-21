package one.nem.lacerta.component.viewer;

import android.graphics.Bitmap;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import one.nem.lacerta.model.document.page.Page;

public class ViewerBodyAdapter extends RecyclerView.Adapter<ViewerBodyAdapter.ViewHolder>{

    ArrayList<Page> pages;

    ItemClickListener listener;

    public ViewerBodyAdapter(ArrayList<Page> pages, ItemClickListener listener){
        this.pages = pages;
        this.listener = listener;
    }

    public ViewerBodyAdapter(ItemClickListener listener){
        this.pages = new ArrayList<>();
        this.listener = listener;
    }

    public void setPages(ArrayList<Page> pages){
        this.pages = pages;
    }

    @NonNull
    @Override
    public ViewerBodyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.viewer_body_list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewerBodyAdapter.ViewHolder holder, int position) {
        Bitmap bitmap = pages.get(position).getBitmap();
        holder.image.setImageBitmap(bitmap);
        holder.itemView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}

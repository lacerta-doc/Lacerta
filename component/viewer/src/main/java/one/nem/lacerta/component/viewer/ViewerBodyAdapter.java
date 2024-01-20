package one.nem.lacerta.component.viewer;

import android.graphics.Bitmap;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewerBodyAdapter extends RecyclerView.Adapter<ViewerBodyAdapter.ViewHolder>{

    ArrayList<Bitmap> images;

    public ViewerBodyAdapter(ArrayList<Bitmap> images){
        this.images = images;
    }

    @NonNull
    @Override
    public ViewerBodyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.viewer_body_list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewerBodyAdapter.ViewHolder holder, int position) {
        holder.image.setImageBitmap(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}

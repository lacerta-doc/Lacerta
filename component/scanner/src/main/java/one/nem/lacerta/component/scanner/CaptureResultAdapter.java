package one.nem.lacerta.component.scanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CaptureResultAdapter extends RecyclerView.Adapter<CaptureResultAdapter.ViewHolder> {
    private final ArrayList<CapturedData> results;

    public CaptureResultAdapter(ArrayList<CapturedData> results) {
        this.results = results;
    }

    @Override
    public CaptureResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_scanner_component_manager_stub, parent, false);
        return new CaptureResultAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CaptureResultAdapter.ViewHolder holder, int position) {
        CapturedData result = results.get(position);
        holder.textViewPath.setText(result.getPath());
        holder.textViewResolutionHeight.setText(result.getResolutionHeight());
        holder.textViewResolutionWidth.setText(result.getResolutionWidth());
        holder.imageView.setImageBitmap(result.getBitmap());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewPath;
        public TextView textViewResolutionHeight;
        public TextView textViewResolutionWidth;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            textViewPath = view.findViewById(R.id.textViewPath);
            textViewResolutionHeight = view.findViewById(R.id.textViewResHeight);
            textViewResolutionWidth = view.findViewById(R.id.textViewResWidth);
            imageView = view.findViewById(R.id.imageViewResult);
        }
    }
}
package one.nem.lacerta.feature.library;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import one.nem.lacerta.model.document.tag.DocumentTag;

public class TagAdapter extends BaseAdapter {

    private ArrayList<DocumentTag> tagList;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Chip chip;
        if (convertView == null) {
            chip = new Chip(parent.getContext());
        }
        else {
            chip = (Chip) convertView;
        }
        chip.setText(tagList.get(position).getName());
        return chip;
    }
}

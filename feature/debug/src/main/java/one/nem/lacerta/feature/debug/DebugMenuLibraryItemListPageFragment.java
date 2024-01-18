package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.annotation.AnimatorRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.LacertaLibrary;

import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuLibraryItemListPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class DebugMenuLibraryItemListPageFragment extends Fragment {

    @Inject
    LacertaLibrary lacertaLibrary;

    public DebugMenuLibraryItemListPageFragment() {
        // Required empty public constructor
    }

    public static DebugMenuLibraryItemListPageFragment newInstance() {
        DebugMenuLibraryItemListPageFragment fragment = new DebugMenuLibraryItemListPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_debug_menu_library_item_list_page, container, false);

        ArrayList<ListItem> listItems = lacertaLibrary.getRecentDocument(10);
        for (ListItem listItem : listItems) {
            System.out.println(listItem.getTitle());
        }

        RecyclerView recyclerView = view.findViewById(R.id.item_recycler_view);
        recyclerView.setAdapter(new ItemAdapter(listItems));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
        private ArrayList<ListItem> listItems;

        public ItemAdapter(ArrayList<ListItem> listItems) {
            this.listItems = listItems;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_debug_menu_document, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            ListItem listItem = listItems.get(position);
            holder.document_list_item_title.setText(listItem.getTitle());
            holder.document_list_item_description.setText(listItem.getDescription());
            holder.document_list_item_updated_at.setText(listItem.getItemType().toString());
        }

        @Override
        public int getItemCount() {
            return listItems.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {

            TextView document_list_item_title;
            TextView document_list_item_description;
            TextView document_list_item_updated_at;


            public ItemViewHolder(View itemView) {
                super(itemView);
                document_list_item_title = itemView.findViewById(R.id.document_list_item_title);
                document_list_item_description = itemView.findViewById(R.id.document_list_item_description);
                document_list_item_updated_at = itemView.findViewById(R.id.document_list_item_updated_at);
            }
        }
    }

}
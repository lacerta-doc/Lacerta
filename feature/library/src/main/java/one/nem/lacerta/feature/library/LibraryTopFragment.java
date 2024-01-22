package one.nem.lacerta.feature.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.tag.DocumentTag;
import one.nem.lacerta.utils.LacertaLogger;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class LibraryTopFragment extends Fragment {

    @Inject
    LacertaLibrary lacertaLibrary;

    @Inject
    LacertaLogger logger;

    public LibraryTopFragment() {
        // Required empty public constructor
    }
    public static LibraryTopFragment newInstance() {
        LibraryTopFragment fragment = new LibraryTopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library_top, container, false);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.library_item_recycler_view);

        ListItemAdapter listItemAdapter = new ListItemAdapter(documentId -> {
            Toast.makeText(getContext(), documentId, Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(listItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        lacertaLibrary.getLibraryPage(10).thenAccept(libraryItemPage -> {
            logger.debug("LibraryTopFragment", "Item selected! libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());
            listItemAdapter.setLibraryItemPage(libraryItemPage);
            getActivity().runOnUiThread(() -> {
                listItemAdapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size() - 1);
            });
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dir_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}

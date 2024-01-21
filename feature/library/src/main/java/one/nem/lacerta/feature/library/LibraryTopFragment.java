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



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class LibraryTopFragment extends Fragment {

    @Inject
    LacertaLibrary lacertaLibrary;

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
            listItemAdapter.setLibraryItemPage(libraryItemPage);
            getActivity().runOnUiThread(() -> {
                listItemAdapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size());
            });
        });

        CollapsingToolbarLayout collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        // Set the Toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        // Set the title of the CollapsingToolbarLayout
        collapsingToolbarLayout.setTitle("Library");

        AppBarLayout appBarLayout = view.findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    // Collapsed
                    getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), one.nem.lacerta.shared.ui.R.color.colorSecondaryContainer));
                } else if (verticalOffset == 0) {
                    // Expanded
                    getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), one.nem.lacerta.shared.ui.R.color.colorSurface));
                } else {
                    // Somewhere in between
                    // Here you can add a color transition if you want
                }
            }
        });
    }
}

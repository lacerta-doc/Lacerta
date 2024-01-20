package one.nem.lacerta.feature.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.document.DocumentMeta;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class HomeTopFragment extends Fragment {

    @Inject
    LacertaLibrary lacertaLibrary;

    public HomeTopFragment() {
        // Required empty public constructor
    }

    public static HomeTopFragment newInstance() {
        HomeTopFragment fragment = new HomeTopFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_top, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.home_item_recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ListItemAdapter listItemAdapter = new ListItemAdapter();
        recyclerView.setAdapter(listItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        lacertaLibrary.getRecentDocument(10).thenAccept(listItems -> {
            listItemAdapter.setListItems(listItems);
            getActivity().runOnUiThread(listItemAdapter::notifyDataSetChanged);
        });

        CollapsingToolbarLayout collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        // Set the Toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        // Set the title of the CollapsingToolbarLayout
        collapsingToolbarLayout.setTitle("Lacerta");

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








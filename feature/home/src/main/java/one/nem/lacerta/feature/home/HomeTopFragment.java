package one.nem.lacerta.feature.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.viewer.ViewerMainActivity;
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

//        setHasOptionsMenu(true);
        Toolbar toolbar = view.findViewById(R.id.home_toolbar);
        toolbar.setNavigationIcon(one.nem.lacerta.shared.ui.R.drawable.arrow_back_24px);
        toolbar.setNavigationOnClickListener(v -> {
            Toast.makeText(getContext(), "Works!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
        });
        toolbar.inflateMenu(R.menu.drawer_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.item_placeholder) {
                Toast.makeText(getContext(), "Works!", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
        return view;
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        RecyclerView recyclerView = view.findViewById(R.id.home_item_recycler_view);
//
//
//        ListItemAdapter listItemAdapter = new ListItemAdapter(documentId -> {
//            Log.d("HomeTopFragment", "onViewCreated: " + documentId);
//            Intent intent = new Intent(getContext(), ViewerMainActivity.class);
//            intent.putExtra("documentId", documentId);
//            startActivity(intent);
//        });
//        recyclerView.setAdapter(listItemAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        lacertaLibrary.getRecentDocument(10).thenAccept(listItems -> {
//            listItemAdapter.setListItems(listItems);
//            getActivity().runOnUiThread(() -> {
//                listItemAdapter.notifyItemRangeInserted(0, listItems.size());
//            });
//        });
//    }
//
//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.drawer_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
}








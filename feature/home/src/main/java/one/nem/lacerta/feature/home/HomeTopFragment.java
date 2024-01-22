package one.nem.lacerta.feature.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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
import one.nem.lacerta.utils.FeatureSwitch;
import one.nem.lacerta.utils.Store;


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

        Toolbar toolbar = view.findViewById(R.id.home_toolbar);
        toolbarSetup(toolbar, false, "ホーム");

        ListItemAdapter listItemAdapter = new ListItemAdapter(documentId -> {
            Log.d("HomeTopFragment", "onViewCreated: " + documentId);
            Intent intent = new Intent(getContext(), ViewerMainActivity.class);
            intent.putExtra("documentId", documentId);
            startActivity(intent);
        });
        recyclerView.setAdapter(listItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        lacertaLibrary.getRecentDocument(10).thenAccept(listItems -> {
            if (listItems == null) {
                return;
            }
            listItemAdapter.setListItems(listItems);
            getActivity().runOnUiThread(() -> {
                Log.d("HomeTopFragment", "onViewCreated: " + listItems.size());
                if (FeatureSwitch.RecyclerView.useSimpleNotifyMethod) {
                    listItemAdapter.notifyDataSetChanged();
                } else {
                    // IndexOutOfBoundsExceptionを吐くことがあったので
                    listItemAdapter.notifyItemRangeInserted(0, listItems.size() - 1);
                }
            });
        });
    }

    /**
     * ToolbarをInitする
     *
     * @param toolbar Toolbar
     * @param showBackButton 戻るボタンを表示するか
     * @param title タイトル
     */
    private void toolbarSetup(Toolbar toolbar, boolean showBackButton, String title) {
        getActivity().runOnUiThread(() -> {
            if (showBackButton) {
                toolbar.setNavigationIcon(one.nem.lacerta.shared.ui.R.drawable.arrow_back_24px);
                toolbar.setNavigationOnClickListener(v -> {
                    //this.libraryItemPage = lacertaLibrary.getLibraryPage(this.libraryItemPage.getParentId(), 10).join();
                    // Back
                    Navigation.findNavController(requireView()).popBackStack();
                });
            } else {
                toolbar.setNavigationIcon(null);
            }
            toolbar.setTitle(title);
        });
    }
}








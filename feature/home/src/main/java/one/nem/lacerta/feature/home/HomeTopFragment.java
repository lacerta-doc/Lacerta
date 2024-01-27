package one.nem.lacerta.feature.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.viewer.ViewerMainActivity;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.utils.FeatureSwitch;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class HomeTopFragment extends Fragment {

    @Inject
    LacertaLibrary lacertaLibrary;

    private ListItemAdapter listItemAdapter;

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

        // Set status bar color
        AppBarLayout appBarLayout = view.findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            if (Math.abs(verticalOffset) == appBarLayout1.getTotalScrollRange()) {
                // Collapsed
                getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), one.nem.lacerta.shared.ui.R.color.colorSecondaryContainer));
            } else if (verticalOffset == 0) {
                // Expanded
                getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), one.nem.lacerta.shared.ui.R.color.colorSurface));
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.home_item_recycler_view);

        Toolbar toolbar = view.findViewById(R.id.home_toolbar);
        toolbarSetup(toolbar, false, "ホーム");

        this.listItemAdapter = new ListItemAdapter(new DocumentSelectListener() {
            @Override
            public void onDocumentSelect(String documentId, String documentName) {
                Intent intent = new Intent(getContext(), ViewerMainActivity.class);
                Log.d("HomeTopFragment", "onDocumentSelect: " + documentId + " " + documentName);
                intent.putExtra("documentId", documentId);
                intent.putExtra("documentName", documentName);
                startActivity(intent, ActivityOptions.makeCustomAnimation(getContext(), one.nem.lacerta.shared.ui.R.anim.nav_up_enter_anim, one.nem.lacerta.shared.ui.R.anim.nav_up_exit_anim).toBundle());
            }
        });
        recyclerView.setAdapter(listItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateList();
    }

    @Override
    public void onResume() {
        super.onResume();

        updateList();
    }

    private void updateList() {
        long startTime = System.currentTimeMillis();
        lacertaLibrary.getRecentDocument(10).thenAccept(listItems -> {
            long endTime = System.currentTimeMillis();
            if (listItems == null) {
                return;
            }
            this.listItemAdapter.setListItems(listItems);
            if (endTime - startTime > 500) { // 500ms以上かかった場合は表示アニメーションをする
                getActivity().runOnUiThread(() -> {
                    this.listItemAdapter.notifyItemRangeInserted(0, listItems.size());
                });
            } else {
                getActivity().runOnUiThread(() -> {
                    this.listItemAdapter.notifyDataSetChanged();
                });
            }
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








package one.nem.lacerta.feature.library;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import one.nem.lacerta.model.FragmentNavigation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryContainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryContainerFragment extends Fragment implements FragmentNavigation {

    FragmentContainerView fragmentContainerView;

    public LibraryContainerFragment() {
        // Required empty public constructor
    }

    public static LibraryContainerFragment newInstance() {
        LibraryContainerFragment fragment = new LibraryContainerFragment();
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
        return inflater.inflate(R.layout.fragment_library_container, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentContainerView = view.findViewById(R.id.fragmentContainerView);
    }

    @Override
    public void navigateToFragment(Fragment fragment) {
        this.navigateToFragment(fragment, true);
    }

    @Override
    public void navigateToFragment(Fragment fragment, boolean addToBackStack) {
        this.navigateToFragment(fragment, addToBackStack, false);
    }

    @Override
    public void navigateToFragment(Fragment fragment, boolean addToBackStack, boolean clearBackStack) {
        if (fragmentContainerView != null) {
        }
    }

    @Override
    public void navigateToFragmentAlternate(Fragment fragment, boolean addToBackStack) {

    }
}
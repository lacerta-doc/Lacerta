package one.nem.lacerta.model;

import androidx.fragment.app.Fragment;

public interface FragmentNavigation {
    void navigateToFragment(Fragment fragment);

    void navigateToFragment(Fragment fragment, boolean addToBackStack);

    void navigateToFragment(Fragment fragment, boolean addToBackStack, boolean clearBackStack);

    void navigateToFragmentAlternate(Fragment fragment, boolean addToBackStack);
}

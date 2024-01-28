package one.nem.lacerta.component.viewer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewerViewPagerAdapter extends FragmentStateAdapter {

    // Variables
    private ArrayList<String> fragmentTargetIdList = new ArrayList<>();
    private ArrayList<String> fragmentTitleList = new ArrayList<>();

    // Setter

    public void setFragmentTargetIdList(ArrayList<String> fragmentTargetIdList) {
        this.fragmentTargetIdList = fragmentTargetIdList;
    }

    public void setFragmentTitleList(ArrayList<String> fragmentTitleList) {
        this.fragmentTitleList = fragmentTitleList;
    }

    public ViewerViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ViewerListFragment.newInstance(fragmentTargetIdList.get(position), fragmentTitleList.get(position));
    }

    @Override
    public int getItemCount() {
        if (fragmentTargetIdList == null || fragmentTitleList == null) {
            return 0;
        } else if (fragmentTargetIdList.size() != fragmentTitleList.size()) {
            throw new IllegalStateException("fragmentTargetIdList.size() != fragmentTitleList.size()");
        } else {
            return fragmentTargetIdList.size();
        }
    }

    @Nullable
    public CharSequence getTabTitle(int position) {
        return fragmentTitleList.get(position);
    }

    // Getter

    public String getFragmentTargetId(int position) {
        return fragmentTargetIdList.get(position);
    }

    public String getFragmentTitle(int position) {
        return fragmentTitleList.get(position);
    }
}

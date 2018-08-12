package neu.edu.bingeshopper.presentation.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.common.NavigationUtil;
import neu.edu.bingeshopper.databinding.FragmentHomeBinding;
import neu.edu.bingeshopper.presentation.productList.ProductListFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        binding.books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtil.navigate(ProductListFragment.newInstance("book", "bestseller")
                        , getFragmentManager().beginTransaction(), R.id.content_frame);
            }
        });

        binding.electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtil.navigate(ProductListFragment.newInstance("electronics", "bestseller")
                        , getFragmentManager().beginTransaction(), R.id.content_frame);
            }
        });

        binding.fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtil.navigate(ProductListFragment.newInstance("fashion", "bestseller")
                        , getFragmentManager().beginTransaction(), R.id.content_frame);
            }
        });
    }
}

package neu.edu.bingeshopper.presentation.productList;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.databinding.FragmentProductListBinding;
import neu.edu.bingeshopper.presentation.ViewModelFactory;

public class ProductListFragment extends DaggerFragment {

    private static String QUERY = "query";
    private static String SORT = "sort";
    private ProductListAdapter adapter;
    private RecyclerView recyclerView;
    private ProductListViewModel viewModel;
    private String query;
    private String sort;
    @Inject
    ViewModelFactory viewModelFactory;

    public static ProductListFragment newInstance() {

        Bundle args = new Bundle();

        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductListFragment newInstance(String query, String sort) {

        Bundle args = new Bundle();
        args.putString(QUERY, query);
        args.putString(SORT, sort);

        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductListViewModel.class);
        if (getArguments() != null) {
            query = getArguments().getString(QUERY);
            sort = getArguments().getString(SORT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentProductListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false);
        init(binding);
        return binding.getRoot();
    }

    private void init(final FragmentProductListBinding binding) {
        binding.productListRecyclerView.setVisibility(View.GONE);
        binding.productListProgressBar.setVisibility(View.VISIBLE);
        adapter = new ProductListAdapter(getFragmentManager());
        recyclerView = binding.productListRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        Observer<ProductListViewModel.ProductListViewModelResponse> observer = new Observer<ProductListViewModel.ProductListViewModelResponse>() {
            @Override
            public void onChanged(@Nullable ProductListViewModel.ProductListViewModelResponse productListViewModelResponse) {
                binding.productListProgressBar.setVisibility(View.GONE);
                binding.productListRecyclerView.setVisibility(View.VISIBLE);
                switch (productListViewModelResponse.getStatus()) {
                    case Error:
                        Toast.makeText(getContext(), productListViewModelResponse.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                    case Success:
                        adapter.setData(productListViewModelResponse.getProducts());
                        break;
                }

            }
        };

        viewModel.getResponseMutableLiveData().observe(this, observer);

        if (query != null && sort != null) {
            viewModel.searchProduct(query, sort);
        }

    }
}


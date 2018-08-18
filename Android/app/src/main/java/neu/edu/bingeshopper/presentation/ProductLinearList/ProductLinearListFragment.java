package neu.edu.bingeshopper.presentation.ProductLinearList;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.common.NavigationUtil;
import neu.edu.bingeshopper.common.UserManager;
import neu.edu.bingeshopper.databinding.FragmentLinearListBinding;
import neu.edu.bingeshopper.presentation.ViewModelFactory;
import neu.edu.bingeshopper.presentation.productDetail.ProductDetailFragment;

public class ProductLinearListFragment extends DaggerFragment {

    private static String VIEW_TYPE = "ViewType";
    private CurrentViewType currentViewType;
    private FragmentLinearListBinding binding;
    private RecyclerView recyclerView;
    private ProductLinearListViewModel viewModel;
    private ProductLinearListAdapter adapter;

    @Inject
    UserManager userManager;

    @Inject
    ViewModelFactory viewModelFactory;

    public static ProductLinearListFragment newInstance(CurrentViewType viewType) {

        Bundle args = new Bundle();
        args.putSerializable(VIEW_TYPE, viewType);
        ProductLinearListFragment fragment = new ProductLinearListFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentViewType = (CurrentViewType) getArguments().getSerializable(VIEW_TYPE);
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductLinearListViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_linear_list, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        binding.progressBar.setVisibility(View.VISIBLE);
        recyclerView = binding.linearListRecyclerview;
        adapter = new ProductLinearListAdapter(currentViewType, new ProductLinearListFragmentCallBack() {
            @Override
            public void onProductClicked(Product product) {
                NavigationUtil.navigate(ProductDetailFragment.newInstance(product), getFragmentManager().beginTransaction(), R.id.content_frame);
            }

            @Override
            public void onDeleteFromInventoryClicked(int inventoryId) {

            }

            @Override
            public void onOrderClicked(int orderId) {
                ProductLinearListFragment fragment = ProductLinearListFragment.newInstance(CurrentViewType.ORDER_TRANSACTION);
                NavigationUtil.navigate(fragment, getFragmentManager().beginTransaction(), R.id.content_frame);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        switch (currentViewType) {

            case WISH_LIST:
                initialiseWishListView();
                break;
            case ORDER_HISTORY:
                initialiseOrderHistory();
                break;
            case INVENTORY_LIST:
                initialiseInventoryView();
                break;
        }

    }

    private void initialiseOrderHistory() {
        Observer<ProductLinearListViewModel.ProductLinearListViewModelResponse> observer = new Observer<ProductLinearListViewModel.ProductLinearListViewModelResponse>() {
            @Override
            public void onChanged(@Nullable ProductLinearListViewModel.ProductLinearListViewModelResponse productLinearListViewModelResponse) {
                binding.progressBar.setVisibility(View.GONE);
                switch (productLinearListViewModelResponse.getStatus()) {
                    case Success:
                        if (productLinearListViewModelResponse.getData().isEmpty()) {
                            binding.emptyState.setVisibility(View.VISIBLE);
                            binding.linearListRecyclerview.setVisibility(View.GONE);
                            binding.emptyState.setText("No order history found");
                        } else {
                            binding.emptyState.setVisibility(View.GONE);
                            binding.linearListRecyclerview.setVisibility(View.VISIBLE);
                            adapter.setData(productLinearListViewModelResponse.getData());
                        }
                    case Error:
                        Toast.makeText(getContext(), productLinearListViewModelResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        viewModel.getResponseMutableLiveData().observe(this, observer);
        viewModel.getOrderList(userManager.getUser().getId());
    }

    private void initialiseInventoryView() {
        Observer<ProductLinearListViewModel.ProductLinearListViewModelResponse> observer = new Observer<ProductLinearListViewModel.ProductLinearListViewModelResponse>() {
            @Override
            public void onChanged(@Nullable ProductLinearListViewModel.ProductLinearListViewModelResponse productLinearListViewModelResponse) {
                binding.progressBar.setVisibility(View.GONE);
                switch (productLinearListViewModelResponse.getStatus()) {
                    case Success:
                        if (productLinearListViewModelResponse.getData().isEmpty()) {
                            binding.emptyState.setVisibility(View.VISIBLE);
                            binding.linearListRecyclerview.setVisibility(View.GONE);
                            binding.emptyState.setText("No inventory found");
                        } else {
                            binding.emptyState.setVisibility(View.GONE);
                            binding.linearListRecyclerview.setVisibility(View.VISIBLE);
                            adapter.setData(productLinearListViewModelResponse.getData());
                        }
                    case Error:
                        Toast.makeText(getContext(), productLinearListViewModelResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        viewModel.getResponseMutableLiveData().observe(this, observer);
        viewModel.getSellerInventory(userManager.getUser().getId());
    }

    private void initialiseWishListView() {
        Observer<ProductLinearListViewModel.ProductLinearListViewModelResponse> observerWishList = new Observer<ProductLinearListViewModel.ProductLinearListViewModelResponse>() {
            @Override
            public void onChanged(@Nullable ProductLinearListViewModel.ProductLinearListViewModelResponse productLinearListViewModelResponse) {
                binding.progressBar.setVisibility(View.GONE);
                switch (productLinearListViewModelResponse.getStatus()) {
                    case Success:
                        if (productLinearListViewModelResponse.getData().isEmpty()) {
                            binding.emptyState.setVisibility(View.VISIBLE);
                            binding.linearListRecyclerview.setVisibility(View.GONE);
                            binding.emptyState.setText("No wish list found");

                        } else {
                            binding.emptyState.setVisibility(View.GONE);
                            binding.linearListRecyclerview.setVisibility(View.VISIBLE);
                            adapter.setData(productLinearListViewModelResponse.getData());

                        }
                        break;

                    case Error:
                        Toast.makeText(getContext(), productLinearListViewModelResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        viewModel.getResponseMutableLiveData().observe(this, observerWishList);
        viewModel.getWishList(userManager.getUser().getId());
    }

    public enum CurrentViewType {
        WISH_LIST,
        ORDER_HISTORY,
        ORDER_TRANSACTION,
        INVENTORY_LIST;
    }

    interface ProductLinearListFragmentCallBack {
        void onProductClicked(Product product);

        void onDeleteFromInventoryClicked(int inventoryId);

        void onOrderClicked(int orderId);
    }

}

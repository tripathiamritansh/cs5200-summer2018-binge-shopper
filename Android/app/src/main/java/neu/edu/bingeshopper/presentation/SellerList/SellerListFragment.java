package neu.edu.bingeshopper.presentation.SellerList;

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
import neu.edu.bingeshopper.Repository.Model.Cart;
import neu.edu.bingeshopper.Repository.Model.CartItem;
import neu.edu.bingeshopper.Repository.Model.Inventory;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.Repository.Model.User;
import neu.edu.bingeshopper.common.NavigationUtil;
import neu.edu.bingeshopper.databinding.FragmentSellerListBinding;
import neu.edu.bingeshopper.presentation.ViewModelFactory;
import neu.edu.bingeshopper.presentation.reviews.ReviewsFragment;

public class SellerListFragment extends DaggerFragment {


    private static final String PRODUCT_ID = "Product_id";
    private static final String PRODUCT_QUANTITY = "Product_Quantity";
    private int productId;
    private int productQuantity;
    private SellerListViewModel viewModel;
    private FragmentSellerListBinding binding;
    private SellerListAdapter adapter;
    private RecyclerView recyclerView;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    Cart cart;


    public static SellerListFragment newInstance(int productId, int quantity) {

        Bundle args = new Bundle();
        args.putInt(PRODUCT_ID, productId);
        args.putInt(PRODUCT_QUANTITY, quantity);
        SellerListFragment fragment = new SellerListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            productId = getArguments().getInt(PRODUCT_ID);
            productQuantity = getArguments().getInt(PRODUCT_QUANTITY);
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SellerListViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_seller_list, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        binding.sellerListProgressBar.setVisibility(View.VISIBLE);
        binding.sellerListRecyclerView.setVisibility(View.GONE);
        binding.sellerListEmptyState.setVisibility(View.GONE);
        initialiseRecycleView();

        Observer<SellerListViewModel.SellerListViewModelResponse> sellerListViewModelResponseObserver = new Observer<SellerListViewModel.SellerListViewModelResponse>() {
            @Override
            public void onChanged(@Nullable SellerListViewModel.SellerListViewModelResponse sellerListViewModelResponse) {
                binding.sellerListProgressBar.setVisibility(View.GONE);
                switch (sellerListViewModelResponse.getStatus()) {
                    case SUCCESS:
                        if (sellerListViewModelResponse.getInventories().isEmpty()) {
                            binding.sellerListEmptyState.setVisibility(View.VISIBLE);
                        } else {
                            binding.sellerListRecyclerView.setVisibility(View.VISIBLE);
                            adapter.setData(sellerListViewModelResponse.getInventories());
                        }
                        break;
                    case ERROR:
                        Toast.makeText(getContext(), sellerListViewModelResponse.getMessgae(), Toast.LENGTH_LONG).show();
                        getFragmentManager().popBackStack();
                }

            }
        };

        viewModel.getMutableLiveData().observe(this, sellerListViewModelResponseObserver);
        viewModel.getInventoryList(productId, productQuantity);
    }

    private void initialiseRecycleView() {
        adapter = new SellerListAdapter(new SellerListFragmentCallBack() {
            @Override
            public void onAddToCartClicked(Product product, User seller, Inventory inventory) {
                boolean res = cart.saveToCart(new CartItem(product, seller, productQuantity, inventory));
                if (res) {
                    Toast.makeText(getContext(), "Product added in the cart", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Product not added in the cart", Toast.LENGTH_LONG).show();
                }
                getFragmentManager().popBackStack();
            }

            @Override
            public void onShowSellerReviewsClicked(int sellerId) {

                ReviewsFragment fragment = ReviewsFragment.newInstance(ReviewsFragment.ReviewListType.SellerReviews, sellerId);
                NavigationUtil.navigate(fragment, getFragmentManager().beginTransaction(), R.id.content_frame);

            }
        });

        recyclerView = binding.sellerListRecyclerView;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    public interface SellerListFragmentCallBack {
        void onAddToCartClicked(Product product, User seller, Inventory inventory);

        void onShowSellerReviewsClicked(int sellerId);
    }

}

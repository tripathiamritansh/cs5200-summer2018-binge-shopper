package neu.edu.bingeshopper.presentation.productDetail;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.AdapterItem;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.databinding.FragmentProductDetailBinding;
import neu.edu.bingeshopper.presentation.ViewModelFactory;

public class ProductDetailFragment extends DaggerFragment {

    private Product product;
    private static String PRODUCT_KEY = "product_key";
    private Integer quatityEntered = 1;
    private FragmentProductDetailBinding binding;

    private RecyclerView recyclerView;
    private ProductDetailAdapter adapter;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    neu.edu.bingeshopper.common.UserManager userManager;

    private ProductDetailViewModel viewModel;

    public static ProductDetailFragment newInstance(Product product) {

        Bundle args = new Bundle();
        args.putParcelable(PRODUCT_KEY, product);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductDetailViewModel.class);
        if (getArguments() != null) {
            product = getArguments().getParcelable(PRODUCT_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        recyclerView = binding.productDetailRecyclerView;
        adapter = new ProductDetailAdapter(getFragmentManager(), userManager, getContext(), new ProductDetailFragmentCallBack() {
            @Override
            public void OnAddToInventoryClicked(int qty, int price, Product product) {
                viewModel.addProductToInventory(Objects.requireNonNull(userManager.getUser()).getId(), qty, price, product);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        final List<AdapterItem> items = new ArrayList<>();
        if (product != null) {
            items.add(product);
            adapter.setData(items);
        }

        Observer<ProductDetailViewModel.ProductDetailsViewModelResponse> observer = new Observer<ProductDetailViewModel.ProductDetailsViewModelResponse>() {
            @Override
            public void onChanged(@Nullable ProductDetailViewModel.ProductDetailsViewModelResponse productDetailsViewModelResponse) {
                switch (productDetailsViewModelResponse.getStatus()) {
                    case Success:
                        if (productDetailsViewModelResponse.getReviews() != null) {
                            items.addAll(productDetailsViewModelResponse.getReviews());
                            adapter.setData(items);
                        } else {
                            Toast.makeText(getContext(), "Product Added in sellers Inventory", Toast.LENGTH_LONG).show();
                            getFragmentManager().popBackStack();
                        }
                        break;
                    case Error:
                        Toast.makeText(getContext(), productDetailsViewModelResponse.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                }

            }
        };
        viewModel.getResponseMutableLiveData().observe(this, observer);
        viewModel.fetchReviews(product.getId());

    }

    interface ProductDetailFragmentCallBack {
        void OnAddToInventoryClicked(int qty, int price, Product product);
    }


}

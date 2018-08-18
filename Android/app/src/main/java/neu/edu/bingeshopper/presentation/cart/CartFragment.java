package neu.edu.bingeshopper.presentation.cart;

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

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.Cart;
import neu.edu.bingeshopper.Repository.Model.CartItem;
import neu.edu.bingeshopper.common.UserManager;
import neu.edu.bingeshopper.databinding.FragmentCartBinding;
import neu.edu.bingeshopper.presentation.ViewModelFactory;

public class CartFragment extends DaggerFragment {

    private RecyclerView recyclerView;
    private CartAdapter adapter;

    @Inject
    Cart cart;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    UserManager userManager;

    private CartViewModel viewModel;


    public static CartFragment newInstance() {

        Bundle args = new Bundle();

        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CartViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCartBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        init(binding);
        return binding.getRoot();
    }

    private void init(final FragmentCartBinding binding) {

        Observer<CartViewModel.CartViewModelResponse> observer = new Observer<CartViewModel.CartViewModelResponse>() {
            @Override
            public void onChanged(@Nullable CartViewModel.CartViewModelResponse cartViewModelResponse) {
                Toast.makeText(getContext(), cartViewModelResponse.getMessage(), Toast.LENGTH_LONG).show();
                switch (cartViewModelResponse.getStatus()) {
                    case Success:
                        cart.clearCart();
                        getFragmentManager().popBackStack();
                        break;
                    case Error:
                        break;

                }
            }
        };

        viewModel.getResponseMutableLiveData().observe(this, observer);


        final List<CartItem> cartItems = cart.getCartItems();
        updateView(binding, cartItems);
        recyclerView = binding.cartRecyclerView;
        adapter = new CartAdapter(cartItems, new CartFragmentCallback() {
            @Override
            public void onDeleteClicked(CartItem item) {
                if (cart.removeFromCart(item)) {
                    List<CartItem> updatedCart = cart.getCartItems();
                    adapter.setData(updatedCart);
                    updateView(binding, updatedCart);
                } else {
                    Toast.makeText(getContext(), "Could not remove from cart", Toast.LENGTH_LONG).show();
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    private void updateView(final FragmentCartBinding binding, @Nullable List<CartItem> cartItems) {
        if (Objects.requireNonNull(cartItems).isEmpty()) {
            binding.group.setVisibility(View.GONE);
            binding.emptyState.setVisibility(View.VISIBLE);
        } else {
            binding.group.setVisibility(View.VISIBLE);
            binding.emptyState.setVisibility(View.GONE);
            binding.totalPrice.setText("Total Price :$" + cart.getCartTotal());
            binding.placeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.group.setVisibility(View.GONE);
//                    binding.
                    viewModel.placeOrder(userManager.getUser().getId(), cart.getCartItems());
                }
            });
        }

    }

    interface CartFragmentCallback {
        void onDeleteClicked(CartItem item);
    }
}

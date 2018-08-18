package neu.edu.bingeshopper.presentation.cart;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.CartItem;
import neu.edu.bingeshopper.databinding.ItemCartBinding;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartItem> data;
    private CartFragment.CartFragmentCallback cartFragmentCallback;

    public CartAdapter(List<CartItem> data, CartFragment.CartFragmentCallback cartFragmentCallback) {
        this.data = data;
        this.cartFragmentCallback = cartFragmentCallback;
    }

    public void setData(List<CartItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCartBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_cart, parent, false);

        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCartBinding binding;

        public ViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        void bind(final int pos) {
            Picasso.get().load(data.get(pos).getProduct().getImage_url()).into(binding.productImage);
            binding.productName.setText(data.get(pos).getProduct().getName());
            binding.productPrice.setText("$" + data.get(pos).getInventory().getPrice());
            binding.textView.setText("Quantity :" + String.valueOf(data.get(pos).getQty()));
            binding.sellerName.setText("Seller :" + data.get(pos).getSeller().getUsername());

            binding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartFragmentCallback.onDeleteClicked(data.get(pos));
                }
            });
            binding.executePendingBindings();
        }
    }
}

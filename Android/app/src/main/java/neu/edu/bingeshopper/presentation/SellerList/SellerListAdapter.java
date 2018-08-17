package neu.edu.bingeshopper.presentation.SellerList;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.Inventory;
import neu.edu.bingeshopper.databinding.ItemSellerListBinding;

public class SellerListAdapter extends RecyclerView.Adapter<SellerListAdapter.ViewHolder> {

    private List<Inventory> data = new ArrayList<>();
    private ItemSellerListBinding binding;
    private SellerListFragment.SellerListFragmentCallBack callBack;

    public SellerListAdapter(SellerListFragment.SellerListFragmentCallBack callBack) {
        this.callBack = callBack;
    }

    public void setData(List<Inventory> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_seller_list, parent, false);
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

        ItemSellerListBinding binding;

        public ViewHolder(ItemSellerListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final int pos) {
            binding.sellerName.setText(data.get(pos).getSeller().getUsername());

            binding.price.setText("Price :$" + String.valueOf(data.get(pos).getPrice()));
            binding.addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onAddToCartClicked(data.get(pos).getProduct(), data.get(pos).getSeller(),data.get(pos));
                }
            });
            binding.sellerReviewsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onShowSellerReviewsClicked(data.get(pos).getSeller().getId());
                }
            });
        }
    }
}

package neu.edu.bingeshopper.presentation.ProductLinearList;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.Order;
import neu.edu.bingeshopper.databinding.ItemInventoryListBinding;
import neu.edu.bingeshopper.databinding.ItemOrderHistoryBinding;
import neu.edu.bingeshopper.databinding.ItemWishListBinding;

public class ProductLinearListAdapter extends RecyclerView.Adapter {

    private List<ProductLinearListModel> data = new ArrayList<>();

    private ProductLinearListFragment.CurrentViewType currentViewType;

    private ProductLinearListFragment.ProductLinearListFragmentCallBack callBack;

    public ProductLinearListAdapter(ProductLinearListFragment.CurrentViewType currentViewType, ProductLinearListFragment.ProductLinearListFragmentCallBack callBack) {
        this.currentViewType = currentViewType;
        this.callBack = callBack;
    }

    public void setData(List<ProductLinearListModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (currentViewType) {
            case WISH_LIST:
                ItemWishListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_wish_list, parent, false);
                return new WishListViewHolder(binding);
            case INVENTORY_LIST:
                ItemInventoryListBinding itemInventoryListBinding = DataBindingUtil.inflate(inflater, R.layout.item_inventory_list, parent, false);
                return new InventoryViewHolder(itemInventoryListBinding);

            case ORDER_HISTORY:
                ItemOrderHistoryBinding orderHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.item_order_history, parent, false);
                return new OrderHistoryViewHolder(orderHistoryBinding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (currentViewType) {
            case WISH_LIST:
                WishListViewHolder viewHolder = (WishListViewHolder) holder;
                viewHolder.bind(position);
                break;
            case INVENTORY_LIST:
                InventoryViewHolder inventoryViewHolder = (InventoryViewHolder) holder;
                inventoryViewHolder.bind(position);
                break;
            case ORDER_HISTORY:
                OrderHistoryViewHolder orderHistoryViewHolder = (OrderHistoryViewHolder) holder;
                orderHistoryViewHolder.bind(position);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class WishListViewHolder extends RecyclerView.ViewHolder {

        private ItemWishListBinding binding;

        public WishListViewHolder(ItemWishListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final int pos) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onProductClicked(data.get(pos).getProduct());
                }
            });
            Picasso.get().load(data.get(pos).getProduct().getImage_url()).into(binding.productImage);
            binding.productName.setText(data.get(pos).getProduct().getName());
            binding.executePendingBindings();
        }
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
        private ItemOrderHistoryBinding orderHistoryBinding;

        public OrderHistoryViewHolder(ItemOrderHistoryBinding orderHistoryBinding) {
            super(orderHistoryBinding.getRoot());
            this.orderHistoryBinding = orderHistoryBinding;
        }

        void bind(int pos) {
            Order order = data.get(pos).getOrder();
            Calendar cal = Calendar.getInstance();
            cal.setTime(order.getDate());
            SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
            orderHistoryBinding.orderDate.setText("Order date :" + dateOnly.format(order.getDate()));
            orderHistoryBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            orderHistoryBinding.executePendingBindings();
        }
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder {

        ItemInventoryListBinding binding;

        public InventoryViewHolder(ItemInventoryListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(int pos) {
            Picasso.get().load(data.get(pos).getInventory().getProduct().getImage_url()).into(binding.productImage);
            binding.productName.setText(data.get(pos).getInventory().getProduct().getName());
            binding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            binding.productPrice.setText("$" + data.get(pos).getInventory().getPrice());
            binding.textView.setText("Quantity :" + data.get(pos).getInventory().getQty());
            binding.executePendingBindings();
        }
    }
}

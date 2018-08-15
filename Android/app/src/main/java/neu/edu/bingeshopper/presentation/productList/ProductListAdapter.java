package neu.edu.bingeshopper.presentation.productList;


import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.common.NavigationUtil;
import neu.edu.bingeshopper.databinding.ItemProductListBinding;
import neu.edu.bingeshopper.presentation.productDetail.ProductDetailFragment;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<Product> data = new ArrayList<>();
    private FragmentManager fragmentManager;

    public ProductListAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }


    public void setData(List<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemProductListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_product_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtil.navigate(ProductDetailFragment.newInstance(data.get(position)), fragmentManager.beginTransaction(), R.id.content_frame);
            }
        });
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemProductListBinding binding;

        public ViewHolder(ItemProductListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(int position) {
            Product product = data.get(position);
            Picasso.get().load(product.getImage_url()).into(binding.productListImage);
            binding.productListText.setText(product.getName());
            binding.executePendingBindings();
        }
    }


}

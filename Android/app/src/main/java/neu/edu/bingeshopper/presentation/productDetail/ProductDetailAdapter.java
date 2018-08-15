package neu.edu.bingeshopper.presentation.productDetail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.AdapterItem;
import neu.edu.bingeshopper.Repository.Model.Product;
import neu.edu.bingeshopper.Repository.Model.ProductReview;
import neu.edu.bingeshopper.databinding.ItemProductDetailBinding;
import neu.edu.bingeshopper.databinding.ItemReviewLayoutBinding;

public class ProductDetailAdapter extends RecyclerView.Adapter {


    List<AdapterItem> data = new ArrayList<>();


    public void setData(List<AdapterItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {

            case 0:
                return new ProductDetailViewHolder((ItemProductDetailBinding)
                        DataBindingUtil.inflate(inflater, R.layout.item_product_detail, parent, false));
            case 1:
                return new ProductReviewViewHolder((ItemReviewLayoutBinding)
                        DataBindingUtil.inflate(inflater, R.layout.item_review_layout, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                ((ProductDetailViewHolder) holder).bind(position);
                break;
            case 1:
                ((ProductReviewViewHolder) holder).bind(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public class ProductDetailViewHolder extends RecyclerView.ViewHolder {

        ItemProductDetailBinding binding;

        public ProductDetailViewHolder(ItemProductDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(int pos) {
            Product product = ((Product) data.get(pos));
            binding.chooseSellerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(binding.container.getContext(), "Button clicker quantity " + binding.quantityEdittext.getText().toString(), Toast.LENGTH_LONG).show();
                }
            });
            Picasso.get().load(product.getImage_url()).into(binding.productImage);
            binding.productName.setText(product.getName());
            binding.productDescription.setText(product.getDescription());
            binding.executePendingBindings();
        }
    }

    public class ProductReviewViewHolder extends RecyclerView.ViewHolder {
        ItemReviewLayoutBinding binding;

        public ProductReviewViewHolder(ItemReviewLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(int pos) {
            ProductReview review = ((ProductReview) data.get(pos));
            binding.review.setText(review.getReview());
        }


    }

}

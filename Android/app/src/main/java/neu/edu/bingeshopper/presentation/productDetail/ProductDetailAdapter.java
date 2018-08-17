package neu.edu.bingeshopper.presentation.productDetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
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
import neu.edu.bingeshopper.Repository.Model.UserType;
import neu.edu.bingeshopper.common.NavigationUtil;
import neu.edu.bingeshopper.common.UserManager;
import neu.edu.bingeshopper.databinding.ItemProductDetailBinding;
import neu.edu.bingeshopper.databinding.ItemReviewLayoutBinding;
import neu.edu.bingeshopper.presentation.SellerList.SellerListFragment;

public class ProductDetailAdapter extends RecyclerView.Adapter {


    private List<AdapterItem> data = new ArrayList<>();
    private FragmentManager fragmentManager;
    private UserManager userManager;
    private Context context;
    private ProductDetailFragment.ProductDetailFragmentCallBack callBack;

    public ProductDetailAdapter(FragmentManager fragmentManager, UserManager userManager, Context context, ProductDetailFragment.ProductDetailFragmentCallBack callBack) {
        this.fragmentManager = fragmentManager;
        this.userManager = userManager;
        this.context = context;
        this.callBack = callBack;
    }

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

        private void bind(final int pos) {
            final Product product = ((Product) data.get(pos));

            if (userManager.getUser() != null && userManager.getUser().getUserType() == UserType.Seller) {
                binding.chooseSellerButton.setVisibility(View.GONE);
                binding.priceEdittext.setVisibility(View.VISIBLE);
                binding.textView4.setVisibility(View.VISIBLE);
                binding.priceHeading.setVisibility(View.VISIBLE);
                binding.quantityEdittext.setVisibility(View.VISIBLE);
                binding.addToInventoryButton.setVisibility(View.VISIBLE);

                binding.addToInventoryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String qty = binding.quantityEdittext.getText().toString();
                        String price = binding.priceEdittext.getText().toString();
                        if (qty.equals("") && price.equals("")) {
                            Toast.makeText(context, "Please Enter price and quantity", Toast.LENGTH_LONG).show();
                        } else {
                            callBack.OnAddToInventoryClicked(Integer.valueOf(qty), Integer.valueOf(price), product);
                        }
                    }
                });

            } else if (userManager.getUser() != null && userManager.getUser().getUserType() == UserType.Buyer) {
                binding.priceHeading.setVisibility(View.GONE);
                binding.addToInventoryButton.setVisibility(View.GONE);
                binding.priceEdittext.setVisibility(View.GONE);
                binding.priceHeading.setVisibility(View.GONE);
                binding.chooseSellerButton.setVisibility(View.VISIBLE);
                binding.quantityEdittext.setVisibility(View.VISIBLE);
                binding.textView4.setVisibility(View.VISIBLE);

                binding.chooseSellerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SellerListFragment fragment = SellerListFragment.newInstance(product.getId(), Integer.valueOf(binding.quantityEdittext.getText().toString()));
                        NavigationUtil.navigate(fragment, fragmentManager.beginTransaction(), R.id.content_frame);
                    }
                });

            }

            Picasso.get().load(product.getImage_url()).into(binding.productImage);
            binding.productName.setText(product.getName());
            binding.productDescription.setText(product.getDescription());
            binding.wishListButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.OnAddToWishListClicked((Product) data.get(pos));
                }
            });
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

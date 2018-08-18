package neu.edu.bingeshopper.presentation.reviews;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.Review;
import neu.edu.bingeshopper.Repository.Model.SellerReview;
import neu.edu.bingeshopper.databinding.ItemReviewLayoutBinding;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {

    List<Review> data = new ArrayList<>();

    public void setData(List<Review> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemReviewLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_review_layout, parent, false);
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

        private ItemReviewLayoutBinding binding;

        public ViewHolder(ItemReviewLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(int pos) {
            SellerReview review = ((SellerReview) data.get(pos).getSellerReviews());
            binding.review.setText(review.getReview());
        }
    }
}

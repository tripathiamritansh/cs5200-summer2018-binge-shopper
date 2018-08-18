package neu.edu.bingeshopper.presentation.reviews;

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
import neu.edu.bingeshopper.databinding.FragmentReviewListBinding;
import neu.edu.bingeshopper.presentation.ViewModelFactory;

public class ReviewsFragment extends DaggerFragment {


    private ReviewListType reviewListType;
    private static final String VIEW_TYPE = "viewType";
    private int userId;
    private static final String USER_ID = "user_id";
    private FragmentReviewListBinding binding;
    private ReviewListAdapter adapter;
    private RecyclerView recyclerView;

    @Inject
    ViewModelFactory viewModelFactory;

    ReviewListViewModel viewModel;

    public static ReviewsFragment newInstance(ReviewListType reviewListType, int id) {

        Bundle args = new Bundle();
        args.putSerializable(VIEW_TYPE, reviewListType);
        args.putInt(USER_ID, id);
        ReviewsFragment fragment = new ReviewsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(USER_ID);
            reviewListType = (ReviewListType) getArguments().getSerializable(VIEW_TYPE);
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReviewListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_list, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {

        recyclerView = binding.reviewListRecyclerView;
        adapter = new ReviewListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.reviewListProgressBar.setVisibility(View.VISIBLE);
        Observer<ReviewListViewModel.ReviewListViewModelResponse> observer = new Observer<ReviewListViewModel.ReviewListViewModelResponse>() {
            @Override
            public void onChanged(@Nullable ReviewListViewModel.ReviewListViewModelResponse reviewListViewModelResponse) {
                binding.reviewListProgressBar.setVisibility(View.GONE);
                switch (reviewListViewModelResponse.getStatus()) {
                    case Success:
                        if (reviewListViewModelResponse.getReviews().isEmpty()) {
                            binding.reviewListEmptyState.setVisibility(View.VISIBLE);
                            binding.reviewListRecyclerView.setVisibility(View.GONE);
                        } else {
                            adapter.setData(reviewListViewModelResponse.getReviews());
                        }
                        break;
                    case Error:
                        Toast.makeText(getContext(), reviewListViewModelResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        break;
                }


            }
        };

        viewModel.getReviewListViewModelResponseMutableLiveData().observe(this, observer);
        viewModel.getReviews(userId);

    }

    public enum ReviewListType {
        BuyerReviews,
        SellerReviews
    }
}

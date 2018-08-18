package neu.edu.bingeshopper.presentation.admin;

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
import neu.edu.bingeshopper.Repository.Model.User;
import neu.edu.bingeshopper.databinding.FragmentAdminBinding;
import neu.edu.bingeshopper.presentation.ViewModelFactory;

public class AdminFragment extends DaggerFragment {

    private AdminAdapter adapter;
    private RecyclerView recyclerView;
    private FragmentAdminBinding binding;
    private AdminViewModel viewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    public static AdminFragment newInstance() {

        Bundle args = new Bundle();
        AdminFragment fragment = new AdminFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AdminViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        recyclerView = binding.recyclerView;
        adapter = new AdminAdapter(new AdminCallBack() {
            @Override
            public void ontoggle(int userId, boolean bool) {
                binding.progressBar.setVisibility(View.VISIBLE);
                viewModel.approveUser(userId, bool);
            }

            @Override
            public void onDeleteClicked(int userId) {
                binding.progressBar.setVisibility(View.VISIBLE);
                viewModel.deleteUser(userId);
            }

            @Override
            public void onUserClicked(User user) {
                switch (user.getUserType()){
                    case Buyer:


                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.progressBar.setVisibility(View.VISIBLE);

        Observer<AdminViewModel.AdminViewModelResponse> observer = new Observer<AdminViewModel.AdminViewModelResponse>() {
            @Override
            public void onChanged(@Nullable AdminViewModel.AdminViewModelResponse adminViewModelResponse) {
                binding.progressBar.setVisibility(View.GONE);
                switch (adminViewModelResponse.getStatus()) {
                    case Success:
                        if (adminViewModelResponse.getUsers().isEmpty()) {
                            binding.emptyState.setVisibility(View.VISIBLE);
                            binding.recyclerView.setVisibility(View.GONE);
                        } else {
                            binding.emptyState.setVisibility(View.GONE);
                            binding.recyclerView.setVisibility(View.VISIBLE);
                            adapter.setUserList(adminViewModelResponse.getUsers());
                        }
                        break;
                    case Error:
                        Toast.makeText(getContext(), adminViewModelResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        viewModel.getResponseMutableLiveData().observe(this, observer);
        viewModel.getAllUsers();
    }

    interface AdminCallBack {
        void ontoggle(int userId, boolean bool);

        void onDeleteClicked(int userId);

        void onUserClicked(User user);
    }
}

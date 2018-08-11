package neu.edu.bingeshopper.presentation.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.common.NavigationUtil;
import neu.edu.bingeshopper.common.UserManager;
import neu.edu.bingeshopper.databinding.FragmentLoginBinding;
import neu.edu.bingeshopper.presentation.MainActivity;
import neu.edu.bingeshopper.presentation.ViewModelFactory;
import neu.edu.bingeshopper.presentation.signup.SignUpFragment;

public class LoginFragment extends DaggerFragment {

    @Inject
    UserManager userManager;
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    LoginViewModel viewModel;


    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        init(binding);
        return binding.getRoot();
    }

    private void init(final FragmentLoginBinding binding) {
        Observer<LoginViewModel.LoginViewModelResponse> loginViewModelResponseObserver = new Observer<LoginViewModel.LoginViewModelResponse>() {
            @Override
            public void onChanged(@Nullable LoginViewModel.LoginViewModelResponse loginViewModelResponse) {
                binding.loginProgress.setVisibility(View.GONE);
                switch (Objects.requireNonNull(loginViewModelResponse).getStatus()) {
                    case Success:
                        ((MainActivity) Objects.requireNonNull(getActivity())).updateNavBar();
                        Objects.requireNonNull(getFragmentManager()).popBackStack();
                        break;
                    case Error:
                        Toast.makeText(getContext(), loginViewModelResponse.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        viewModel.getResponseMutableLiveData().observe(this, loginViewModelResponseObserver);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.loginProgress.setVisibility(View.VISIBLE);
                viewModel.loginUser(binding.username.getText().toString(), binding.password.getText().toString());
            }
        });

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtil.navigate(SignUpFragment.newInstance(), getFragmentManager().beginTransaction(), R.id.content_frame);
            }
        });
    }


}

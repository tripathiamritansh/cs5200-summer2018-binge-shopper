package neu.edu.bingeshopper.presentation.profile;

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

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.User;
import neu.edu.bingeshopper.common.IOUtils;
import neu.edu.bingeshopper.common.UserManager;
import neu.edu.bingeshopper.databinding.FragmentSignupBinding;
import neu.edu.bingeshopper.presentation.ViewModelFactory;

public class ProfileFragment extends DaggerFragment {

    private FragmentSignupBinding binding;
    private ProfileViewModel viewModel;

    @Inject
    UserManager userManager;

    @Inject
    ViewModelFactory viewModelFactory;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {

        Observer<ProfileViewModel.ProfileViewModelResponse> observer = new Observer<ProfileViewModel.ProfileViewModelResponse>() {
            @Override
            public void onChanged(@Nullable ProfileViewModel.ProfileViewModelResponse profileViewModelResponse) {
                binding.loginProgress.setVisibility(View.GONE);
                Toast.makeText(getContext(), profileViewModelResponse.getMessage(), Toast.LENGTH_LONG).show();

            }
        };

        viewModel.getResponseMutableLiveData().observe(this, observer);

        final User user = userManager.getUser();
        binding.usertypeSpinner.setVisibility(View.GONE);
        binding.text.setText("Profile");
        binding.username.setText(user.getUsername());
        binding.lastname.setText(user.getLastName());
        binding.firstname.setText(user.getFirstName());
        binding.email.setText(user.getEmail());
        binding.signupButton.setText("Update Profile");
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IOUtils.hideSoftKeyboard(getActivity());
                binding.loginProgress.setVisibility(View.VISIBLE);
                String email = binding.email.getText().toString();
                String firstName = binding.firstname.getText().toString();
                String lastName = binding.lastname.getText().toString();
                String userName = binding.username.getText().toString();
                String password = binding.password.getText().toString();

                if (userName.equals("") || firstName.equals("") || lastName.equals("") || email.equals("")) {
                    binding.loginProgress.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Fields cannot be blank except password", Toast.LENGTH_LONG).show();
                } else {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setUsername(userName);
                    viewModel.updateProfile(user);
                }
            }
        });


    }
}

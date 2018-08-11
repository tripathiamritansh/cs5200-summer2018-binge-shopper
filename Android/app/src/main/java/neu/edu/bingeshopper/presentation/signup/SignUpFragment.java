package neu.edu.bingeshopper.presentation.signup;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import neu.edu.bingeshopper.R;
import neu.edu.bingeshopper.Repository.Model.User;
import neu.edu.bingeshopper.Repository.Model.UserType;
import neu.edu.bingeshopper.common.NavigationUtil;
import neu.edu.bingeshopper.databinding.FragmentSignupBinding;
import neu.edu.bingeshopper.presentation.MainActivity;
import neu.edu.bingeshopper.presentation.ViewModelFactory;
import neu.edu.bingeshopper.presentation.home.HomeFragment;

public class SignUpFragment extends DaggerFragment implements AdapterView.OnItemSelectedListener {

    @Inject
    ViewModelFactory factory;

    @Inject
    SignUpViewModel viewModel;

    private UserType usertype;
    private FragmentSignupBinding binding;

    public static SignUpFragment newInstance() {

        Bundle args = new Bundle();

        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, factory).get(SignUpViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        initialiseSpinner();
        Observer<SignUpViewModel.SignUpViewModelResponse> observer = new Observer<SignUpViewModel.SignUpViewModelResponse>() {
            @Override
            public void onChanged(@Nullable SignUpViewModel.SignUpViewModelResponse signUpViewModelResponse) {
                binding.loginProgress.setVisibility(View.GONE);
                switch (Objects.requireNonNull(signUpViewModelResponse).getStatus()) {
                    case Success:
                        ((MainActivity) Objects.requireNonNull(getActivity())).updateNavBar();
                        NavigationUtil.navigateWithOutBackStack(HomeFragment.newInstance(), Objects.requireNonNull(getFragmentManager()).beginTransaction(), R.id.content_frame);
                        break;

                    case Error:
                        Toast.makeText(getContext(), signUpViewModelResponse.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        viewModel.getResponseMutableLiveData().observe(this, observer);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    User user = new User(binding.firstname.getText().toString(), binding.lastname.getText().toString(),
                            binding.username.getText().toString(), binding.password.getText().toString(), binding.email.getText().toString(), usertype);
                    binding.loginProgress.setVisibility(View.VISIBLE);
                    viewModel.signUpUser(user);
                } else {
                    Toast.makeText(getContext(), "InValid Input", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    private void initialiseSpinner() {
        Spinner spinner = binding.usertypeSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.usertype_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getItemAtPosition(i).toString().equals("Buyer")) {
            usertype = UserType.BUYER;
        } else if (adapterView.getItemAtPosition(i).toString().equals("Seller")) {
            usertype = UserType.SELLER;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private boolean validateInput() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Objects.nonNull(binding.firstname.getText().toString()) &&
                    Objects.nonNull(binding.lastname.getText().toString())
                    && Objects.nonNull(binding.username.getText().toString()) &&
                    Objects.nonNull(binding.password.getText().toString()) &&
                    Objects.nonNull(binding.email.getText().toString()) && Objects.nonNull(usertype);
        }
        return true;
    }
}

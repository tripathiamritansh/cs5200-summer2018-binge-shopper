package neu.edu.bingeshopper.presentation.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import neu.edu.bingeshopper.common.UserManager;
import neu.edu.bingeshopper.presentation.ViewModelFactory;

public class LoginFragment extends DaggerFragment {

    @Inject
    UserManager userManager;
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    LoginViewModel viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return super.onCreateView(inflater, container, savedInstanceState);
    }


//    private void init()
}

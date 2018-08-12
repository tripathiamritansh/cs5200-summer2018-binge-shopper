package neu.edu.bingeshopper.DI;

import android.arch.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import neu.edu.bingeshopper.presentation.login.LoginViewModel;
import neu.edu.bingeshopper.presentation.productList.ProductListViewModel;
import neu.edu.bingeshopper.presentation.signup.SignUpViewModel;

@Module
public abstract class ViewModelBuilder {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel.class)
    abstract ViewModel bindSignUpViewMode(SignUpViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel.class)
    abstract ViewModel bindProductListViewModel(ProductListViewModel viewModel);
}

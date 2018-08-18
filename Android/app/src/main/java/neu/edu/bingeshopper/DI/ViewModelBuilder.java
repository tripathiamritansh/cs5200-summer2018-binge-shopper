package neu.edu.bingeshopper.DI;

import android.arch.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import neu.edu.bingeshopper.presentation.ProductLinearList.ProductLinearListViewModel;
import neu.edu.bingeshopper.presentation.SellerList.SellerListViewModel;
import neu.edu.bingeshopper.presentation.admin.AdminViewModel;
import neu.edu.bingeshopper.presentation.cart.CartViewModel;
import neu.edu.bingeshopper.presentation.login.LoginViewModel;
import neu.edu.bingeshopper.presentation.productDetail.ProductDetailViewModel;
import neu.edu.bingeshopper.presentation.productList.ProductListViewModel;
import neu.edu.bingeshopper.presentation.profile.ProfileViewModel;
import neu.edu.bingeshopper.presentation.reviews.ReviewListViewModel;
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

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel.class)
    abstract ViewModel bindProductDetailViewModel(ProductDetailViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SellerListViewModel.class)
    abstract ViewModel bindSellerListViewModel(SellerListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel.class)
    abstract ViewModel bindCartViewModel(CartViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(ProductLinearListViewModel.class)
    abstract ViewModel bindProductLinearListViewModel(ProductLinearListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ReviewListViewModel.class)
    abstract ViewModel bindReviewListViewModel(ReviewListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AdminViewModel.class)
    abstract ViewModel bindAdminViewModel(AdminViewModel viewModel);

}

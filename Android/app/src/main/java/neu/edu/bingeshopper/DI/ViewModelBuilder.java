package neu.edu.bingeshopper.DI;

import android.arch.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import neu.edu.bingeshopper.presentation.login.LoginViewModel;

@Module
public abstract class ViewModelBuilder {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindChallengeViewModel(LoginViewModel viewModel);

}

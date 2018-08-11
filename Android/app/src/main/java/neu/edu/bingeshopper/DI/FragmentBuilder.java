package neu.edu.bingeshopper.DI;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import neu.edu.bingeshopper.presentation.login.LoginFragment;
import neu.edu.bingeshopper.presentation.signup.SignUpFragment;

@Module
public abstract class FragmentBuilder {


    @ContributesAndroidInjector
    abstract LoginFragment provideLoginFragment();

    @ContributesAndroidInjector
    abstract SignUpFragment provideSignUpFragment();


}

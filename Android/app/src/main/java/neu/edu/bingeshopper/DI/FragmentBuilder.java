package neu.edu.bingeshopper.DI;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import neu.edu.bingeshopper.presentation.login.LoginFragment;

@Module
public abstract class FragmentBuilder {


    @ContributesAndroidInjector
    abstract LoginFragment provideLoginFragment();
}

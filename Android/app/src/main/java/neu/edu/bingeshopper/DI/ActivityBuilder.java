package neu.edu.bingeshopper.DI;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import neu.edu.bingeshopper.presentation.MainActivity;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract MainActivity bindMainActivity();

}

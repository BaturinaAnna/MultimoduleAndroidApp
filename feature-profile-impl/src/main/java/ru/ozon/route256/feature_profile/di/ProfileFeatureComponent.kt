package ru.ozon.route256.feature_profile.di

import dagger.Component
import ru.ozon.route256.core_local_storage_api.LocalStorageApi
import ru.ozon.route256.core_utils.di.PerFeature
import ru.ozon.route256.feature_profile.presentation.view.ProfileFragment
import ru.ozon.route256.feature_profile_api.ProfileNavigationApi

@Component(
    modules = [InteractorModule::class, RepositoryModule::class],
    dependencies = [ProfileFeatureDependencies::class]
)
@PerFeature
abstract class ProfileFeatureComponent {
    companion object {

        @Volatile
        var profileFeatureComponent: ProfileFeatureComponent? = null
            private set

        fun initAndGet(profileFeatureDependencies: ProfileFeatureDependencies): ProfileFeatureComponent? {
            if (profileFeatureComponent == null) {
                synchronized(ProfileFeatureComponent::class) {
                    profileFeatureComponent = DaggerProfileFeatureComponent.builder()
                        .profileFeatureDependencies(profileFeatureDependencies)
                        .build()
                }
            }
            return profileFeatureComponent
        }

        fun get(): ProfileFeatureComponent? {
            if (profileFeatureComponent == null) {
                throw RuntimeException("you must call 'initAndGet()' method")
            }
            return profileFeatureComponent
        }

        fun resetComponent() {
            profileFeatureComponent = null
        }
    }

    abstract fun inject(fragment: ProfileFragment)

    @PerFeature
    @Component(dependencies = [ProfileNavigationApi::class, LocalStorageApi::class])
    interface ProfileFeatureDependenciesComponent : ProfileFeatureDependencies
}
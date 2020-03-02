package sen.com.module.food.di

import dagger.Module
import dagger.Provides
import sen.com.module.food.dataStores.FoodLocalDataStore
import sen.com.module.food.dataStores.FoodRemoteDataStore
import sen.com.module.food.repositories.FoodRepository
import javax.inject.Singleton

/**
 * Created by korneliussendy on 01/03/20,
 * at 11.01.
 * My Application
 */
@Module
class FoodModule {
//    @Provides
//    fun provideFoodLocalDataStore() = FoodLocalDataStore()
//
//    @Provides
//    fun provideFoodRemoteDataStore() = FoodRemoteDataStore()

    @Provides
    @Singleton
    fun provideFoodRepo(): FoodRepository =
        FoodRepository.instance.apply {
            init(
                FoodLocalDataStore.instance,
                FoodRemoteDataStore.instance
            )
        }
}
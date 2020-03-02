package sen.com.kelana.di

import dagger.Component
import sen.com.module.food.di.FoodModule
import javax.inject.Singleton

/**
 * Created by korneliussendy on 01/03/20,
 * at 11.06.
 * My Application
 */
@Component(modules = [FoodModule::class])
@Singleton
interface AppComponent {
}
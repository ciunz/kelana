package sen.com.module.food.di

import dagger.Component
import javax.inject.Singleton

/**
 * Created by korneliussendy on 01/03/20,
 * at 11.15.
 * My Application
 */
@Component(modules = [FoodModule::class])
@Singleton
interface FoodComponent {
}
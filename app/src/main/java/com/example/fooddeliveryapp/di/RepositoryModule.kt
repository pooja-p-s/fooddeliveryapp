// di/RepositoryModule.kt
package com.example.fooddeliveryapp.di

import com.example.fooddeliveryapp.data.repo.FoodRepositoryImpl
import com.example.fooddeliveryapp.domain.repo.IFoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFoodRepository(
        impl: FoodRepositoryImpl
    ): IFoodRepository
}

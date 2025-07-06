package com.hninor.pruebamelidesign.core.di

import com.hninor.pruebamelidesign.data.repository.ProductRepositoryImpl
import com.hninor.pruebamelidesign.data.repository.ThemeRepositoryImpl
import com.hninor.pruebamelidesign.domain.repository.ProductRepository
import com.hninor.pruebamelidesign.domain.repository.ThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository
    
    @Binds
    abstract fun bindThemeRepository(
        themeRepositoryImpl: ThemeRepositoryImpl
    ): ThemeRepository
} 
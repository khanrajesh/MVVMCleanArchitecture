package com.noobdev.propermvvmarcitecture.di_dependency_injection

import com.noobdev.propermvvmarcitecture.data.remote.ApiService
import com.noobdev.propermvvmarcitecture.data.remote.dto.ResponseDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideResponseMapper(): ResponseDtoMapper {
        return ResponseDtoMapper()
    }

    @Singleton
    @Provides
    fun provideResponseService(): ApiService {
        return  Retrofit.Builder()
            .baseUrl("https://api.coincap.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}

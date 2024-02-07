package com.syhan.cookbook.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.syhan.cookbook.feature_recipes.data.remote.RecipeApi
import com.syhan.cookbook.feature_recipes.data.repository.RecipeRepositoryImpl
import com.syhan.cookbook.feature_recipes.domain.repository.RecipeRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RecipeApi::class.java)
    }
    single<RecipeRepository> {
        RecipeRepositoryImpl(get())
    }
}


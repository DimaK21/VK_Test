package ru.kryu.vktest.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kryu.vktest.data.GoodsRepositoryImpl
import ru.kryu.vktest.data.NetworkClient
import ru.kryu.vktest.data.network.ApiService
import ru.kryu.vktest.data.network.RetrofitNetworkClient
import ru.kryu.vktest.domain.GoodsRepository

const val API_BASE_URL = "https://dummyjson.com/"

val diModule = module {
    includes(
        dataModule,
        repositoryModule,
        useCaseModule,
        viewModelModule,
    )
}

val dataModule = module {
    single<NetworkClient> {
        RetrofitNetworkClient(
            apiService = get(),
            context = androidContext()
        )
    }
    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single<GoodsRepository> {
        GoodsRepositoryImpl(networkClient = get())
    }
}

val useCaseModule = module {

}

val viewModelModule = module {

}

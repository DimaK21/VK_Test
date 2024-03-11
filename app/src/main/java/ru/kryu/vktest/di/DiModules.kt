package ru.kryu.vktest.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kryu.vktest.data.GoodsRepositoryImpl
import ru.kryu.vktest.data.NetworkClient
import ru.kryu.vktest.data.network.ApiService
import ru.kryu.vktest.data.network.RetrofitNetworkClient
import ru.kryu.vktest.domain.GoodsRepository
import ru.kryu.vktest.domain.GoodsUseCase
import ru.kryu.vktest.presentation.GoodsViewModel

const val API_BASE_URL = "https://dummyjson.com/"

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
    single {
        GoodsUseCase(goodsRepository = get())
    }
}

val viewModelModule = module {
    viewModel {
        GoodsViewModel(goodsUseCase = get())
    }
}

val diModule = module {
    includes(
        dataModule,
        repositoryModule,
        useCaseModule,
        viewModelModule,
    )
}

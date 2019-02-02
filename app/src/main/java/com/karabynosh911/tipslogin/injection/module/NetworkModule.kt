package com.karabynosh911.tipslogin.injection.module

import com.karabynosh911.tipslogin.network.TipsApi
import com.karabynosh911.tipslogin.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@Suppress("unused")
object NetworkModule{

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideTipsApi(retrofit: Retrofit): TipsApi{
        return retrofit.create(TipsApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic

    internal fun provideRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}
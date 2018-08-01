package sample.displayqiita.dagger

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sample.displayqiita.request.ArticleRequest
import javax.inject.Singleton

/**
 * ライブラリによりWEB APIの要求やQiitaの記事の取得要求を行います.
  */
class RequestModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl("https://qiita.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideArticleRequest(retrofit: Retrofit): ArticleRequest =
            retrofit.create(ArticleRequest::class.java)
}
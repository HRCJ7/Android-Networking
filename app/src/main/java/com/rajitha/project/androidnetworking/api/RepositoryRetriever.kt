package com.rajitha.project.androidnetworking.api
import com.rajitha.project.androidnetworking.BuildConfig
import com.rajitha.project.androidnetworking.data.RepoResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryRetriever {
  private val service: GithubService

  companion object {
    private const val BASE_URL = "https://api.github.com/"
  }

  init {
      val httpClient = OkHttpClient.Builder()
      if (BuildConfig.DEBUG) {
          val logging = HttpLoggingInterceptor()
          logging.setLevel(HttpLoggingInterceptor.Level.BODY)
          httpClient.addInterceptor(logging)
      }

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    service = retrofit.create(GithubService::class.java)
  }

  fun getRepositories(callback: Callback<RepoResult>) {
    service.searchRepositories().enqueue(callback)
  }

}
package com.rajitha.project.androidnetworking.api

import com.rajitha.project.androidnetworking.data.RepoResult
import retrofit2.Call
import retrofit2.http.GET


interface GithubService {
  @GET("/repositories")
  fun retrieveRepositories(): Call<RepoResult>

  @GET("/search/repositories?q=language:kotlin&sort=stars&order=desc") //sample search
  fun searchRepositories(): Call<RepoResult>

  @GET("user")
  fun getUsers(): Call<RepoResult>
}
package com.rajitha.project.androidnetworking.api
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import com.rajitha.project.androidnetworking.R
import com.rajitha.project.androidnetworking.data.RepoResult
import com.rajitha.project.androidnetworking.ui.adapters.RepoListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : Activity() {

  private val repoRetriever = RepositoryRetriever()

  private val callback = object : Callback<RepoResult> {
    override fun onFailure(call: Call<RepoResult>?, t: Throwable?) {
      Log.e("MainActivity", "Problem calling Github API ${t?.message}")
    }

    override fun onResponse(call: Call<RepoResult>?, response: Response<RepoResult>?) {
      response?.isSuccessful.let {
        val resultList = RepoResult(response?.body()?.items ?: emptyList())
        repoList.adapter = RepoListAdapter(resultList)
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    repoList.layoutManager = LinearLayoutManager(this)

    if (isNetworkConnected()) {
      repoRetriever.getRepositories(callback)
    } else {
      AlertDialog.Builder(this).setTitle("No Internet Connection")
          .setMessage("Please check your internet connection and try again")
          .setPositiveButton(android.R.string.ok) { _, _ -> }
          .setIcon(android.R.drawable.ic_dialog_alert).show()
    }

    refreshButton.setOnClickListener {
      repoRetriever.getRepositories(callback)
    }
  }

  private fun isNetworkConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
    val networkInfo = connectivityManager.activeNetworkInfo //2
    return networkInfo != null && networkInfo.isConnected //3
  }
}

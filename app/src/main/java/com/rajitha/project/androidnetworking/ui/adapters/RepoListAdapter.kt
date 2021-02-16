package com.rajitha.project.androidnetworking.ui.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rajitha.project.androidnetworking.R
import com.rajitha.project.androidnetworking.data.Item
import com.rajitha.project.androidnetworking.data.RepoResult
import com.rajitha.project.androidnetworking.extensions.ctx
import kotlinx.android.synthetic.main.item_repo.view.*


class RepoListAdapter(private val repoList: RepoResult) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    //2
    val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_repo, parent, false)
    return ViewHolder(view)
  }


  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    //3
    holder.bindRepo(repoList.items[position])
  }

  //4
  override fun getItemCount(): Int = repoList.items.size

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    //5
    fun bindRepo(repo: Item) {
      with(repo) {
        //6
        itemView.username.text = owner.login.orEmpty()
        //7
        itemView.repoDescription.text = description.orEmpty()
      }
    }
  }
}
package com.alimswi.matic_challenge.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alimswi.matic_challenge.Data.RepositoryData
import com.alimswi.matic_challenge.Model.Repository
import com.alimswi.matic_challenge.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_repo_cardview.view.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList

class RepositoryAdapter(val context: Context,val RepositoryList:ArrayList<Repository>) :
                        RecyclerView.Adapter<RepositoryAdapter.MyViewHolder>(){
    val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_repo_cardview,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {

        return  RepositoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.BindRepo(RepositoryList[position])
    }

    inner class   MyViewHolder(item: View) : RecyclerView.ViewHolder(item){
        fun BindRepo(Repo : Repository)=try{

            itemView.txtRepoName.text = Repo.RepositoryName
            itemView.txtRepoDescription.text = Repo.RepositoryDescription
            itemView.txtRepoOwner.text = Repo.RepositoryOwner?.OwnerName
            itemView.txtRepoStarCount.text = Repo.RepositoryNumberOfStars.toString()

            Picasso.get().load(Repo.RepositoryOwner?.OwnerImageUrl)
                .into(itemView.imgRepoOwnerImage, object : Callback {
                    override fun onSuccess() {
                        Log.i("1","--------Loading image Successful--------")
                    }

                    override fun onError(e: java.lang.Exception?) {
                        Log.e("2","--------Error loading image--------"+e!!.message)
                        var tempImg = itemView.findViewById<ImageView>(R.id.imgRepoOwnerImage)
                        tempImg.setImageResource(R.drawable.github)
                    }

                })
        }
        catch (e:Exception){
            Log.e(TAG,e.printStackTrace().toString())
        }
    }

}
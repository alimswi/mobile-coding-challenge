package com.alimswi.matic_challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimswi.matic_challenge.Adapter.RepositoryAdapter
import com.alimswi.matic_challenge.Data.RepositoryData
import com.alimswi.matic_challenge.Model.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(),CoroutineScope {
    private val TAG = javaClass.simpleName
    lateinit var repAdapter:RepositoryAdapter
    private var repoList:ArrayList<Repository> = arrayListOf()
    private val repoData = RepositoryData()
    private var layoutManager: RecyclerView.LayoutManager? = null

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        launch{
            loadRepositories(getString(R.string.github_trending_api_url)+">${getDateByInterval()}&sort=stars&order=desc")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    private suspend fun loadRepositories(repoURl:String?){
        try {
                    repoList =  repoData.getRepositoryData(repoURl!!,"items",0)
                    while (repoList.isEmpty()) delay(1)

                    repAdapter = RepositoryAdapter(this,repoList)
                    rvMainRepoList.layoutManager = layoutManager
                    rvMainRepoList.adapter = repAdapter
                    rvMainRepoList.isNestedScrollingEnabled = false
                    rvMainRepoList.setHasFixedSize(true)
                    rvMainRepoList.setItemViewCacheSize(20)

            }
        catch (e:Exception){
            Log.e(TAG,e.printStackTrace().toString())
        }
    }

    private fun getDateByInterval():String?{
        return try{
            val date = getDaysAgo(30)
            val format = SimpleDateFormat("yyyy-MM-dd")
            format.format(date)
        } catch (e: Exception){
            Log.e(TAG,e.message)
            null
        }
    }

    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

        return calendar.time
    }
}

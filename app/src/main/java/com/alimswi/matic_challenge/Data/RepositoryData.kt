package com.alimswi.matic_challenge.Data

import android.util.Log
import com.alimswi.matic_challenge.Model.Owner
import com.alimswi.matic_challenge.Model.Repository
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.util.logging.Logger
import kotlin.Exception

class RepositoryData {
   val TAG:String = javaClass.simpleName

    fun getRepositoryData(RepositoryUrl:String?,JsonArrayName:String?,PageNumber:Int?):ArrayList<Repository>{
        try{
            var repositoryList:ArrayList<Repository> = arrayListOf()
            Fuel.get(RepositoryUrl!!)
                .timeout(100000)
                .header("Content-Type" to "application/json")
                .responseJson { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.e("NOT#VALID", "$ex")
                        }
                        is Result.Success -> {
                            val data = result.get()
                            val jsonData = result.value.obj().getJSONArray(JsonArrayName)
                                    for(j:Int  in 0 until jsonData.length()) {
                                        val owner = Gson().fromJson(jsonData.getJSONObject(j).getString("owner"),Owner::class.java)
                                        val repoName =jsonData.getJSONObject(j).get("name").toString()
                                        val repoDescription = jsonData.getJSONObject(j).get("description").toString()
                                        val repoNumberOfStars =jsonData.getJSONObject(j).get("stargazers_count").toString().toInt()

                                        repositoryList.add(Repository(repoName,repoDescription,owner,repoNumberOfStars))
                                        Logger.getLogger(request!!::class.java.name)
                                            .warning("${owner.OwnerName} loaded")
                                    }
                            }
                        }
                    }
            return repositoryList
        }
        catch (e:Exception){
            Log.e(TAG,"CANNOT LOAD:"+e.message)
            return arrayListOf()
        }
    }
}
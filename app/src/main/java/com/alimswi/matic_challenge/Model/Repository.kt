package com.alimswi.matic_challenge.Model

data class Repository(
    var RepositoryName:String?=null,
    var RepositoryDescription:String? = null,
    var RepositoryOwner:Owner? = null,
    var RepositoryNumberOfStars:Int? = 0)
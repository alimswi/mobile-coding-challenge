package com.alimswi.matic_challenge.Model

class Repository(
    var RepositoryName:String?=null,
    var RepositoryDescription:String? = null,
    var RepositoryOwner:Owner? = null,
    var RepositoryNumberOfStars:Int? = 0)
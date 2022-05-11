package com.example.firebase_cloudfirestore.model

class User {
    var name : String? = null
    var age : Int? = null

    constructor(name: String?, age: Int?) {
        this.name = name
        this.age = age
    }

    constructor()

}
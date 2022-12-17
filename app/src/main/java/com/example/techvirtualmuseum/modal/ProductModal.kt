package com.example.techvirtualmuseum.modal

class productModal {
    var name: String? = null
    var descripcion: String? = null
    var img: String? = null
    var id: String? = null
    var video: String? = null

    constructor() {
        // empty constructor required for firebase.
    }

    // constructor for our object class.
    constructor(name: String?, descripcion:String?, img: String?, id:String?, video: String?) {
        this.name = name
        this.descripcion = descripcion
        this.id = id
        this.img = img
        this.video = video
    }
}
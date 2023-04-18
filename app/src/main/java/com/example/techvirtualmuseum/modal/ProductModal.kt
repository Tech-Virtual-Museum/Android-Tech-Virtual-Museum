package com.example.techvirtualmuseum.modal

class ProductModal {
    var name: String? = null
    var descripcion: String? = null
    var img: String? = null
    var videoId: String? = null
    var video: String? = null

    constructor() {
        // empty constructor required for firebase.
    }

    // constructor for our object class.
    constructor(name: String?, descripcion:String?, img: String?, videoId:String?, video: String?) {
        this.name = name
        this.descripcion = descripcion
        this.videoId = videoId
        this.img = img
        this.video = video
    }
}
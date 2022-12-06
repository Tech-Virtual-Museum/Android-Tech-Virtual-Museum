package com.example.techvirtualmuseum

class productModal {
    // getter and setter methods variables for storing our image and name.
    var name: String? = null
    var descripcion: String? = null
    var img: String? = null

    constructor() {
        // empty constructor required for firebase.
    }

    // constructor for our object class.
    constructor(name: String?, descripcion:String?, img: String?) {
        this.name = name
        this.descripcion = descripcion
        this.img = img
    }
}
package com.example.techvirtualmuseum

class productModal {
    var name: String? = null
    var descripcion: String? = null
    var img: String? = null
    var id: String? = null

    constructor() {
        // empty constructor required for firebase.
    }

    // constructor for our object class.
    constructor(name: String?, descripcion:String?, img: String?, id:String?) {
        this.name = name
        this.descripcion = descripcion
        this.id = id
        this.img = img
    }
}
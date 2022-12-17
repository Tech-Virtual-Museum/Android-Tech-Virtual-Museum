package com.example.techvirtualmuseum.modal

class dataModal {
    // getter and setter methods variables for storing our image and name.
    var name: String? = null
    var date: String? = null
    var hour: String? = null
    var pricing: String? = null
    var description: String? = null
    var imgUrl: String? = null

    constructor() {
        // empty constructor required for firebase.
    }

    // constructor for our object class.
    constructor(name: String?, date:String? , hour:String?, description:String?, pricing: String?, imgUrl: String?) {
        this.name = name
        this.date = date
        this.hour = hour
        this.pricing = pricing
        this.description = description
        this.imgUrl = imgUrl
    }

}
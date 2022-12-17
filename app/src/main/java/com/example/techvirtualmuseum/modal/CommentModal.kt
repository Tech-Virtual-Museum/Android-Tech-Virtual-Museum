package com.example.techvirtualmuseum.modal

class commentModal {
    // getter and setter methods variables for storing our image and name.
    var name: String? = null
    var comment: String? = null

    constructor() {
        // empty constructor required for firebase.
    }

    // constructor for our object class.
    constructor(name: String?, comment: String?) {
        this.name = name
        this.comment = comment
    }

}
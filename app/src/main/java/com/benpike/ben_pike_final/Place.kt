package com.benpike.ben_pike_final

class Place {
    var placeName: String? = null
    var url: String? = null
    var placeLat: Number? = null
    var placeLong: Number? = null

    // required empty constructor - not needed to add, but needed to read
    constructor() {
    }

    // 1st overload w/all properties passed in
    constructor(placeName: String, url: String?, placeLat: Number, placeLong: Number) {
        this.placeName = placeName
        this.url = url
        this.placeLat = placeLat
        this.placeLong = placeLong
    }
}
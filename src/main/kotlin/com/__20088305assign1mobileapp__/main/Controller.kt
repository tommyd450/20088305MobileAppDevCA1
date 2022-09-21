package com.__20088305assign1mobileapp__.main
import mu.KotlinLogging
class controller {
    fun addPlacemark(){
        var aPlacemark = PlacemarkModel()
        println("Add Placemark")
        println()
        print("Enter a Title : ")
        aPlacemark.title = readLine()!!
        print("Enter a Description : ")
        aPlacemark.description = readLine()!!

        if (aPlacemark.title.isNotEmpty() && aPlacemark.description.isNotEmpty()) {
            aPlacemark.id = placemarks.size.toLong()
            placemarks.add(aPlacemark.copy())
            logger.info("Placemark Added : [ $aPlacemark ]")
        }
        else
            logger.info("Placemark Not Added")
    }

    fun updatePlacemark() {
        println("Update Placemark")
        println()
        listPlacemarks()
        var searchId = getId()
        val aPlacemark = search(searchId)

        if(aPlacemark != null) {
            // Ask the user for new details here
            print("Old title ="+aPlacemark.title)
            print("Enter a new Title : ")
            aPlacemark.title = readLine()!!
            print("Old title ="+aPlacemark.description)
            print("Enter a New Description : ")
            aPlacemark.description = readLine()!!
        }
        else
            println("Placemark Not Updated...")
    }

    fun listPlacemarks() {
        println("You Chose List All Placemarks")
        println()
        placemarks.forEach{ logger.info("${it}")}
    }

    fun getId() : Long {
        var strId : String?
        var searchId : Long
        print("Enter id to Search/Update")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }

    fun search(id: Long) : PlacemarkModel? {
        var foundPlacemark: PlacemarkModel? = placemarks.find{ p -> p.id == id }
        return foundPlacemark
    }

    fun searchPlacemark() {
        var searchId= getId()
        print(search(searchId))
    }

    fun dummyData() {
        placemarks.add(PlacemarkModel(1, "New York New York", "So Good They Named It Twice"))
        placemarks.add(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
        placemarks.add(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
    }

}
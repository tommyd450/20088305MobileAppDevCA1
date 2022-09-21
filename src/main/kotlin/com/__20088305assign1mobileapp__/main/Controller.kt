package com.__20088305assign1mobileapp__.main

import com.__20088305assign1mobileapp__.animal.Animal
class controller {
    val animalList = ArrayList<Animal>()
    fun addPlacemark(species: String, appearence: String, location: String,img: String) : Boolean{
        var newAnimal = Animal(animalList.size.toLong(),species,appearence,location,img)
        println("Add Animal")
        println()

        if (species.isNotEmpty() && appearence.isNotEmpty()) {

            animalList.add(newAnimal.copy())
            return true
        }
        else
            return false
    }

    fun updatePlacemark(searchID: Int) : String {
        println("Update Placemark")
        println()
        listPlacemarks()
        var searchId = getId()
        val animal = search(searchId)

        if(animal != null) {
            // Ask the user for new details here
            print("Old title ="+animal.species)
            print("Enter a new Title : ")
            //aPlacemark.title = readLine()!!
            print("Old title ="+animal.appearance)
            print("Enter a New Description : ")
            //aPlacemark.description = readLine()!!
        }
        else
            println("Placemark Not Updated...")
        return ""
    }

    fun listPlacemarks():String {
        println("You Chose List All Placemarks")
        println()
        var list : String = ""
        animalList.forEach{ list+=it.toString()+"\n"}
        return list
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

    fun search(id: Long) : Animal? {
        var foundAnimal: Animal? = animalList.find{ p -> p.id == id }
        return foundAnimal
    }

    fun searchPlacemark() {
        var searchId= getId()
        print(search(searchId))
    }

    fun dummyData() {
        animalList.add(Animal(1, "Dog", "Dog Like Yolk"))
        animalList.add(Animal(2, "Cat", "Cat Adjacent"))
        animalList.add(Animal(3, "RabbitDog", "An unholy abomination"))
    }

}
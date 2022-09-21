package com.__20088305assign1mobileapp__.main

import com.__20088305assign1mobileapp__.animal.Animal
class Controller {
    val animalList = ArrayList<Animal>()
    fun addAnimal(species: String, appearence: String, location: String,img: String) : Boolean{
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

    fun updateAnimal(searchID: Int) : String {
        println("Update Placemark")
        println()
        var searchId = getId()
        val animal = search(searchId)

        if(animal != null) {
            // Ask the user for new details here
            print("Old title ="+animal.species)
            print("Enter a new Title : ")
            print("Old title ="+animal.appearance)
            print("Enter a New Description : ")
        }
        else
            println("Placemark Not Updated...")
        return ""
    }

    fun listAnimals():String {
        var list : String = ""
        animalList.forEach{ list+=it.toString()+"\n"}
        print(list)
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

    fun searchAnimals() {
        var searchId= getId()
        print(search(searchId))
    }

    fun dummyData() {
        animalList.add(Animal(1, "Dog", "Dog Like Yolk"))
        animalList.add(Animal(2, "Cat", "Cat Adjacent"))
        animalList.add(Animal(3, "RabbitDog", "An unholy abomination"))
    }

}
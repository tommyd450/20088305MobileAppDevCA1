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

    fun updateAnimal(searchID: Long,species: String,appearence: String,location: String,img: String) : Boolean {
        println("Update Placemark")
        println()
        val animal = search(searchID)

        if(animal != null) {
            for(i in animalList)
            {
                if(i == animal)
                {
                    i.species=species
                    i.appearance=appearence
                    i.location=location
                    i.img=img
                    return true
                }
            }
        }
            return false
    }

    fun listAnimals():String {
        var list : String = "\n"
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

    fun deleteAnimal(animal: Animal?){
        for(i in animalList)
        {
            if(i == animal)
            {
                animalList.remove(i)
            }
        }
    }

    fun dummyData() {
        animalList.add(Animal(1, "Dog", "Dog Like Yolk"))
        animalList.add(Animal(2, "Cat", "Cat Adjacent"))
        animalList.add(Animal(3, "RabbitDog", "An unholy abomination"))
    }

}
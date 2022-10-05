package com.__20088305assign1mobileapp__.main
import com.__20088305assign1mobileapp__.animal.Animal
import com.__20088305assign1mobileapp__.animal.AnimalDatabase

val animalList = AnimalDatabase()
class Controller {


    fun addAnimal(species: String, appearence: String, location: String,img: String) : Boolean{
        print(animalList.animals.size.toLong())
        val newAnimal = Animal(animalList.animals.size.toLong(),species,appearence,location,img)
        if(animalList.create(newAnimal)){
            return true
        }
        return false
    }

    fun updateAnimal(searchID: Long,species: String,appearence: String,location: String,img: String) : Boolean {
        val animal = Animal(searchID,species,appearence,location,img)
        if(animalList.update(animal)) {
            return true
        }
        return false
    }

    fun listAnimals():String {
        var list = "\n"
        animalList.dbToList()
        animalList.findAll().forEach{ list+=it.toString()+"\n"}
        return list
    }

    fun search(id: Long): Animal? {
        return animalList.findAll().find { p -> p.id == id }
    }

    fun deleteAnimal(animal: Animal?) : Boolean {
        if (animalList.delete(animal) && !animalList.findAll().contains(animal)){
            return true
        }
        return false
    }


}
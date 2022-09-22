package com.__20088305assign1mobileapp__.main

import com.__20088305assign1mobileapp__.animal.Animal
import com.__20088305assign1mobileapp__.animal.AnimalList
import com.__20088305assign1mobileapp__.animal.AnimalStore

val animalList = AnimalList()
class Controller {


    fun addAnimal(species: String, appearence: String, location: String,img: String) : Boolean{
        var newAnimal = Animal(animalList.animals.size.toLong(),species,appearence,location,img)
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
        var list : String = "\n"
        animalList.findAll().forEach{ list+=it.toString()+"\n"}
        print(list)
        return list
    }


    fun search(id: Long) : Animal? {
        var foundAnimal: Animal? = animalList.findAll().find{ p -> p.id == id }
        return foundAnimal
    }

    /*
    fun searchAnimals() {
        var searchId= getId()
        print(search(searchId))
    }*/

    fun deleteAnimal(animal: Animal?) : Boolean {
        if (animalList.delete(animal) && !animalList.findAll().contains(animal)){
            return true
        }
        return false
    }

    fun dummyData() {
        //animalList.add(Animal(1, "Dog", "Dog Like Yolk"))
        //animalList.add(Animal(2, "Cat", "Cat Adjacent"))
        //animalList.add(Animal(3, "RabbitDog", "An unholy abomination"))
    }

}
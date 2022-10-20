package com.__20088305assign1mobileapp__.main
import com.__20088305assign1mobileapp__.animal.Animal
import com.__20088305assign1mobileapp__.animal.AnimalDatabase


class Controller {
    var entryNo =0
    val animalList = AnimalDatabase()
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

    fun saveAllAnimalsToDb() : Boolean
    {
        if(animalList.saveAllToDatabase())
        {
            return true
        }
        return false
    }

    fun loadAnimalsFromDb() : Boolean
    {
        if(animalList.dbToList()) {
            return true
        }
        return false
    }

    fun nextEntry() : Animal?
    {
        if(entryNo+1 <= animalList.animals.size-1) {
            entryNo++
            return animalList.animals.get(entryNo)
        }
        return null
    }
    fun prevEntry() : Animal?
    {
        if (entryNo - 1 < 0) {
            entryNo--
            return animalList.animals.get(entryNo)
        }
        return animalList.animals.get(entryNo)
    }
}
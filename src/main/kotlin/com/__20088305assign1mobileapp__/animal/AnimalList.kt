package com.__20088305assign1mobileapp__.animal

import com.__20088305assign1mobileapp__.main.animalList


class AnimalList : AnimalStore {

        val animals = ArrayList<Animal>()

        override fun findAll(): List<Animal> {
            return animals
        }

        override fun findOne(id: Long) : Animal? {
            var foundAnimal: Animal? = animals.find { p -> p.id == id }
            return foundAnimal
        }

        override fun create(animal: Animal) : Boolean{
            animals.add(animal)
            if (animals.contains(animal))
            {
                return true
            }
            return false
        }

        override fun update(animal: Animal) : Boolean{
            var foundAnimal = findOne(animal.id)
            if (foundAnimal != null) {
                foundAnimal.species=animal.species
                foundAnimal.appearance=animal.appearance
                foundAnimal.location=animal.location
                foundAnimal.img=animal.img
                return true
            }
            return false
        }

        override fun delete(animal: Animal?): Boolean{
            for(i in animals)
            {
                if(i == animal)
                {
                    animals.remove(i)
                    return true
                }
            }
            return false
        }

        /*internal fun logAll() {
            animals.forEach { logger.info("${it}") }
        }*/
    }

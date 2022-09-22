package com.__20088305assign1mobileapp__.animal

interface AnimalStore {
        fun findAll(): List<Animal>
        fun findOne(id: Long): Animal?
        fun create(animal: Animal) : Boolean
        fun update(animal: Animal) : Boolean
        fun delete(animal: Animal?) : Boolean
}
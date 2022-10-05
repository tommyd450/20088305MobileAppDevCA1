package com.__20088305assign1mobileapp__.animal

import com.__20088305assign1mobileapp__.main.animalList
import java.sql.*
class animalDatabase: AnimalStore {
    val animals = ArrayList<Animal>()

    fun dbToList(): String {
        animals.clear()
        try {
            val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileappdb", "root", "")
            val st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
            val rs: ResultSet
            rs = st.executeQuery("select * from `Animals`")
            while (rs.next()){
                var id =  rs.getString("id")
                var species = rs.getString("species")
                var appearence = rs.getString("appearence")
                var location = rs.getString("location")
                var img = rs.getString("img")
                var newAnimal = Animal(id.toLong(),species,appearence,location,img)
                animals.add(newAnimal)
            }
        }catch (e:SQLException)
        {
            println(e)
        }
        return ""
    }

    override fun findAll(): List<Animal> {

        return animals
    }

    override fun findOne(id: Long) : Animal? {
        var foundAnimal: Animal? = animals.find { p -> p.id == id }
        return foundAnimal
    }

    override fun create(animal: Animal) : Boolean{
        try {
            val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileappdb", "root", "")
            var query: String = "INSERT INTO `animals`(species,appearence,location,img) VALUES("+" '"+animal.species+"' , '"+animal.appearance+"' , '"+animal.location+"', '"+animal.img+"')";
            var stm : PreparedStatement = con.prepareStatement(query)
            stm.execute()
            if (animals.contains(animal))
            {
                return true
            }
        }catch (e:SQLException){
            println(e)
            println("SQL Problem")
        }
        return false
    }

    override fun update(animal: Animal) : Boolean{
        try {
            var foundAnimal = findOne(animal.id)
            if (foundAnimal != null) {
                foundAnimal.species=animal.species
                foundAnimal.appearance=animal.appearance
                foundAnimal.location=animal.location
                foundAnimal.img=animal.img
                val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileappdb", "root", "")
                var query: String = "UPDATE `animals` SET `id`='"+animal.id+"',`species`='"+animal.species+"',`appearence`='"+animal.appearance+"',`location`='"+animal.location+"',`img`='"+animal.img+"' WHERE `id` = '"+animal.id+"'"
                var stm : PreparedStatement = con.prepareStatement(query)
                stm.execute();
                return true
            }else
            {
                println("Failed to add animal to the database/List.")
                return false
            }
        }catch (e: SQLException)
        {
            println(e)
            println("SQL Error")
        }
        return false
    }

    override fun delete(animal: Animal?): Boolean{
            if(animal!=null && findOne(animal.id)==animal) {
                animals.removeAt(animals.indexOf(animal))
                return true
            }
        return false
    }
}
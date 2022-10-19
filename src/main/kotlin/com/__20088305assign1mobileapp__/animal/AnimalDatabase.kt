package com.__20088305assign1mobileapp__.animal

import java.sql.*
class AnimalDatabase: AnimalStore {
    val animals = ArrayList<Animal>()

    fun dbToList(): Boolean {
        animals.clear()
        try {
            val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileappdb", "root", "")
            val st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
            val rs = st.executeQuery("select * from `animals`")
            while (rs.next()){
                val id =  rs.getString("id")
                val species = rs.getString("species")
                val appearence = rs.getString("appearence")
                val location = rs.getString("location")
                val img = rs.getString("img")
                val newAnimal = Animal(id.toLong(),species,appearence,location,img)
                animals.add(newAnimal)
            }
            return true
        }catch (e:SQLException)
        {
            println(e)
        }
        return false
    }

    fun saveAllToDatabase():Boolean
    {
        // Wipes and Resaves all to the Database running on WAMP
        try {
            val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileappdb", "root", "")
            var query: String = "DELETE FROM `animals`"
            var stm : PreparedStatement = con.prepareStatement(query)
            stm.execute()
            if (animals.isNotEmpty()){
                animals.forEach {
                    query = "INSERT INTO `animals`(species,appearence,location,img) VALUES("+" '"+it.species+"' , '"+it.appearance+"' , '"+it.location+"', '"+it.img+"')"
                    stm = con.prepareStatement(query)
                    stm.execute()
                }
                return true
            }
        }catch (e:SQLException)
        {
            print(e.stackTrace)
        }
        return false
    }

    override fun findAll(): List<Animal> {
        return animals
    }

    override fun findOne(id: Long): Animal? {
        return animals.find { p -> p.id == id }
    }

    override fun create(animal: Animal) : Boolean{
        try {
            val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileappdb", "root", "")
            val query: String = "INSERT INTO `animals`(species,appearence,location,img) VALUES("+" '"+animal.species+"' , '"+animal.appearance+"' , '"+animal.location+"', '"+animal.img+"')"
            val stm : PreparedStatement = con.prepareStatement(query)
            stm.execute()
            dbToList()
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
            val foundAnimal = findOne(animal.id)
            if (foundAnimal != null) {
                foundAnimal.species=animal.species
                foundAnimal.appearance=animal.appearance
                foundAnimal.location=animal.location
                foundAnimal.img=animal.img
                val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileappdb", "root", "")
                val query: String = "UPDATE `animals` SET `id`='"+animal.id+"',`species`='"+animal.species+"',`appearence`='"+animal.appearance+"',`location`='"+animal.location+"',`img`='"+animal.img+"' WHERE `id` = '"+animal.id+"'"
                val stm : PreparedStatement = con.prepareStatement(query)
                stm.execute()
                return true
            }else
            {
                println("Failed to Update the Animal/It does not exist in the database.")
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
        if (animal!= null  && animals.contains(animal))
        {
            try
            {
                val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileappdb", "root", "")
                val query: String = "DELETE FROM `animals` WHERE `id`='"+animal.id+"'"
                val stm : PreparedStatement = con.prepareStatement(query)
                stm.execute()
                animals.remove(animal)
                println("Animal Deleted Successfully")
            }
            catch(e: SQLException)
            {
                println(e)
            }
            return true
        }else {
            println("Animal Does not exist Try again")
            return false
        }
    }

}
package com.__20088305assign1mobileapp__.view

import javafx.scene.control.TextField
import com.__20088305assign1mobileapp__.app.Styles
import com.__20088305assign1mobileapp__.main.Controller
import javafx.scene.control.TextArea

import tornadofx.*

class MainView : View("Hello TornadoFX") {
    var cont = Controller() //Instantiates Controller
    var speciesField: TextField by singleAssign() //Dont know what singleAssign does look up
    var appearenceField: TextField by singleAssign()
    var locationField: TextField by singleAssign()
    var imageField: TextField by singleAssign()
    var updateById: TextField by singleAssign()
    var updateSpecies: TextField by singleAssign()
    var updateAppearence: TextField by singleAssign()
    var updateLocation: TextField by singleAssign()
    var updateImg: TextField by singleAssign()
    var deleteById: TextField by singleAssign()
    var displayArea: TextArea by singleAssign()
    var displayArea2 : TextArea by singleAssign()

    override val root = hbox {
        form {
            fieldset {// This Field Set is for the adding of animal objects
                label("Add New Animal") {
                    addClass(Styles.heading)
                }
                field("Animal: ")
                {
                    speciesField = textfield()
                }
                field("Appearence: ")
                {
                    appearenceField = textfield()
                }
                field("Location: ")
                {
                    locationField = textfield()
                }
                field("Image Link: ")
                {
                    imageField = textfield()
                }
                buttonbar {
                    button("Add"){
                        action {
                            print(speciesField.text)
                            print(appearenceField.text)
                            cont.addAnimal(speciesField.text,appearenceField.text,locationField.text,imageField.text)
                        }
                    }
                    button("List") {
                        action {
                            displayArea.text+= cont.listAnimals()+"\n"
                        }
                    }
                    button("Save"){
                        action{
                            if(cont.saveAllAnimalsToDb())
                            {
                                displayArea.text += "Successfully Saved\n"
                            }else
                            {
                                displayArea.text += "Failed To Save\n"
                            }
                        }
                    }
                    button("Load"){
                        action{
                            if(cont.loadAnimalsFromDb())
                            {
                                displayArea.text += "Successfully Loaded\n"
                            }else{
                                displayArea.text += "Failed To Load\n"
                            }
                        }
                    }
                }

            }
            fieldset {
                label("Delete Animal")
                field("Delete By ID") {
                    deleteById = textfield()
                }
                button("Delete") {
                    action{
                        try{
                            //cont.dummyData()
                            cont.deleteAnimal(cont.search(deleteById.text.toLong()))
                            displayArea.text += cont.listAnimals()+"\n"
                        }catch (e: NumberFormatException){
                            displayArea.text("\n Borked")
                        }

                    }
                }
            }
            fieldset {// Updating Animal Field
                label("Update Animal")
                field("Animal to Update Id") {
                    updateById = textfield()
                }
                field("New Species") {
                    updateSpecies = textfield()
                }
                field("New Appearence") {
                    updateAppearence = textfield()
                }
                field("New Location") {
                    updateLocation = textfield()
                }
                field("New Img") {
                    updateImg = textfield()
                }
                button("Update") {
                    action{
                        try{
                            cont.search(updateById.text.toLong())
                            if(cont.updateAnimal(updateById.text.toLong(),updateSpecies.text,updateAppearence.text,updateLocation.text,updateImg.text))
                            {
                                print("Great Success")
                            }

                        }catch (e: NumberFormatException){

                        }

                    }
                }
            }

        }
        hbox {
            displayArea = textarea()
            displayArea.prefHeight(20.00)
            displayArea.prefWidth(20.00)
            displayArea.maxHeight = 700.0;

        }
    }
}
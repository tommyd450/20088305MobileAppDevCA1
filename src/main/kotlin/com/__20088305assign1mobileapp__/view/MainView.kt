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
    var deleteById: TextField by singleAssign()
    var displayArea: TextArea by singleAssign()

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
                            cont.addAnimal(speciesField.text,appearenceField.text,"","")
                        }
                    }
                    button("List") {
                        action {
                            displayArea.text(cont.listAnimals())
                        }
                    }
                }

            }
            fieldset {
                label("Delete Animal")
                field("Delete By ID")
                button("Delete") {
                    action{
                        //add delete stuff
                    }
                }
            }

        }
        hbox{
            displayArea = textarea()
            displayArea.prefHeight(20.00)
            displayArea.prefWidth(20.00)

        }
    }
}
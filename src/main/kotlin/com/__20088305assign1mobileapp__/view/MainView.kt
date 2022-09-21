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
    var displayArea: TextArea by singleAssign()
    override val root = hbox {
        form {
            fieldset {
                label(title) {
                    addClass(Styles.heading)
                }
                field("Animal")
                {
                    speciesField = textfield()
                }

                field("Appearence")
                {
                    appearenceField = textfield()
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
                            displayArea.text = cont.listAnimals()

                        }
                    }
                }
            }
        }
        hbox{
            displayArea = textarea {  }
        }
    }
}
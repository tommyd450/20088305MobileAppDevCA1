package com.__20088305assign1mobileapp__.view

import javafx.scene.control.TextField
import com.__20088305assign1mobileapp__.app.Styles
import com.__20088305assign1mobileapp__.main.controller
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    var firstNameField: TextField by singleAssign()
    var lastNameField: TextField by singleAssign()
    override val root = hbox {
        form {
            fieldset {
                label(title) {
                    addClass(Styles.heading)
                }
                field("Animal")
                {
                    firstNameField = textfield()
                }

                buttonbar {
                    button("Add")
                }
                field("Animal")
                {
                    textfield {  "Yep"}
                }

                buttonbar {
                    button("Add"){
                        action {
                            print(firstNameField.text)
                        }
                    }
                }
            }
        }
        hbox{
            textarea {  }
        }
    }
}
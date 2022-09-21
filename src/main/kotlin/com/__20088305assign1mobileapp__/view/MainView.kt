package com.__20088305assign1mobileapp__.view


import com.__20088305assign1mobileapp__.app.Styles
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root = hbox {
        form {
            fieldset {
                label(title) {
                    addClass(Styles.heading)
                }
                field("Animal")
                {
                    textfield {  }
                }

                buttonbar {
                    button("Add")
                }
                field("Animal2")
                {
                    textfield {  }
                }

                buttonbar {
                    button("Add")
                }
            }
        }
        hbox{
            textarea {  }
        }
    }
}
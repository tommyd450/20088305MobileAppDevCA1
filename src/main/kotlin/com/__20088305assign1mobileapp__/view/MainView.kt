package com.__20088305assign1mobileapp__.view

import javafx.scene.control.TextField
import com.__20088305assign1mobileapp__.app.Styles
import com.__20088305assign1mobileapp__.main.Controller
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.image.Image
import javafx.scene.image.ImageView

import tornadofx.*
import java.lang.NullPointerException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainView : View("Animal Spotting App") {
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
    var viewName : Label by singleAssign()
    var viewAppearence : Label by singleAssign()
    var viewLocation : Label by singleAssign()
    var viewImageLink : ImageView by singleAssign()
    var viewId : Label by singleAssign()
    lateinit var imageDisplay : ImageView

    override val root = hbox {

        form {
            style = "-fx-border-color: black;\n" +
                    "-fx-border-insets: 5;\n" +
                    "-fx-border-width: 1;\n" +
                    "-fx-border-style: solid;\n";
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
                            cont.deleteAnimal(cont.search(deleteById.text.toLong()))
                            displayArea.text += cont.listAnimals()+"\n"
                        }catch (e: NumberFormatException){

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
        vbox{
            style = "-fx-border-color: black;\n" +
                    "-fx-border-insets: 5;\n" +
                    "-fx-border-width: 1;\n" +
                    "-fx-border-style: solid;\n";
            prefWidth = 300.0
            try{
                padding = Insets(50.0,10.0,20.0,20.0)
                viewImageLink = imageview()
                viewImageLink.maxHeight(100.00)
                viewImageLink.maxWidth(100.00)
                viewImageLink.prefHeight(100.0)
                viewImageLink.prefWidth(100.0)
                viewImageLink.fitHeight=300.0
                viewImageLink.fitWidth=300.0
                viewImageLink.preserveRatioProperty()

                //viewImageLink.isPreserveRatio

                fieldset {
                    padding = Insets(20.0, 10.0, 20.0, 20.0)
                    viewId = label("Id: "){}
                    viewName = label("Name:") {}
                    viewLocation = label("Location:") {}
                    viewAppearence = label("Appearence:") {}

                    button("Next") {
                        action {
                            try {
                                var cycleAn = cont.nextEntry()
                                print(cycleAn.toString())
                                if (cycleAn != null) {
                                    viewId.text = "Id : " + cycleAn.id.toString()
                                    viewName.text = "Name : " + cycleAn.species
                                    viewAppearence.text = "Location : " + cycleAn.appearance
                                    viewLocation.text = "Appearence : " + cycleAn.location
                                    updateById.text = cycleAn.id.toString()
                                    updateSpecies.text = cycleAn.species
                                    updateAppearence.text = cycleAn.appearance
                                    updateImg.text = cycleAn.img
                                    updateLocation.text = cycleAn.location
                                    var url = URL(cycleAn.img)
                                    var urlCon = url.openConnection() as HttpURLConnection
                                    var response = urlCon.responseCode
                                    if (response != 404) {
                                        var image = Image(cycleAn.img, 300.0, 300.0, true, true)
                                        viewImageLink.image = image
                                        println(viewImageLink.image.url)
                                    }
                                }
                            } catch (e: NullPointerException) {
                                //e.printStackTrace()
                            } catch (e: MalformedURLException) {
                                viewImageLink.image = Image(System.getProperty("user.dir")+"\\NoImgFound.png")
                            }
                        }
                    }
                    button("Previous") {
                        action {
                            try {
                                var cycleAn = cont.prevEntry()
                                print(cycleAn.toString())
                                if (cycleAn != null) {
                                    viewId.text = "Id : " + cycleAn.id.toString()
                                    viewName.text = "Name : " + cycleAn.species
                                    viewAppearence.text = "Location : " + cycleAn.appearance
                                    viewLocation.text = "Appearence : " + cycleAn.location
                                    updateById.text = cycleAn.id.toString()
                                    updateSpecies.text = cycleAn.species
                                    updateAppearence.text = cycleAn.appearance
                                    updateImg.text = cycleAn.img
                                    updateLocation.text = cycleAn.location
                                    var url = URL(cycleAn.img)
                                    var urlCon = url.openConnection() as HttpURLConnection
                                    var response = urlCon.responseCode
                                    if (response != 404) {
                                        var image = Image(cycleAn.img, 300.0, 300.0, true, true)
                                        viewImageLink.image = image
                                        //println(viewImageLink.image.url)
                                    }
                                }
                            } catch (e: NullPointerException) {
                                //e.printStackTrace()
                            } catch (e: MalformedURLException) {
                                viewImageLink.image = Image(System.getProperty("user.dir")+"\\NoImgFound.png")
                            }
                        }
                    }
                }
            }catch(e:MalformedURLException)
            {
                e.printStackTrace()
            }


        }
        hbox {
            displayArea = textarea()
            displayArea.prefHeight(20.00)
            displayArea.prefWidth(20.00)
            displayArea.maxHeight = 500.0;

        }
    }
}
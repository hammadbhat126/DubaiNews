package io.realworld.uaenews

object ColorPicker{

    var colors =
        arrayOf("#28B5B5","#CD5C5C", "#F08080" , "#FA8072", "#E9967A" , "#FFA07A", "#28B5B5","#CD5C5C", "#F08080" , "#FA8072", "#E9967A" , "#FFA07A" , "#28B5B5","#CD5C5C", "#F08080" , "#FA8072", "#E9967A" , "#FFA07A")


    var colorIndex = 1
    fun getColor():String{
        return colors[colorIndex++ % colors.size]
    }
}
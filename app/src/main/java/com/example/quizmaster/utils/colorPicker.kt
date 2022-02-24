package com.example.quizmaster.utils

object colorPicker {
    var colors = arrayOf(
        "#808080",
        "#800000",
        "#808000",
        "#00FF00",
        "#00FFFF",
        "#008080",
        "#000080",
        "#FF00FF",
        "#800080",
        "#CD5C5C",
        "#F08080",
        "#FA8072",
        "#E9967A",
        "#FFA07A "
    )
    var currentColorIndex = 0

    fun getColor(): String {
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}
package com.softworkshub.a7minworkout

import android.media.Image

class ExerciseModel(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isSelected: Boolean ,
    private var isCompleted: Boolean ,
) {

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return name
    }

    fun setImage(image: Int) {
        this.image = image
    }

    fun getImage(): Int {
        return image
    }

    fun setIsSelected(isSelected: Boolean) {
        this.isSelected = isSelected
    }

    fun getIsSelected(): Boolean {
        return isSelected
    }

    fun setIsCompleted(isCompleted: Boolean) {
        this.isCompleted = isCompleted
    }

    fun getIsCompleted(): Boolean {
        return isCompleted
    }
}

package com.softworkshub.a7minworkout

object Constants {

    fun defaultExerciseList():ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val JumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.plancks_img_vector,
            false,
            false)
        exerciseList.add(JumpingJacks)


        val placks = ExerciseModel(
            2,
            "placks ",
            R.drawable.plancks_img_vector,
            false,
            false)
        exerciseList.add(placks)

        val pushups = ExerciseModel(
            3,
            "pushups",
            R.drawable.plancks_img_vector,
            false,
            false)
        exerciseList.add(pushups)

        val downtoEarth = ExerciseModel(
            4,
            "downtoEarth",
            R.drawable.plancks_img_vector,
            false,
            false)
        exerciseList.add(downtoEarth)

        return exerciseList
    }
}
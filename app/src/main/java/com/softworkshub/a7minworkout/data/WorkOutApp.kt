package com.softworkshub.a7minworkout.data

import android.app.Application


class WorkOutApp : Application() {

    val db : HistoryDatabase by lazy {
        HistoryDatabase.getInstance(this)
    }
}
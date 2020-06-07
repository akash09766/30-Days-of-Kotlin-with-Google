package com.skylightdevelopers.android.moviemaasala.app.util

import java.io.IOException

class NoConnectivityException(val msg: String) : IOException() {
    override val message: String?
        get() = msg
}
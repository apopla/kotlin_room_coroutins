package com.avanade.tech1.screens.details

import android.arch.lifecycle.ViewModel
import com.avanade.tech1.Tech1App

/**
 * Created by Paulina on 2018-08-09.
 */
class DetailsViewModel: ViewModel() {


    init {
        Tech1App.appComponent.inject(this)

    }

    fun authorClicked(authorId: Int) {

    }

}
package com.avanade.tech1.screens.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.avanade.tech1.R

/**
 * Created by Paulina on 2018-08-09.
 */
class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

    }
}
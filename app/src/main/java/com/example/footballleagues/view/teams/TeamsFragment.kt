package com.example.footballleagues.view.teams

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.footballleagues.view.BaseFragment

class TeamsFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lid = TeamsFragmentArgs.fromBundle(arguments!!).leagueId
        val parent = activity as? AppCompatActivity
        val toolbar = parent?.supportActionBar
        toolbar?.title = TeamsFragmentArgs.fromBundle(arguments!!).leagueName

    }
}
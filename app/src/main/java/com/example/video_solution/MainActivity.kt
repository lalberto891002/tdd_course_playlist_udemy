package com.example.video_solution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.video_solution.playlist.PlayListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
           supportFragmentManager.beginTransaction()
                 .add(R.id.container, PlayListFragment.newInstance())
                 .commit()
        }

    }
}
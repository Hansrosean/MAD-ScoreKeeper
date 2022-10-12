package com.mad.scorekeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.lang.String


class MainActivity : AppCompatActivity() {

    private lateinit var imgButtonPlusTeam1: ImageButton
    private lateinit var imgButtonMinusTeam1: ImageButton
    private lateinit var imgButtonPlusTeam2: ImageButton
    private lateinit var imgButtonMinusTeam2: ImageButton
    private lateinit var tvScoreTeam1: TextView
    private lateinit var tvScoreTeam2: TextView
    private var score1: Int = 0
    private var score2: Int = 0

    companion object {
        const val SCORE_STATE_1 = "score_state_1"
        const val SCORE_STATE_2 = "score_state_2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgButtonPlusTeam1 = findViewById(R.id.increaseTeam1)
        imgButtonMinusTeam1 = findViewById(R.id.decreaseTeam1)
        imgButtonPlusTeam2 = findViewById(R.id.increaseTeam2)
        imgButtonMinusTeam2 = findViewById(R.id.decreaseTeam2)
        tvScoreTeam1 = findViewById(R.id.score_1)
        tvScoreTeam2 = findViewById(R.id.score_2)

        imgButtonPlusTeam1.setOnClickListener {
            decreaseScore(it)
        }

        imgButtonMinusTeam1.setOnClickListener {
            decreaseScore(it)
        }

        imgButtonPlusTeam2.setOnClickListener {
            decreaseScore(it)
        }

        imgButtonMinusTeam2.setOnClickListener {
            decreaseScore(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.day_mode)
        } else {
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.night_mode)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (item.itemId == R.id.night_mode) {
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        return true
    }

    private fun decreaseScore(view: View) {
        when (view.id) {
            R.id.decreaseTeam1 -> {
                score1--
                if (score1 <= 0) {
                    score1 = 0
                    tvScoreTeam1.text = String.valueOf(score1)
                } else {
                    tvScoreTeam1.text = String.valueOf(score1)
                }
            }
            R.id.increaseTeam1 -> {
                score1++
                tvScoreTeam1.text = String.valueOf(score1)
            }
            R.id.decreaseTeam2 -> {
                score2--
                if (score2 <= 0) {
                    score2 = 0
                    tvScoreTeam2.text = String.valueOf(score2)
                } else {
                    tvScoreTeam2.text = String.valueOf(score2)
                }
            }
            R.id.increaseTeam2 -> {
                score2++
                tvScoreTeam2.text = String.valueOf(score2)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SCORE_STATE_1, score1)
        outState.putInt(SCORE_STATE_2, score2)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val getScoreTeam1State = savedInstanceState.getInt(SCORE_STATE_1, 0)
        val getScoreTeam2State = savedInstanceState.getInt(SCORE_STATE_2, 0)

        score1 = getScoreTeam1State
        score2 = getScoreTeam2State

        tvScoreTeam1.text = score1.toString()
        tvScoreTeam2.text = score2.toString()
    }

}
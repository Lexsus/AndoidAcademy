package ru.lucass.appname

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity()/*, FragmentMovieList.ClickListener*/{

    private  var fragmentMovieDetails:FragmentMovieDetails?=null
    private  var fragmentMovieList: FragmentMovieList?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null)
        {
            supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.container,
                        FragmentMovieList.newInstance(),
                        FRAGMENT_MOVIE_TAG)
                    commit()
                }
        }
        else
        {
            fragmentMovieList = supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIE_TAG) as?  FragmentMovieList
            fragmentMovieDetails = supportFragmentManager.findFragmentByTag(FRAGMENT_DETAIL_TAG) as?  FragmentMovieDetails
            Log.d("","fragment create");
        }
    }

     fun nextfragment() {
        Log.d("MainActivity","nextfragment")
        //перреход в новый фрагмент
        supportFragmentManager.beginTransaction()
            .apply {
                replace(
                    R.id.container,
                    FragmentMovieDetails.newInstance(),
                    FRAGMENT_DETAIL_TAG
                    )
                addToBackStack(null)
                commit()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        const val FRAGMENT_MOVIE_TAG="MovieListFragment"
        const val FRAGMENT_DETAIL_TAG="MovieDetailFragment"
    }
}
package com.example.hellowworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlin.random.Random


/*
Android activities do hold some state by default without saving. However, this state is limited to the UI state of the activity's views.
For example, if you have an EditText and the user has entered some text into it, that text will be saved and restored automatically
if the activity is destroyed and recreated due to a configuration change (like a screen rotation).


One way to keep state is to overwrite onSaveInstanceState (Bundle) and using the Bundle object (just a map) keep the things we want to
populate again as part of onCreate(Bundle). The correct usecase for onSaveInstanceState is to keep some minimal state when app goes into
background and is killed by os and you want to keep some info for when the app is started back again by the user. Also Bundle has a size limit.


this example generates a random value and retains it on screen rotation. if we do not save it in onSaveInstanceState, it will be lost.
 */
class MainActivity : AppCompatActivity() {

    var someRandomString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("tag","inside onCreate")



        if(savedInstanceState != null){
            Log.i("onCreate","savedInstanceState is not null")
            // get the value from the bundle
            someRandomString = savedInstanceState.getString("key").toString()
        }else{
            someRandomString =  Random.nextInt(10000).toString()
        }

        findViewById<TextView>(R.id.textView).text = someRandomString

        //comment out above line and uncomment below line to see the difference
        //findViewById<TextView>(R.id.textView).text = Random.nextInt(10000).toString()
    }

    /*
        happens when we do screen rotate and auto-rotate is on.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("onSaveInstanceState","screen rotated")


        //outState.putString("key",someRandomString)

    }
}
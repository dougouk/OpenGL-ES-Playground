package com.dan190.myarapplication

import android.opengl.GLSurfaceView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var glView : MyGLSurfaceView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glView = MyGLSurfaceView(this)
        setContentView(glView)
    }
}

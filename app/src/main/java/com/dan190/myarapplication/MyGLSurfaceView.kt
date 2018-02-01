package com.dan190.myarapplication

import android.content.Context
import android.opengl.GLSurfaceView

/**
 * Created by Dan on 31/01/2018.
 */
class MyGLSurfaceView(context: Context) : GLSurfaceView(context) {
    private var renderer : MyGLRenderer
    init {

        // create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        renderer = MyGLRenderer()

        // set the renderer
        setRenderer(renderer)

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
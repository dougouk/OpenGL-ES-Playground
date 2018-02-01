package com.dan190.myarapplication

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.dan190.myarapplication.shapes.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by Dan on 31/01/2018.
 */
class MyGLRenderer : GLSurfaceView.Renderer {
    var triangle: Triangle? = null

    override fun onDrawFrame(gl: GL10?) {
        // redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        triangle?.draw()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // set the background frame color
        GLES20.glClearColor(0f, 0f, 0f, 1f)

        // draw a triangle
        triangle = Triangle()
    }

    companion object {
        fun loadShader(type: Int, shaderCode: String) : Int {
            // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
            // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
            val shader = GLES20.glCreateShader(type)

            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)

            return shader
        }

    }
}
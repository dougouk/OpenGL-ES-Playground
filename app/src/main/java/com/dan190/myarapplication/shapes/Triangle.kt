package com.dan190.myarapplication.shapes

import android.opengl.GLES20
import com.dan190.myarapplication.MyGLRenderer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * Created by Dan on 31/01/2018.
 */
class Triangle {
    private lateinit var vertexBuffer : FloatBuffer
    private var program : Int = 0

    val COORDS_PER_VERTEX = 3
    val triangleCoords : FloatArray = arrayOf(
            0.0f,  0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    ).toFloatArray()

    // Set color with red, green, blue and alpha (opacity) values
    val color = arrayOf( 0.63671875f, 0.76953125f, 0.22265625f, 1.0f ).toFloatArray()

    private val vertexShaderCode = "attribute vec4 vPosition;" +
    "void main() {" +
    "  gl_Position = vPosition;" +
    "}"

    private val fragmentShaderCode = (
    "precision mediump float;" +
    "uniform vec4 vColor;" +
    "void main() {" +
    "  gl_FragColor = vColor;" +
    "}")

    var positionHandle = 0
    var colorHandle = 0

    val vertexCount = triangleCoords.size / COORDS_PER_VERTEX
    val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    init {
        val bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.size * 4
        )

        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder())

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer()

        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords)

        // set the buffer to read the first coordinate
        vertexBuffer.position(0)

        val vertexShader = MyGLRenderer.Companion.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader = MyGLRenderer.Companion.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)

        // create empty OpenGL ES Program
        program = GLES20.glCreateProgram()

        // add the vertex shader to program
        GLES20.glAttachShader(program, vertexShader)

        // add the fragment shader to program
        GLES20.glAttachShader(program, fragmentShader)

        // creates OPENGL ES program executables
        GLES20.glLinkProgram(program)
    }

    fun draw() {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(program)

        // get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(program, "vPosition")

        // Prepare the triangle coordinate data
        GLES20.glEnableVertexAttribArray(positionHandle)

        // Enable a handle to the triangle vertices
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer)

        // get handle to fragment shader's vColor member
        colorHandle = GLES20.glGetUniformLocation(program, "vColor")

        // Set color for drawing the triangle
        GLES20.glUniform4fv(colorHandle, 1, color, 0)

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle)

    }
}
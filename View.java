package a1;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import graphicslib3D.GLSLUtils;

import java.nio.FloatBuffer;

import static com.jogamp.opengl.GL.GL_TRIANGLES;
import static com.jogamp.opengl.GL.GL_VERSION;
import static com.jogamp.opengl.GL2ES2.*;
import static com.jogamp.opengl.GL2ES3.GL_COLOR;

public class View implements GLEventListener {

    private GLCanvas myCanvas;
    private int rendering_program;
    private GLSLUtils util = new GLSLUtils();

    private ErrorHandling eh = new ErrorHandling();

    private int vao[] = new int[1];

    Package p = Package.getPackage("com.jogamp.opengl");

    Model m;

    public View(Model m){
        myCanvas = new GLCanvas();
        myCanvas.addGLEventListener(this);
        this.m = m;

    }

    public void display(GLAutoDrawable glAutoDrawable) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glUseProgram(rendering_program);

        float bkg[] = {0.0f, 0.0f, 0.0f, 1.0f};
        FloatBuffer bkgBuffer = Buffers.newDirectFloatBuffer(bkg);
        gl.glClearBufferfv(GL_COLOR, 0, bkgBuffer);

        m.update();

        int offset_loc_y = gl.glGetUniformLocation(rendering_program, "offsetY");
        gl.glProgramUniform1f(rendering_program, offset_loc_y, m.getY());

        int offset_loc_x = gl.glGetUniformLocation(rendering_program, "offsetX");
        gl.glProgramUniform1f(rendering_program, offset_loc_x, m.getX());

        int offset_size = gl.glGetUniformLocation(rendering_program, "sizeOffset");
        gl.glProgramUniform1f(rendering_program, offset_size, m.getSize());

        int color_stats = gl.glGetUniformLocation(rendering_program, "gradient");
        gl.glProgramUniform1f(rendering_program, color_stats, m.getGradient());

        gl.glDrawArrays(GL_TRIANGLES, 0, 3);

    }

    public void init(GLAutoDrawable glAutoDrawable){
        GL4 gl = (GL4) GLContext.getCurrentGL();

        System.out.println( "OpenGL: " + gl.glGetString(GL_VERSION));
        System.out.println( "JOGL: " + p.getImplementationVersion());
        System.out.println( "Java: " + System.getProperty("java.version"));

        rendering_program = createShaderProgram();
        gl.glGenVertexArrays(vao.length, vao, 0);
        gl.glBindVertexArray(vao[0]);

    }

    private int createShaderProgram(){
        int[] vertCompiled = new int[1];
        int[] fragCompiled = new int[1];
        int[] linked = new int[1];

        GL4 gl = (GL4) GLContext.getCurrentGL();

        String vshaderSource[] = util.readShaderSource("a1/vert.shader");
        String fshaderSource[] = util.readShaderSource("a1/frag.shader");
        int lengths[];

        int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
        int fShader = gl.glCreateShader(GL_FRAGMENT_SHADER);

        gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0);
        gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0);

        gl.glCompileShader(vShader);
        eh.checkOpenGLError();
        gl.glGetShaderiv(vShader, GL_COMPILE_STATUS, vertCompiled, 0);
        if(vertCompiled[0]==1){
            System.out.println("...vertex compilation success.");
        }
        else{
            System.out.println("...vertex compilation failed");
            eh.printShaderLog(vShader);
        }

        gl.glCompileShader(fShader);
        eh.checkOpenGLError();
        gl.glGetShaderiv(fShader, GL_COMPILE_STATUS, fragCompiled, 0);
        if(fragCompiled[0]==1){
            System.out.println("...fragment compilation success.");
        }
        else{
            System.out.println("...fragment compilation failed");
            eh.printShaderLog(fShader);
        }

        if((vertCompiled[0] != 1) || (fragCompiled[0] != 1)){
            System.out.println("/n Compilation error; return-flags");
            System.out.println(" vertCompiled = " + vertCompiled[0] +"; fragCompiled = " + fragCompiled[0]);
        }
        else{
            System.out.println("Successful compilation");
        }


        int vfprogram = gl.glCreateProgram();
        gl.glAttachShader(vfprogram, vShader);
        gl.glAttachShader(vfprogram, fShader);

        gl.glLinkProgram(vfprogram);
        eh.checkOpenGLError();
        gl.glGetProgramiv(vfprogram, GL_LINK_STATUS, linked, 0);
        if(linked[0] == 1){
            System.out.println("...linking succeeded");
        }
        else{
            System.out.println("...linking failed");
            eh.printProgramLog(vfprogram);
        }

        return vfprogram;
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){}
    public void dispose(GLAutoDrawable drawable){}

    public GLCanvas getMyCanvas(){
        return myCanvas;
    }
}

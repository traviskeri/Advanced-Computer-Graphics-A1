package a1;

public class Model {

    private float y = 0.0f;
    private float incY = 0.01f;

    private float x = 0.0f;

    private float size = 0.0f;
    private float sizeInc = 0.01f;

    private boolean isMoving = false;
    private boolean isRotating = false;

    private float gradient = 1.0f;

    /**
     * Updates the value of x and y if isMoving or isRotating is true.
     */
    public void update(){
        move();
        rotate();
    }


    /**
     * If isMoving is true each vertex moves up till it reaches the top then down till it reaches the bottom.
     * Then it repeats.
     */
    public void move(){
        if(isMoving()) {
            y += incY;
            if (y >= 1.0f) { incY = -0.01f;}
            if (y <= -1.0f) { incY = 0.01f;}
        }
    }

    /**
     * If isRotating is true the vertices are rotated around the origin.
     */
    public void rotate(){
        if(isRotating()){
            double tempX = x * Math.cos(0.1) - y * Math.sin(0.1);
            double tempY = y * Math.cos(0.1) + x * Math.sin(0.1);

            x = (float)tempX;
            y = (float)tempY;
        }

    }

    /**
     * If the input is pos increase the size by sizeInc, if neg decrease the size by sizeInc.
     * @param i float input from the mouse wheel.
     */
    public void changeSize(float i){
        if(i > 0){
            size = size + sizeInc;
        }
        if(i < 0 && size > -0.25f){
            size = size - sizeInc;
        }
    }

    /**
     * Returns the size offset for the vertices.
     * @return float size
     */
    public float getSize(){
        return this.size;
    }

    /**
     * Returns the y offset for the vertices.
     * @return float y
     */
    public float getY(){
        return this.y;
    }

    /**
     * Returns the x offset for the vertices.
     * @return float x
     */
    public float getX(){
        return this.x;
    }

    /**
     * Returns if the move button has been selected.
     * @return boolean isMoving.
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Sets the value of isMoving to moving.
     * @param moving boolean
     */
    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    /**
     * Returns if the rotate button has been selected.
     * @return boolean isRotating.
     */
    public boolean isRotating(){
        return isRotating;
    }

    /**
     * Sets the value of isRotating to rotating.
     * @param rotating boolean
     */
    public void setRotating(boolean rotating){
        isRotating = rotating;
    }

    /**
     * Flips the value of gradient from 0.0 to 1.0. Must me a float because it
     * needs to be sent to the shader code.
     */
    public void flipGradient(){
        if(gradient == 0.0f){gradient = 1.0f;}
        else gradient = 0.0f;
    }

    /**
     * Returns the value of gradient.
     * @return float gradient
     */
    public float getGradient(){
        return gradient;
    }
}

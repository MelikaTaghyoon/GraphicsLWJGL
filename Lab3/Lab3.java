/* Lab3.java
 * A simple scene consisting of the lower part of a house
 * 24/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [Ry(35) T(0,-0,-5)] House
 */
package Lab3;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of OpenGL calls to construct objects within the 3D scene
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * </ul>
 *
 * <p>Adapted from Mark Bernard's LWJGL NeHe samples
 *
 * @author Anthony Jones and Dan Cornford
 */
public class Lab3 extends GraphicsLab
{
    /**
     * display list id for the unit cube. Note that the initialised value given to a display list id attribute
     * is the id you _want_ to use in your code - you must therefore pass a valid (positive) display list id
     * to glNewList; an invalid value will result in an exception being thrown */
    private final int cubeList = 1;
    
    /** angle that the house is rotate to see the lighting better */
    private float houseRotationAngle= 35.0f;
    
    public static void main(String args[])
    {   new Lab3().run(WINDOWED,"Lab 3 - Construction",0.01f);
    }
    
    protected void initScene()
    {
        // build the unit cube display list for later use
        GL11.glNewList(cubeList,GL11.GL_COMPILE);
        {   drawUnitCube(Colour.RED,Colour.RED,Colour.GREEN,Colour.GREEN,Colour.ORANGE,Colour.GREEN);
        	drawUnitRoof(Colour.BLUE,Colour.GREEN, Colour.RED);
        }
        GL11.glEndList();
    }
    protected void checkSceneInput()
    {
      if(Keyboard.isKeyDown(Keyboard.KEY_R))
      {   houseRotationAngle += 1.0f * getAnimationScale(); // Make the house go around if the R key is pressed
          if (houseRotationAngle > 360.0f) // Wrap the angle back around into 0-360 degrees.
          {  houseRotationAngle = 0.0f;
          }
      }
    }
    
    protected void updateScene()
    {// empty
    }
    protected void renderScene()
    {
        // position the house
        GL11.glTranslatef(0.0f, -0.5f, -5.0f);
        // rotate the house a little so that we can see more of it
        GL11.glRotatef(houseRotationAngle, 0.0f, 1.0f, 0.0f);
        GL11.glScalef(0.8f, 0.8f, 0.8f);
        // draw the base of the house by calling the appropriate display list
        GL11.glCallList(cubeList);
    }
    protected void cleanupScene()
    {// empty
    }

    private void drawUnitRoof(Colour red, Colour second, Colour side) {
    	Vertex v1 = new Vertex(-0.5f, 0.5f, -0.5f);
		Vertex v2 = new Vertex(-0.5f, 0.5f, 0.5f);
		Vertex v3 = new Vertex(0.5f, 0.5f, 0.5f);
		Vertex v4 = new Vertex(0.5f,  0.5f, -0.5f);
		Vertex v5 = new Vertex(-0.5f, 1.0f, 0.0f);
		Vertex v6 = new Vertex(0.5f, 1.0f, 0.0f);
	 
		
		//front face
		GL11.glBegin(GL11.GL_POLYGON);{
			red.submit();
			v1.submit();
			v2.submit();
			v5.submit();
		}
		GL11.glEnd();
		
		//draw side 
		GL11.glBegin(GL11.GL_POLYGON);{
			side.submit();
			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();
		}
		GL11.glEnd();
		
		//back
		GL11.glBegin(GL11.GL_POLYGON);{

			red.submit();
			v3.submit();
			v4.submit();
			v6.submit();
		}
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_POLYGON);{

			side.submit();
			v2.submit();
			v3.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_POLYGON);{

			side.submit();
			v1.submit();
			v5.submit();
			v6.submit();
			v4.submit();
		}
		GL11.glEnd();
		
    }
    
    
    /**
     * Draws a unit cube using the given colours for its 6 faces
     * 
     * @param near   The colour for the cube's near face
     * @param far    The colour for the cube's far face
     * @param left   The colour for the cube's left face
     * @param right  The colour for the cube's right face
     * @param top    The colour for the cube's top face
     * @param bottom The colour for the cube's bottom face
     */
    private void drawUnitCube(Colour near, Colour far, Colour left, Colour right, Colour top, Colour bottom)
    {
        // the vertices for the cube (note that all sides have a length of 1)
        Vertex v1 = new Vertex(-0.5f, -0.5f,  0.5f);
        Vertex v2 = new Vertex(-0.5f,  0.5f,  0.5f);
        Vertex v3 = new Vertex( 0.5f,  0.5f,  0.5f);
        Vertex v4 = new Vertex( 0.5f, -0.5f,  0.5f);
        Vertex v5 = new Vertex(-0.5f, -0.5f, -0.5f);
        Vertex v6 = new Vertex(-0.5f,  0.5f, -0.5f);
        Vertex v7 = new Vertex( 0.5f,  0.5f, -0.5f);
        Vertex v8 = new Vertex( 0.5f, -0.5f, -0.5f);
        // draw the near face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            near.submit();

            v3.submit();
            v2.submit();
            v1.submit();
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            left.submit();

            v2.submit();
            v6.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            right.submit();

            v7.submit();
            v3.submit();
            v4.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            top.submit();

            v7.submit();
            v6.submit();
            v2.submit();
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            bottom.submit();

            v4.submit();
            v1.submit();
            v5.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            far.submit();

            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
        }
        GL11.glEnd();
        
    }
}

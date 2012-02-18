/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.r2012.OperatorControl;
import edu.wpi.first.wpilibj.Joystick;
import edu.arhs.first1100.r2012.OperatorControl.PS3Button;
/**
 *
 * @author Team1100
 */
public class PS3Controller
{
    Joystick ps;
    PS3Button triangle;
    PS3Button square;
    PS3Button circle;
    PS3Button cross;
    PS3Button leftT;
    PS3Button rightT;
    PS3Button rightB;
    PS3Button leftB;
    PS3Button dRight;
    PS3Button dLeft;
    PS3Button dTop;
    PS3Button dBottom;
        public PS3Controller(int channel)
        {
            ps = new Joystick(channel);
            triangle = new PS3Button(ps, 13);
            circle = new PS3Button(ps, 14);
            cross = new PS3Button(ps, 15);
            square = new PS3Button(ps, 16);
            leftT = new PS3Button(ps, 9);
            rightT = new PS3Button(ps, 10);
            leftB = new PS3Button(ps, 11);
            rightB = new PS3Button(ps, 12);
            dRight = new PS3Button(ps, 12);
            dLeft = new PS3Button(ps, 12);
            dTop = new PS3Button(ps, 12);
            dBottom = new PS3Button(ps, 12);
        }
        public boolean getTriangle()
        {
            return triangle.getState();
        }
        public boolean getSquare()
        {
            return square.getState();
        }
        public boolean getCross()
        {
            return cross.getState();
        }
        public boolean getCircle()
        {
            return circle.getState();
        }
        public boolean getLeftT()
        {
            return leftT.getState();
        }
        public boolean getrightT()
        {
            return rightT.getState();
        }
        public boolean getLeftB()
        {
            return leftB.getState();
        }
        public boolean getRightB()
        {
            return rightB.getState();
        }
        public boolean getDRight()
        {
            return dRight.getState();
        }
        public boolean getDBottom()
        {
            return dBottom.getState();
        }
         public boolean getDLeft()
        {
            return dLeft.getState();
        }
        public boolean getDTop()
        {
            return dTop.getState();
        }
}

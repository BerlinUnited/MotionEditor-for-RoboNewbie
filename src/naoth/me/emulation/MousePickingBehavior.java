/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package naoth.me.emulation;

import com.sun.j3d.utils.picking.behaviors.PickMouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
import com.sun.j3d.utils.picking.*;
import javax.media.j3d.*;
//import javax.vecmath.*;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author claas
 */
class MousePickingBehavior extends PickMouseBehavior implements MouseBehaviorCallback
{
  private static final double ROT_AMT = Math.PI / 90.0;
  private KneadEmulator parent = null;
  private PickResult[] picks = null;

  public MousePickingBehavior(Canvas3D canvas, BranchGroup root, Bounds bounds, KneadEmulator parent)
  {
    super( canvas , root , bounds );
    this.setSchedulingBounds(bounds);
    this.parent = parent;

    canvas.addMouseListener
    (
      new MouseListener()
      {
        public void mouseClicked(MouseEvent e)
        {}

        public void mousePressed(MouseEvent e)
        {}

        public void mouseReleased(MouseEvent e)
        {
          resetInfoText();
        }

        public void mouseEntered(MouseEvent e)
        {}

        public void mouseExited(MouseEvent e)
        {}
      }
    );

    canvas.addMouseMotionListener
    (
      new MouseMotionListener()
      {
        int lastX = 0;
        int lastY = 0;

        public void mouseDragged(MouseEvent e)
        {
          int dX = lastX - e.getX();
          int dY = lastY - e.getY();
          double rotA = 0;
          double rotB = 0;

          if(dX > 0)
          {
            rotA = -ROT_AMT;
          }
          else if(dX < 0)
          {
            rotA = ROT_AMT;
          }
          if(dY > 0)
          {
            rotB = -ROT_AMT;
          }
          else if(dY < 0)
          {
            rotB = ROT_AMT;
          }
          setTransform(rotA, rotB);
          lastX = e.getX();
          lastY = e.getY();

        }

        public void mouseMoved(MouseEvent e)
        {}


      }
    );

      pickCanvas.setMode(PickTool.BOUNDS);
  }

  private void setTransform(double angleA, double angleB)
  {
      TransformGroup tg = null;
      String name = "";

      if (picks != null)
      {
        int i = 0;
        if(picks.length > 1)
        {
          i = 1;
        }

        tg = (TransformGroup) picks[i].getNode(PickResult.TRANSFORM_GROUP);
        name = tg.getName();

        if(name != null)
        {
          if(name.equals("Head"))
          {
            parent.setHeadJoints(angleB, angleA);
          }
          if(name.equals("LUpperArm"))
          {
            parent.setShoulderJoints("L", angleB, angleA);
          }
          if(name.equals("RUpperArm"))
          {
            parent.setShoulderJoints("R", angleB, angleA);
          }
          if(name.equals("LLowerArm"))
          {
            parent.setElbowJoints("L", angleA, angleB);
          }
          if(name.equals("RLowerArm"))
          {
            parent.setElbowJoints("R", angleA, angleB);
          }
          if(name.equals("LHipYawPitch") || name.equals("RHipYawPitch") || name.equals("Chest"))
          {
            parent.setHipYawPitchJoints(angleA);
          }
          if(name.equals("LThigh") || name.equals("LHipRoll"))
          {
            parent.setHipRollJoint("L", angleA);
            parent.setHipPitchJoint("L", angleB);
          }
          if(name.equals("RThigh") || name.equals("RHipRoll"))
          {
            parent.setHipRollJoint("R", angleA);
            parent.setHipPitchJoint("R", angleB);
          }
          if(name.equals("LTibia"))
          {
            parent.setKneePitchJoint("L", angleB);
          }
          if(name.equals("RTibia"))
          {
            parent.setKneePitchJoint("R", angleB);
          }
          if(name.equals("LFoot") || name.equals("LAnkle"))
          {
            parent.setAnkleRollJoint("L", angleA);
            parent.setAnklePitchJoint("L", angleB);
          }
          if(name.equals("RFoot") || name.equals("RAnkle"))
          {
            parent.setAnkleRollJoint("R", angleA);
            parent.setAnklePitchJoint("R", angleB);
          }
        }
      }
  }

  private void resetInfoText()
  {
    parent.setInfoText("nothing selected");
  }

  public void updateScene(int xpos, int ypos)
  {
    PickResult[] new_picks = null;
    TransformGroup tg = null;
    String pickedName = null;
    String picked = "nothing selected";

    pickCanvas.setShapeLocation(xpos, ypos);

    new_picks = pickCanvas.pickAll();

    if (new_picks != null)
    {
      int i = 0;
      if(new_picks.length > 1)
      {
        i = 1;
      }
      tg = (TransformGroup) new_picks[i].getNode(PickResult.TRANSFORM_GROUP);

      pickedName = tg.getName();
      if(pickedName != null)
      {
        if(pickedName.equals("Head"))
        {
          picked = "------ head ------";
        }
        if(pickedName.equals("Chest"))
        {
          picked = "------- hip -------";
        }
        if(pickedName.equals("LThigh"))
        {
          picked = "- left upper leg -";
        }
        if(pickedName.equals("RThigh"))
        {
          picked = "- right upper leg -";
        }
        if(pickedName.equals("LTibia"))
        {
          picked = "- left lower leg -";
        }
        if(pickedName.equals("RTibia"))
        {
          picked = "- right lower leg -";
        }
        if(pickedName.equals("LFoot") || pickedName.equals("LAnkle"))
        {
          picked = "---- left foot ----";
        }
        if(pickedName.equals("RFoot") || pickedName.equals("RAnkle"))
        {
          picked = "---- right foot ----";
        }

        if(pickedName.equals("LUpperArm"))
        {
          picked = "- left upper arm -";
        }
        if(pickedName.equals("RUpperArm"))
        {
          picked = "- right upper arm -";
        }
        if(pickedName.equals("LLowerArm"))
        {
          picked = "- left lower arm -";
        }
        if(pickedName.equals("RLowerArm"))
        {
          picked = "- right lower arm -";
        }
        parent.setIgnoreInput(true);
      }
    }
    else
    {
      parent.setIgnoreInput(false);
    }
    parent.setInfoText(picked);
    picks = new_picks;
  }

  public void transformChanged(int arg0, Transform3D arg1)
  {}


}



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package naoth.me.core;

import java.io.*;
import javax.swing.JOptionPane;

//import de.hu_berlin.informatik.ki.motioneditor.core.Scanner;

/**
 *
 * @author claas
 */
public class MotionNetFromTxtParser
{

  private Scanner scanner;
//  private String currentToken;
  private int numberOfJoints;
  //private JointPrototypeConfiguration jointPrototypeConfiguration;
  private JointPrototypeConfiguration JointDefaultConfiguration;
  private String[] Joints = null;
  private int[] initialState = null;
  private boolean occuredError = false;
  private KeyFrame LastKeyFrame = null;
  private int KeyFrameNodeX = 50;
  private int KeyFrameNodeY = 50;
  private int PaintDirection = 1;

  private enum JointID
  {
      HeadYaw,
      HeadPitch,
      LShoulderPitch,
      LShoulderRoll,  
      LElbowRoll,
      LElbowYaw,
      LHipYawPitch,
      LHipRoll,
      LHipPitch,
      LKneePitch,
      LAnklePitch,
      LAnkleRoll,
      RHipYawPitch,
      RHipRoll,
      RHipPitch,
      RKneePitch,
      RAnklePitch,
      RAnkleRoll,
      RShoulderPitch,
      RShoulderRoll,  
      RElbowRoll,
      RElbowYaw,
//Reihenfolge von Nika geändert, hier unten die alte auskommentiert.
//    HeadPitch,
//    HeadYaw,
//    RShoulderPitch,
//    LShoulderPitch,
//    RShoulderRoll,
//    LShoulderRoll,  
//    RElbowRoll,
//    LElbowRoll,
//    RElbowYaw,
//    LElbowYaw,
//    RHipYawPitch,
//    LHipYawPitch,
//    RHipPitch,
//    LHipPitch,
//    RHipRoll,
//    LHipRoll,
//    RKneePitch,
//    LKneePitch,
//    RAnklePitch,
//    LAnklePitch,
//    RAnkleRoll,
//    LAnkleRoll,
    numOfJoint
  };

  private enum KeyWordID
  {
    endOfLine,
    separator,
//    keyFrame,
//    transition,
    comment,
    numberOfKeyWordID,
//    nao
  };

  private MotionNet motionNet;

  MotionNetFromTxtParser(File file)
  {
      this.numberOfJoints = 22; 
      this.JointDefaultConfiguration = new JointDefaultConfiguration();
      setDefaults();
      
    try
    {
      this.scanner = new Scanner(new FileReader(file));
      this.motionNet = new MotionNet(this.JointDefaultConfiguration);
      setInitialKeyFrame();
//      this.currentToken = null;
      scanner.getNextChar();
    eat();
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,
        "Error : Could not open " + file.getName(),
        "Error", JOptionPane.ERROR_MESSAGE);
      this.occuredError = true;
      return;
    }
  }

  private void setDefaults(){
    this.Joints = new String[this.numberOfJoints];
    this.initialState = new int[this.numberOfJoints];
    
      this.Joints[0] = "HeadYaw";
      this.Joints[1] = "HeadPitch";
      this.Joints[2] = "LShoulderPitch";
      this.Joints[3] = "LShoulderRoll";  
      this.Joints[4] = "LElbowRoll";
      this.Joints[5] = "LElbowYaw";
      this.Joints[6] = "LHipYawPitch";
      this.Joints[7] = "LHipRoll";
      this.Joints[8] = "LHipPitch";
      this.Joints[9] = "LKneePitch";
      this.Joints[10] = "LAnklePitch";
      this.Joints[11] = "LAnkleRoll";
      this.Joints[12] = "RHipYawPitch";
      this.Joints[13] = "RHipRoll";
      this.Joints[14] = "RHipPitch";
      this.Joints[15] = "RKneePitch";
      this.Joints[16] = "RAnklePitch";
      this.Joints[17] = "RAnkleRoll";
      this.Joints[18] = "RShoulderPitch";
      this.Joints[19] = "RShoulderRoll";  
      this.Joints[20] = "RElbowRoll";
      this.Joints[21] = "RElbowYaw";
    
// Von Nika auskommentiert, weil nur Joints erneuert - initialState wird im Prog eh nicht benutzt.
//    this.Joints[0] = "HeadPitch";
//    this.initialState[0] = 0;
//    this.Joints[1] = "HeadYaw";
//    this.initialState[1] = 0;
//    this.Joints[2] = "RShoulderPitch";
//    this.initialState[2] = 120;
//    this.Joints[3] = "LShoulderPitch";
//    this.initialState[3] = 120;
//    this.Joints[4] = "RShoulderRoll";
//    this.initialState[4] = 0;
//    this.Joints[5] = "LShoulderRoll";
//    this.initialState[5] = 0;
//    this.Joints[6] = "RElbowRoll";
//    this.initialState[6] = 90;
//    this.Joints[7] = "LElbowRoll";
//    this.initialState[7] = -90;
//    this.Joints[8] = "RElbowYaw";
//    this.initialState[8] = 90;
//    this.Joints[9] = "LElbowYaw";
//    this.initialState[9] = -90;
//    this.Joints[10] = "RHipYawPitch";
//    this.initialState[10] = 0;
//    this.Joints[11] = "LHipYawPitch";
//    this.initialState[11] = 0;
//    this.Joints[12] = "RHipPitch";
//    this.initialState[12] = -31;
//    this.Joints[13] = "LHipPitch";
//    this.initialState[13] = -31;
//    this.Joints[14] = "RHipRoll";
//    this.initialState[14] = 0;
//    this.Joints[15] = "LHipRoll";
//    this.initialState[15] = 0;
//    this.Joints[16] = "RKneePitch";
//    this.initialState[16] = 63;
//    this.Joints[17] = "LKneePitch";
//    this.initialState[17] = 63;
//    this.Joints[18] = "RAnklePitch";
//    this.initialState[1] = -31;
//    this.Joints[19] = "LAnklePitch";
//    this.initialState[1] = -31;
//    this.Joints[20] = "RAnkleRoll";
//    this.initialState[1] = 0;
//    this.Joints[21] = "LAnkleRoll";
//    this.initialState[1] = 0;
  }
  
  private void setInitialKeyFrame(){
      KeyFrame frame = new KeyFrame(this.JointDefaultConfiguration, 0, 0, 0);
//TODO: parse transition time
      for (int i = 0; i < 22; i++)
          frame.setJointValue(Joints[i], initialState[i]);
      
      this.motionNet.addKeyFrame(frame);
      this.LastKeyFrame = frame;

  }
          
          

  private void eat() throws IOException
  {
    scanner.getNextToken();
  }
  //end eat

  private boolean isToken(KeyWordID tokenId)
  {
    return scanner.isToken(getKeyWord(tokenId));
  }//end isToken

  private void isTokenAndEat(KeyWordID tokenId) throws IOException
  {
    if(isToken(tokenId))
      eat();
    else
      ;//throw "ERROR: Syntaxfehler in Zeile %d.\n";
  }
  //end isTokenAndEat

  public MotionNet parse() throws IOException
  {
    //isTokenAndEat(KeyWordID.nao);
    parseMotionNet();
    //System.out.println("parsed: " + this.motionNet.toString());
    return this.motionNet;
  }//end parse



  public void parseMotionNet() throws IOException
  {//TODO: EOF??
    while(!this.occuredError)
    {
        parseKeyFrame();
    }//end while
  }//end parseMotionNet



  public void parseKeyFrame() throws IOException
  {
      int transitionTime = 0;
      try{
        transitionTime = parseInt();
      }catch(NumberFormatException ex){
        this.occuredError = true;
        return;  
      }
      float[] Keys = new float[numberOfJoints];//numberOfJoints
      // TODO: new way of painting all these
      //isTokenAndEat(KeyWordID.keyFrame); // keyword "keyframe"
      //isTokenAndEat(KeyWordID.separator); // ";"
      int id = scanner.getLineNumber(); // parseInt(); // id

      // skip the coordinates
      //isTokenAndEat(KeyWordID.separator); // ";"
      int x = scanner.getLineNumber()%3*100; // parseInt(); // x
      //isTokenAndEat(KeyWordID.separator); // ";"
      int y = (scanner.getLineNumber()/3 + 1)*100; // parseInt(); // y

      if(this.LastKeyFrame != null)
      {
        this.KeyFrameNodeX += 100 * this.PaintDirection;
        if(this.KeyFrameNodeX >= 500 || this.KeyFrameNodeX <= 0)
        {
          this.PaintDirection *= -1;
          this.KeyFrameNodeX += 100 * this.PaintDirection;
          //this.KeyFrameNodeX = 0;
          this.KeyFrameNodeY += 100;
        }
        if(x == 0 && y == 0)
        {
          x = this.KeyFrameNodeX;
          y = this.KeyFrameNodeY;
        }
      }

      // parse joint values
      int i = 0;
      KeyFrame Frame = new KeyFrame(this.JointDefaultConfiguration, id, x, y);
//TODO: parse transition time
      while( i<22  ) //!isToken(KeyWordID.endOfLine)
      {
        //isTokenAndEat(KeyWordID.separator); // ";"
        if(i < this.Joints.length)//numberOfJoints)
        {
         
          Keys[i] = Math.round(parseFloat() * 10) / 10;
          Frame.setJointValue(this.Joints[i], Keys[i]);
        }
        else
        {
          JOptionPane.showMessageDialog(null,
            "Error : To much keys in frame " + id + " detected",
            "Error", JOptionPane.ERROR_MESSAGE);
          this.occuredError = true;
          return;
        }
         i++;
      }
      //end while
      this.motionNet.addKeyFrame(Frame);

      Transition transition = new Transition("*", transitionTime);
      this.motionNet.addTransition(transition, this.LastKeyFrame, Frame);
      
      this.LastKeyFrame = Frame;
  }
  //end parseKeyFrame

  // parse an integer (something like "- 20" is not allowed, correct would be "-20")
  public int parseInt() throws IOException
  {
    int result = Integer.parseInt(scanner.buffer);
    eat();

    return result;
  }
  //end parseInt


  // pars a float
  public float parseFloat() throws IOException
  {
    float result = (float) Float.parseFloat(scanner.buffer);
    eat();

    return result;
  }
  //end parseInt


  private String getKeyWord(KeyWordID keyWordID)
  {
    switch(keyWordID)
    {
      case endOfLine: System.out.println("eol");return "/r/n";//return System.getProperty("line.separator");
      case separator: System.out.println("space");return " ";
      default: System.out.println("unknown");return "unknown";
    }//end switch
  }
  //end getKeyWord

  private String getJointName(JointID joint)
  {
    switch(joint)
    {
      case HeadPitch: return "HeadPitch";
      case HeadYaw: return "HeadYaw";
      case RShoulderRoll: return "RShoulderRoll";
      case LShoulderRoll: return "LShoulderRoll";
      case RShoulderPitch: return "RShoulderPitch";
      case LShoulderPitch: return "LShoulderPitch";
      case RElbowRoll: return "RElbowRoll";
      case LElbowRoll: return "LElbowRoll";
      case RElbowYaw: return "RElbowYaw";
      case LElbowYaw: return "LElbowYaw";
      case LHipYawPitch: return "LHipYawPitch";
      case RHipYawPitch: return "RHipYawPitch";
      case RHipPitch: return "RHipPitch";
      case LHipPitch: return "LHipPitch";
      case RHipRoll: return "RHipRoll";
      case LHipRoll: return "LHipRoll";
      case RKneePitch: return "RKneePitch";
      case LKneePitch: return "LKneePitch";
      case RAnklePitch: return "RAnklePitch";
      case LAnklePitch: return "LAnklePitch";
      case RAnkleRoll: return "RAnkleRoll";
      case LAnkleRoll: return "LAnkleRoll";
      default: return "Unknown Joint";
    }//end switch
  }
  //end getJointName


  private JointID jointIDFromName(String name)
  {
    try
    {
      return JointID.valueOf(name);
    }
    catch(IllegalArgumentException e)
    {
      return JointID.numOfJoint;
    }
  }
  //end getJointName


}

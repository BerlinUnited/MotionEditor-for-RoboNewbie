/*
 * 
 */

package naoth.me.core;

import java.util.ArrayList;

/**
 *
 * @author Heinrich Mellmann
 */
public class JointDefaultConfiguration extends JointPrototypeConfiguration
{

    public JointDefaultConfiguration()
    {
        ArrayList<JointPrototype> JointPrototypeList = new ArrayList<JointPrototype>();
        JointPrototype headYaw = new JointPrototype("HeadYaw", -120, 120, 0);
        JointPrototype headPitch = new JointPrototype("HeadPitch", -45, 45, 0);
        
        JointPrototype rShoulderPitch = new JointPrototype("RShoulderPitch", -120, 120, -119);
        JointPrototype lShoulderPitch = new JointPrototype("LShoulderPitch", -120, 120, -119);
        JointPrototype rShoulderRoll = new JointPrototype("RShoulderRoll", -95, 1, 0);
        JointPrototype lShoulderRoll = new JointPrototype("LShoulderRoll", -1, 95, 0);
        
        JointPrototype rElbowRoll = new JointPrototype("RElbowRoll", -120, 120, 119);
        JointPrototype lElbowRoll = new JointPrototype("LElbowRoll", -120, 120, -119);
        JointPrototype rElbowYaw = new JointPrototype("RElbowYaw", -1, 90, 89);
        JointPrototype lElbowYaw = new JointPrototype("LElbowYaw", -90, 1, -89);
        
        JointPrototype rHipYawPitch = new JointPrototype("RHipYawPitch", -90, 1, 0);
        JointPrototype rHipPitch = new JointPrototype("RHipPitch", -25, 100, 31.5);
        JointPrototype rHipRoll = new JointPrototype("RHipRoll", -45, 25, 0);
        JointPrototype rKneePitch = new JointPrototype("RKneePitch", -130, 1, -63);
        JointPrototype rAnklePitch = new JointPrototype("RAnklePitch", -45, 75, 31.5);
        JointPrototype rAnkleRoll = new JointPrototype("RAnkleRoll", -25, 45, 0);
        
        JointPrototype lHipYawPitch = new JointPrototype("LHipYawPitch", -90, 1, 0);
        JointPrototype lHipPitch = new JointPrototype("LHipPitch", -25, 100, 31.5);
        JointPrototype lHipRoll = new JointPrototype("LHipRoll", -25, 45, 0);
        JointPrototype lKneePitch = new JointPrototype("LKneePitch", -130, 1, -63);
        JointPrototype lAnklePitch = new JointPrototype("LAnklePitch", -45, 75, 31.5);
        JointPrototype lAnkleRoll = new JointPrototype("LAnkleRoll", -45, 25, 0);

        JointPrototypeList.add(headYaw);
        JointPrototypeList.add(headPitch);
        
        JointPrototypeList.add(rShoulderPitch);
        JointPrototypeList.add(lShoulderPitch);
        JointPrototypeList.add(rShoulderRoll);
        JointPrototypeList.add(lShoulderRoll);
        
        JointPrototypeList.add(rElbowYaw);
        JointPrototypeList.add(lElbowYaw);
        JointPrototypeList.add(rElbowRoll);
        JointPrototypeList.add(lElbowRoll);
        
        JointPrototypeList.add(rHipYawPitch);
        JointPrototypeList.add(rHipPitch);
        JointPrototypeList.add(rHipRoll);
        JointPrototypeList.add(rKneePitch);
        JointPrototypeList.add(rAnklePitch);
        JointPrototypeList.add(rAnkleRoll);
        
        JointPrototypeList.add(lHipYawPitch);
        JointPrototypeList.add(lHipPitch);
        JointPrototypeList.add(lHipRoll);
        JointPrototypeList.add(lKneePitch);
        JointPrototypeList.add(lAnklePitch);
        JointPrototypeList.add(lAnkleRoll);
        this.jointValuePrototypeList = JointPrototypeList;
    }//end loadFromFile


    
}//end ConfigurationLoader

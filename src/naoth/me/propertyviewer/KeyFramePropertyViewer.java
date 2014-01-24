/*
 * KeyFramePropertyViewer.java
 *
 * Created on 12. März 2008, 18:49
 */

package naoth.me.propertyviewer;

//import de.hu_berlin.informatik.ki.motioneditor.core.Joint;
import naoth.me.core.KeyFrame;
import naoth.me.core.Transition;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.*;

/**
 *
 * @author  Heinrich Mellmann
 */
public class KeyFramePropertyViewer extends javax.swing.JPanel implements PropertyChangeListener
{
    private KeyFrameEditor keyFrameEditor = null;
    KeyFrame currentKeyFrame = null;
    
    public PropertyChangeListener jointChangeListener;

    /** Creates new form KeyFramePropertyViewer */
    public KeyFramePropertyViewer()
    {
        initComponents();
        
        this.jScrollPane.getVerticalScrollBar().setBlockIncrement(20);
        this.jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        //keyFrameEditor = new KeyFrameEditor(new KeyFrame());

        this.jointChangeListener = new PropertyChangeListener()
          {
            public void propertyChange(PropertyChangeEvent evt)
            {
              if(evt.getPropertyName().compareTo("jointStateChanged") == 0)
              {
                firePropertyChange("jointStateChanged", null, null);
              }
            }
          };


    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        jPanelContent = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(300, 600));

        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelContent.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane.setViewportView(jPanelContent);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public void propertyChange(PropertyChangeEvent event)
    {
      currentKeyFrame = null;
      this.jPanelContent.removeAll();

      if(event.getPropertyName().compareTo("selectedKeyFrame") == 0 && event.getNewValue() instanceof KeyFrame)
      {
          currentKeyFrame = (KeyFrame)event.getNewValue();

          if(keyFrameEditor == null)
          {
            this.keyFrameEditor = new KeyFrameEditor(currentKeyFrame);
            this.keyFrameEditor.addPropertyChangeListener(this.jointChangeListener);
            this.keyFrameEditor.addPropertyChangeListener
            (
              new PropertyChangeListener()
              {
                public void propertyChange(PropertyChangeEvent evt)
                {
                  if(evt.getPropertyName().compareTo("jointValueChanged") == 0)
                  {
                    firePropertyChange("jointValueChanged", null, evt.getNewValue());
                  }
                  if(evt.getPropertyName().compareTo("jointStateChanged") == 0)
                  {
                    firePropertyChange("jointStateChanged", null, null);
                  }
                }
              }
            );
            this.jPanelContent.add(this.keyFrameEditor);
            this.keyFrameEditor.setVisible(true);
            this.keyFrameEditor.setPreferredSize(this.jPanelContent.getPreferredSize());
            this.jPanelContent.setLayout(new FlowLayout());
          }

          this.jPanelContent.add(this.keyFrameEditor);
          this.keyFrameEditor.setKeyFrameControls(currentKeyFrame);
          this.keyFrameEditor.repaint();

          this.revalidate();
          //System.out.println(currentKeyFrame);
          this.revalidate();
        }

        if(event.getPropertyName().compareTo("selectedTransition") == 0 && event.getNewValue() instanceof Transition)
        {
            TransitionEditor t = new TransitionEditor((Transition)event.getNewValue());
            this.jPanelContent.setLayout(new FlowLayout());
            this.jPanelContent.add(t);
            t.setVisible(true);
            t.repaint();
            this.revalidate();
        }

        this.repaint();
    }//end propertyChange

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JScrollPane jScrollPane;
    // End of variables declaration//GEN-END:variables

    
}

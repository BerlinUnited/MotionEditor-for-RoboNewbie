/*
 * JointControl.java
 *
 * Created on 15. März 2008, 23:11
 */

package naoth.me.propertyviewer;

import naoth.me.core.Joint;
import naoth.me.core.Transition;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author  sven
 */
public class TransitionEditor extends javax.swing.JPanel {
    
    private Transition transition;
    
    /** Creates new form JointControl */
    public TransitionEditor() {
        initComponents();
    }
    
    public TransitionEditor(Transition transition){
        this(   transition.getDuration(), 
                transition.getCondition());
        
        this.transition = transition;
    }
    
    public TransitionEditor(double duration, String condition){
        
        initComponents();

        SpinnerNumberModel model = new SpinnerNumberModel(duration, 0, 100000, 1);
        this.jSpinnerDuration.setModel(model);
        this.jSpinnerDuration.setValue(duration);
        
        this.jTextCondition.setText(condition);
        
        this.jSpinnerDuration.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                double value = (Double)jSpinnerDuration.getValue();
                transition.setDuration(value);
            }
        });
        
        //this.jSpinnerJointValue.setMinimum(minValue);
        //this.jSpinnerJointValue.setMaximum(maxValue);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelDuration = new javax.swing.JLabel();
        jSpinnerDuration = new javax.swing.JSpinner();
        jLabelCondition = new javax.swing.JLabel();
        jTextCondition = new javax.swing.JTextField();

        setToolTipText("Name of a Robots Joint/Motor");

        jLabelDuration.setText("Duration (ms)");
        jLabelDuration.setMaximumSize(new java.awt.Dimension(100, 14));
        jLabelDuration.setPreferredSize(new java.awt.Dimension(50, 14));

        jSpinnerDuration.setPreferredSize(new java.awt.Dimension(50, 18));

        jLabelCondition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCondition.setText("Condition");
        jLabelCondition.setPreferredSize(new java.awt.Dimension(36, 14));

        jTextCondition.setText("*");
        jTextCondition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextConditionActionPerformed(evt);
            }
        });
        jTextCondition.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextConditionKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCondition, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextCondition, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinnerDuration, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabelDuration, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCondition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextCondition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextConditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextConditionActionPerformed
        String condition = this.jTextCondition.getText();
        if(condition.length() == 0) condition = Transition.DEFAULT_CONDITION;
        transition.setCondition(condition);
        System.out.println(transition.getCondition());
    }//GEN-LAST:event_jTextConditionActionPerformed

    private void jTextConditionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextConditionKeyReleased
        String condition = this.jTextCondition.getText();
        if(condition.length() == 0) condition = Transition.DEFAULT_CONDITION;
        transition.setCondition(condition);
        System.out.println(transition.getCondition());
    }//GEN-LAST:event_jTextConditionKeyReleased
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCondition;
    private javax.swing.JLabel jLabelDuration;
    private javax.swing.JSpinner jSpinnerDuration;
    private javax.swing.JTextField jTextCondition;
    // End of variables declaration//GEN-END:variables
    
}
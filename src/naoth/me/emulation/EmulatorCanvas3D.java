/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package naoth.me.emulation;

import java.awt.Font;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Canvas3D;

/**
 *
 * @author claas
 */
public class EmulatorCanvas3D extends Canvas3D
{
  private static final long serialVersionUID = 7144426579917281131L;
  private String message = "nothing selected";

  EmulatorCanvas3D(GraphicsConfiguration config)
  {
    super(config);
  }
  
  public void setMessageString(String msg)
  {
    message = msg;
    postRender();
  }

  @Override
  public void postRender()
  {
    this.getGraphics2D().setColor(java.awt.Color.yellow);
    this.getGraphics2D().setFont(new Font("SansSerif", Font.BOLD, 24));
    this.getGraphics2D().drawString(message,10,28);
    try
    {
      this.getGraphics2D().flush(false);
    }
    catch(java.lang.Exception ex)
    {
    }
  }
}
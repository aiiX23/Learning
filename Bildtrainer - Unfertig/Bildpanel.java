import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class Bildpanel extends JPanel
{
	private Graphics2D g2d;
	private View view;
	private BufferedImage image;
	public Bildpanel(View view)
	{
		this.view = view;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g2d = (Graphics2D)g;
		if(image != null)
		{
			double width = (getWidth() * 1.0) / image.getWidth();
			double height = (getHeight() * 1.0) / image.getHeight();
			if(width >= height)
			{
				width = height * image.getHeight();
				height *= image.getHeight();
			}
			else 
			{
				height = width * image.getHeight();
				width *= image.getWidth();
			}
			g.drawImage(image, (int)((getWidth() - width) / 2.0), (int)((getHeight() - height) / 2.0), (int)width, (int)height, this);
		}
		this.setVisible(true);
	}
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
		paintComponent(view.getPanel_Bild().getGraphics());
	}
}
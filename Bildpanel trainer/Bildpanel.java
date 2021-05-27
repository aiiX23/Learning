package Bildpanelübung2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Bildpanel extends JPanel
{
	private BufferedImage bi;
	
	public void setImage(BufferedImage bi)
	{
		this.bi = bi;
		this.repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(bi == null)
			return;
		double skalierung;
		int x,y,breite,hoehe;
		
		if((double)this.getWidth() / this.getHeight() > (double)bi.getWidth() / bi.getHeight())
		{
			skalierung = (double)this.getHeight() / bi.getHeight();
			hoehe = this.getHeight();
			y = 0;
			breite = (int)(bi.getWidth() * skalierung);
			x = (this.getWidth() - breite) / 2;
		}
		else
		{
			skalierung = (double)this.getWidth() / bi.getWidth();
			breite = this.getWidth();
			x = 0;
			hoehe = (int)(bi.getHeight() * skalierung);
			y = (this.getHeight() - hoehe) / 2;
		}
		g.drawImage(bi, x, y, breite, hoehe, this);
	}
}

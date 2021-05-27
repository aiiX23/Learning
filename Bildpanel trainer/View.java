package Bildpanelübung2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class View
{
	
	private JFrame frame;
	private Bildpanel panel_bild;
	
	public View()
	{
		initialize();
		frame.setVisible(true);
	}


	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnFileChooser = new JButton("Bildauswahl");
		panel.add(btnFileChooser, BorderLayout.NORTH);
		btnFileChooser.addActionListener(e -> {
			JFileChooser jfc = new JFileChooser("D:\\Music");
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setMultiSelectionEnabled(false);
			if(jfc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)	return;
			File file = jfc.getSelectedFile();
			try
			{
				setImage(ImageIO.read(file));
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		});
		
		panel_bild = new Bildpanel();
		panel.add(panel_bild, BorderLayout.CENTER);
	}
	public void setImage(BufferedImage bi)
	{
		panel_bild.setImage(bi);
	}
	
	public static void main(String[] args)
	{
		new View();
	}
}
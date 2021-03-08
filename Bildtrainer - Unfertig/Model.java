import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Model
{
	private Controller controller;
	private JFileChooser jfc;
	private int zaehler = 0;
	private File f;
	private File[] fa;

	public Model(Controller controller)
	{
		this.controller = controller;
	}
	public void verzeichnisWähler( )
	{
		jfc = new JFileChooser("U:\\Software Aufgabendaten\\Bilder");
		jfc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		if(jfc.showOpenDialog( null ) != JFileChooser.APPROVE_OPTION) return;
		f = jfc.getSelectedFile();
		fa = f.listFiles();
		
	}
	public BufferedImage bi()
	{
		try
		{
			return ImageIO.read( fa[zaehler] );
		} catch ( IOException e )
		{
			e.printStackTrace();
		}
		return null;
	}
	public void weiter()
	{
		if(zaehler == fa.length-1)
			zaehler = 0;
		else
			zaehler++;
		controller.fileübergabe();
	}
	public void textEncoder(String str)
	{
		try
		{
			XMLEncoder encoder = new XMLEncoder( new FileOutputStream("U:\\Software Aufgabendaten\\Bilder\\" + fa[zaehler].getName() + ".xml"));
			encoder.writeObject( str );
			encoder.close( );
		} catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}
	}
	public String textDecoder()
	{
		try
		{
			File pfad = new File("U:\\Software Aufgabendaten\\Bilder\\" + fa[zaehler].getName() + ".xml");
			if(pfad.exists( ) == false)
				return "Nicht vorhanden";
			XMLDecoder decoder = new XMLDecoder( new FileInputStream(pfad));

			String str = (String) decoder.readObject();
			decoder.close( );
			
			return str;			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return "";
	}
}
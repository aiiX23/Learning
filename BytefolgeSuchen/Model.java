package muntenbeck.GUI.BytefolgeSuchen;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Model
{
	private Controller controller;
	private JFileChooser auswahl;
	private FileInputStream fis;
	private File verzeichnis;
	
	public Model( Controller controller )
	{
		this.controller = controller;
	}
	
	public void pfadWahl(String msg)
	{
		auswahl = new JFileChooser( "U:\\Software Aufgabendaten" );
		auswahl.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		if(auswahl.showDialog( null, msg )!=JFileChooser.APPROVE_OPTION) return;
		try
		{
			suche(auswahl.getSelectedFile( ));
		} catch (EOFException e)
		{
			e.printStackTrace();
		}
		
	} 
	
	public String byteAbfrage( File datei )
	{
		String dateiinhalt = "";
		String jpeg = "255 216 255 225 ";
		String tif = "73 73 42 0 ";
		String gif = "71 73 70 56 ";
		String bmp = "66 77 198 128 ";
		
		try
		{
			fis = new FileInputStream( datei );
		} 
		catch (FileNotFoundException e)
		{	e.printStackTrace();	}
		
		for (int i = 0; i < 4; i++)
		{
			int bytes =	0;
			try
			{
				bytes = fis.read( );
			} 
			catch (IOException e)
			{	e.printStackTrace();	}
			
			if (bytes == -1) break;
			dateiinhalt = dateiinhalt + String.valueOf( bytes ) + " ";
		}
		return dateiinhalt;
	}
	
	public void suche ( File ordner ) throws EOFException
	{
		File[] liste = ordner.listFiles( );
		if(liste != null )
		{
			for(int i = 0; i < liste.length; i++)
			{
				if(liste[i].isFile( ))
				{
					String typ = byteAbfrage(liste[i]);
					//if( typ ==  )
					{
					}
					controller.append( liste[i].getAbsolutePath( ));
				}
				else if(liste[i].isDirectory( ))
				{
					suche(liste[i]);
				}
			}
		}
	}
}

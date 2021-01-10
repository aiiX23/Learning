package muntenbeck.GUI.FileChooserRekursiv;

import java.io.File;

import javax.swing.JFileChooser;

public class Verzeichnissuche
{
	private JFileChooser auswahl;
	private int anzahlOrdner;
	private int anzahlDateien;
	private long groeßeDateien;
	
	public Verzeichnissuche()
	{
		Verzeichnissuche();
	}
	
	private void Verzeichnissuche()
	{
		auswahl = new JFileChooser("U:\\");
		auswahl.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		int returnwert = auswahl.showDialog( null, null );
		
		if(returnwert != JFileChooser.APPROVE_OPTION) return;
		File dateien = auswahl.getSelectedFile( );
		suche (dateien);
		
		System.out.println( "Anzahl Dateien:\t\t" + anzahlDateien );
		System.out.println( "Anzahl Ordner:\t\t"+ anzahlOrdner );
		System.out.println( "Größe der Dateien:\t" + groeßeDateien +" bytes" );
	}
	
	private void suche (File verzeichnis)
	{
		File[] liste = verzeichnis.listFiles( );
		if(liste != null)
		{
			for(int i = 0; i < liste.length; i++)
			{
				if(liste[i].isFile( ))
				{
					anzahlDateien++;
					groeßeDateien = groeßeDateien + liste[i].length( );
				}
				else if(liste[i].isDirectory( ))
				{
					anzahlOrdner++;
					suche(liste[i]);
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		new Verzeichnissuche();
	}
	
}

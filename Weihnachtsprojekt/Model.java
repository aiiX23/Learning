import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Model
{
	private Controller controller;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private File verzeichnis;
	private String dateiname;
	private int anzahlBytes;
	private File neueDatei;

	public Model(Controller controller)
	{
		this.controller = controller;
	}

	private void verbinde()
	{
		try
		{
			socket = new Socket(System.getenv("COMPUTERNAME"), 4888);
			bis = new BufferedInputStream(socket.getInputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			if(reader.readLine().equals("connect"))
			{
				System.out.println("Verbunden");
				senden("name");
				dateiname = reader.readLine();
				senden("bytes");
				anzahlBytes = Integer.valueOf(reader.readLine());
				senden("start");
				empfange();
				fertig();
			}
			reader.close();
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void wähleVerzeichnis()
	{
		JFileChooser auswahl = new JFileChooser();
		auswahl.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(auswahl.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) return;
		verzeichnis = auswahl.getSelectedFile();
	}
	
	public void start()
	{
		if(verzeichnis == null)
		{
			JOptionPane.showMessageDialog(null, "Kein Verzeichnis ausgewählt");
			return;
		}
		verbinde();
	}
	
	private void empfange()
	{
		try
		{
			neueDatei = new File(verzeichnis + "\\" + dateiname);
			bos = new BufferedOutputStream(new FileOutputStream(neueDatei));
			
			for(int i = 0; i < anzahlBytes; i++)
			{
				bos.write(bis.read());
			}
			bis.close();
			bos.close();
			System.out.println("Erfolgreich Übertragen");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	private void senden(String msg)
	{
		try
		{
			writer.write(msg);
			writer.newLine();
			writer.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void fertig()
	{
		if(JOptionPane.showConfirmDialog(null, "Möchten sie die Datei " + dateiname + " öffnen?", "Datei öffnen", JOptionPane.YES_NO_OPTION) == 0)
		{
			new Thread( () ->
			{
				try
				{
					Runtime.getRuntime().exec("explorer " + neueDatei.getAbsolutePath());
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}).start();
		}
		
	}
	
	
}
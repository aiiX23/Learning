package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class TCP
{
	private Socket socket;
	private BufferedReader br;
	private BufferedWriter bw;
	
	public TCP(String ip, int port)
	{
		
		try
		{
			socket = new Socket(ip, port);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e, "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void senden(String uebergabe)
	{
		try
		{
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(uebergabe);
			bw.newLine();
			bw.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String empfangen()
	{
		try
		{
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			return br.readLine();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
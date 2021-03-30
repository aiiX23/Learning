import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiClientTest
{
	public void  starteServer(){
		try {
			//Starte den Server
			ServerSocket server = new ServerSocket(1234);
			
			//Gibt den Host und die IP auf der console aus.
			InetAddress adresse = InetAddress.getLocalHost();
			System.out.println("MServer gestartet \n" + "Server auf IP: \t" + adresse.getHostAddress() +"\nServer Name: \t"+ adresse.getHostName());
			
			//Startet einen Thread für jede neue Verbindung:
			while (true){
				Socket client = server.accept();
				//den Thread Starten und den Clienten übergeben:
				new echo(client).start();
			}
			
		} catch (Exception e) {
			
		}
		
	}
	//den Thread in einer inneren Klasse laufen lassen:
	private class echo extends Thread
	{
		private Socket client;
		public echo(Socket client)
		{
			//einfaches Setter im Construktor um den empfangenen Socket zuzuordnen:
			this.client = client;
		}
		public void run()
		{
			//Jetzt kennen wir auch direkt die IP vom Client.
			String clientAdresse;
			clientAdresse = "Client IP: \t" + client.getInetAddress().getHostAddress() + "\nClient Name: \t" + client.getInetAddress().getHostName();
			System.out.println(clientAdresse);
			try {
				BufferedReader rein = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				PrintWriter senden = new PrintWriter(client.getOutputStream());
				/**
				 * Diese Endlosschleife ist dazu da jede anfrage der Clients anzunehmen und zurück zu senden.
				 * Eine Empfangene Nachricht wird in den Zwischenspeicher gelegt.
				 * Anschliessend wird dieser String mit dem Wort "Antwort" versehen und zurück gesendet.
				 */
				String zwischenspeicher;
				while((zwischenspeicher = rein.readLine()) != null){
					zwischenspeicher = "Antwort: " + zwischenspeicher;
					//Printwriter mit Inhalt füllen:
					senden.println(zwischenspeicher);
					//Printwriter senden:
					senden.flush();
					//Zur sicherheit nochmal auf der Console ausgeben:
					System.out.println(zwischenspeicher);
				}
			
		
			} catch (Exception e) {
			
			}
		}
	}
	public static void main(String[] args) 
	{
		MultiClientTest t = new MultiClientTest();
		t.starteServer();
	}
}
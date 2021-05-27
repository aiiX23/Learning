package TCP;

public class Model
{
	private Controller controller;
	private TCP tcp1, tcp2, tcp3;
	
	public Model(Controller controller)
	{
		this.controller = controller;
	}
	
	public void verbinden()
	{
		tcp1 = new TCP("localhost", 4887);
		tcp2 = new TCP("localhost", 4888);
		tcp3 = new TCP("localhost", 4889);
		
		new Thread(() -> {
			while(true)
			{
				controller.ausgabe("Sensor 1 " + tcp1.empfangen());
			}
		}).start();
		
		new Thread(() -> {
			while(true)
			{
				controller.ausgabe("Sensor 2 " + tcp2.empfangen());
			}
		}).start();
		
		new Thread(() -> {
			while(true)
			{
				controller.ausgabe("Sensor 3 " + tcp3.empfangen());
			}
		}).start();
	}
	public void senden(String send)
	{
		tcp1.senden(send);
	}
}
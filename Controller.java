public class Controller
{
	private View view;
	private Model model;
	
	public Controller()
	{
		view = new View(this);
		model = new Model(this);
	}
	
	public void buttonWähle()
	{
		new Thread(() -> model.wähleVerzeichnis()).start();
	}
	
	public void buttonStart()
	{
		new Thread(() -> model.start()).start();
	}
	

	public static void main(String[] args)
	{
		new Controller();
	}

}

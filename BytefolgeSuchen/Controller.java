package muntenbeck.GUI.BytefolgeSuchen;

public class Controller
{
	private View view;
	private Model model;
	
	public Controller( )
	{
		view = new View(this);
		model = new Model(this);
	}
	
	public void buttonÜbergabe()
	{
		model.pfadWahl( null );
	}
	
	public void append(String daten)
	{
		view.append( daten );
	}

	public static void main( String[] args )
	{
		new Controller();
	}

}

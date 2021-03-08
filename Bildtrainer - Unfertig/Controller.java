import java.awt.image.BufferedImage;

public class Controller
{
	private View view;
	private Model model;
	
	public Controller( )
	{
		view = new View(this);
		model = new Model(this);
	}

	public static void main( String[] args )
	{
		new Controller();
	}

	public void verzeichnis( )
	{
		model.verzeichnisWähler();
		fileübergabe();
	}
	
	public void fileübergabe()
	{
		view.getPanel_Bild().setImage(model.bi());
	}
	
	public void weiterBtn()
	{
		model.weiter();
	}
	
	public void textÜbergabe()
	{
		model.textEncoder( view.getTextArea().getText());
	}
	
	public void textAnzeige()
	{
		view.setTextArea( model.textDecoder( ));
	}
}

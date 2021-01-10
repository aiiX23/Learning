package muntenbeck.GUI.BytefolgeSuchen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View
{
	private Controller controller;
	private JFrame frame;
	private JButton btnSuche;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	public View( Controller controller )
	{
		this.controller = controller;
		initialize();
		createGUI();
		createBTN();
		createTXTArea();
		frame.setVisible( true );
	}

	private void initialize( )
	{
		frame = new JFrame( );
		btnSuche = new JButton("Ordnerpfad w\u00E4hlen");
		scrollPane = new JScrollPane();
		textArea = new JTextArea();
	}
	private void createGUI( )
	{
		frame.setTitle("Versteckte Bilder suche");
		frame.setBounds( 100, 100, 700, 500 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	private void createBTN( )
	{
		frame.getContentPane().add(btnSuche, BorderLayout.NORTH);
		btnSuche.addActionListener( e -> controller.buttonÜbergabe( ));
	}
	
	private void createTXTArea( )
	{
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}
	
	public void append(String daten)
	{
		textArea.append( daten + "\n" );
	}
}

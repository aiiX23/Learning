import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

public class View
{
	private Controller controller;
	private JFrame frame;
	private JPanel panel_Center, panel_Top, panel_Bottom;
	private JButton btnVerzWählen, btnTextAnz, btnWeiter;
	private JCheckBox chckbxWiederholen;
	private JTextArea textArea;
	private Bildpanel panel_Bild;
	private JButton btnSpeichern;

	public View(Controller controller)
	{
		this.controller = controller;
		initialize( );
		frame.setVisible( true );
	}
	
	private void initialize( )
	{
		frame = new JFrame( );
		frame.setBounds(new Rectangle(25, 25, 25, 25));
		frame.setMinimumSize(new Dimension(250, 250));
		frame.setBounds( 100, 100, 600, 400 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		panel_Center = new JPanel();
		frame.getContentPane().add(panel_Center, BorderLayout.CENTER);
		panel_Center.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_Bild = new Bildpanel(this);
		panel_Center.add(panel_Bild);
		
		textArea = new JTextArea();
		panel_Center.add(textArea);
		
		panel_Top = new JPanel();
		frame.getContentPane().add(panel_Top, BorderLayout.NORTH);
		
		btnVerzWählen = new JButton("Pfad w\u00E4hlen");
		btnVerzWählen.setForeground(Color.DARK_GRAY);
		btnVerzWählen.setFont(new Font("Arial", Font.BOLD, 12));
		btnVerzWählen.setBorder(UIManager.getBorder("Button.border"));
		btnVerzWählen.setBackground(Color.WHITE);
		panel_Top.add(btnVerzWählen);
		
		chckbxWiederholen = new JCheckBox("Wiederholen");
		chckbxWiederholen.setHorizontalTextPosition(SwingConstants.LEADING);
		chckbxWiederholen.setFont(new Font("Arial", Font.PLAIN, 12));
		chckbxWiederholen.setActionCommand("");
		panel_Top.add(chckbxWiederholen);
		
		panel_Bottom = new JPanel();
		frame.getContentPane().add(panel_Bottom, BorderLayout.SOUTH);
		
		btnWeiter = new JButton("Weiter");
		panel_Bottom.add(btnWeiter);
		btnWeiter.setForeground(Color.DARK_GRAY);
		btnWeiter.setFont(new Font("Arial", Font.BOLD, 12));
		btnWeiter.setBackground(Color.WHITE);
		
		btnTextAnz = new JButton("Text anzeigen");
		btnTextAnz.setBackground(Color.WHITE);
		btnTextAnz.setForeground(Color.DARK_GRAY);
		btnTextAnz.setFont(new Font("Arial", Font.BOLD, 12));
		panel_Bottom.add(btnTextAnz);
		
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBackground(Color.WHITE);
		btnSpeichern.setForeground(Color.DARK_GRAY);
		btnSpeichern.setFont(new Font("Arial", Font.BOLD, 12));
		panel_Bottom.add(btnSpeichern);
		
		btnVerzWählen.addActionListener( e -> controller.verzeichnis());
		btnWeiter.addActionListener( e -> controller.weiterBtn());
		btnSpeichern.addActionListener( e -> controller.textÜbergabe());
		btnTextAnz.addActionListener( e -> controller.textAnzeige( ));
	}

	public Bildpanel getPanel_Bild( )
	{
		return panel_Bild;
	}

	public void setPanel_Bild( Bildpanel panel_Bild )
	{
		this.panel_Bild = panel_Bild;
	}

	public JTextArea getTextArea( )
	{
		return textArea;
	}
	public void setTextArea(String str)
	{
		textArea.setText( str );
	}
}
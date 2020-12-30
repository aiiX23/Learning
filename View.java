import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class View
{
	private JFrame frame;
	private Controller controller;
	private JButton buttonWählen, buttonStart;

	public View(Controller controller)
	{
		this.controller = controller;
		init();
		frame.setVisible(true);
	}

	private void init()
	{
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 365, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		buttonWählen = new JButton("Pfad w\u00E4hlen");
		buttonWählen.setAlignmentY(0.0f);
		buttonWählen.setBorder(null);
		buttonWählen.setBorderPainted(false);
		buttonWählen.setBackground(Color.WHITE);
		buttonWählen.setFont(new Font("Arial", Font.PLAIN, 16));
		buttonWählen.setBounds(20, 11, 134, 38);
		frame.getContentPane().add(buttonWählen);
		buttonWählen.addActionListener(e -> controller.buttonWähle());
		
		buttonStart = new JButton("\u00DCbertragen");
		buttonStart.setAlignmentY(0.0f);
		buttonStart.setBorder(null);
		buttonStart.setBorderPainted(false);
		buttonStart.setFont(new Font("Arial", Font.PLAIN, 16));
		buttonStart.setBackground(Color.WHITE);
		buttonStart.setBounds(196, 11, 134, 38);
		frame.getContentPane().add(buttonStart);
		buttonStart.addActionListener(e -> controller.buttonStart());
	}
	
}



import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class iconPractice extends JFrame{
	JPanel panel;
	JLabel label;
	JLabel picLabel;
	JButton bombButton;
	public iconPractice() {
		super("icon");
		buildPanel();
		
		
		
		
		
		
		
		
		
		
		add(panel);
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public void buildPanel() {
		panel = new JPanel();
		panel.setLayout(new FlowLayout(1));
		label = new JLabel("Below is an Image");
		picLabel = new JLabel();
		bombButton = new JButton();
		ImageIcon bombImg = new ImageIcon(getClass().getResource("bombicon.png"));
		ImageIcon bombImgScaled = new ImageIcon(bombImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		bombButton.setIcon(bombImgScaled);
	
		//Icon icon = new ImageIcon(getClass().getResource("redflag.png"));
		
		
		ImageIcon flagImg = new ImageIcon(getClass().getResource("redflag.png"));
		ImageIcon flagImgScaled = new ImageIcon(flagImg.getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		picLabel.setIcon(flagImgScaled);
		panel.add(label);
		panel.add(picLabel);
		panel.add(bombButton);
		
	}
	public static void main(String[] args) {
		new iconPractice();
	}
}

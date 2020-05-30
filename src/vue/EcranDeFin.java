package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EcranDeFin extends JPanel {
	public EcranDeFin(String message) {
		this.setLayout(new BorderLayout());
		
		JLabel msg = new JLabel(message);

		msg.setFont(new Font("Serif", Font.PLAIN, 50));

		this.add(msg,BorderLayout.PAGE_START);
	}
}

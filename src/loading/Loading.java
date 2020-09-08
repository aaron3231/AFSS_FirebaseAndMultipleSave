package loading;

import java.awt.Color;
import java.awt.Font;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Loading extends TimerTask{
	private String[] anime = {"Loading", "Loading.", "Loading..", "Loading..."};
	private Color[] color = {new Color(255, 0, 0), new Color(255, 165, 0), new Color(255, 255, 0), new Color(0, 255, 0), new Color(0, 0, 255), new Color(75, 0, 128), new Color(139, 0, 255)};
	private JFrame frame;
	private JLabel label = new JLabel();
	private Font font = new Font("·L³n¥¿¶ÂÅé", Font.PLAIN, 60);
	private int i = 0;

	public Loading(String name) {
		frame = new JFrame(name);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.BLACK);
		label.setBounds(75, 30, 300, 200);
		label.setFont(font);
		label.setForeground(Color.BLUE);
		frame.add(label);
		frame.setVisible(true);
		frame.setBounds(400, 300, 400, 300);
	}

	@Override
	public void run() {
		label.setText(anime[i % 4]);
		label.setForeground(color[i % 7]);
		i++;
	}
}
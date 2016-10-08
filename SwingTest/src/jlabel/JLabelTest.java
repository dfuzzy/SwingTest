package jlabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class JLabelTest implements ComponentListener {
	private JLabel status;
	private JLabel label;
	private JFrame frame;
	
	public JLabelTest() {
		this.status = new JLabel();
		
		this.label = new JLabel();
		this.label.setText("ABCDEFG HIJKLMN OPQRSTU VWXYZ");
		this.label.setBorder(BorderFactory.createLineBorder(Color.RED));
		this.label.setBackground(new Color(255, 192, 192));
		
		this.frame = new JFrame();
		this.frame.getContentPane().add(label, BorderLayout.CENTER);
		this.frame.getContentPane().add(this.status, BorderLayout.NORTH);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(640, 480);
		this.frame.addComponentListener(this);
		this.frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new JLabelTest();
			}
		});
	}

	@Override
	public void componentResized(ComponentEvent e) {
		this.status.setText(
			String.format(
				"%d, %d|%d, %d",
				(int)this.label.getPreferredSize().getWidth(),
				(int)this.label.getPreferredSize().getHeight(),
				this.label.getWidth(),
				this.label.getHeight()
			)
		);
	}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}

}

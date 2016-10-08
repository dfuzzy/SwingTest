package jscrollpane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class JScrollPaneTest implements ActionListener {
	
	private JFrame frame;
	private JCheckBox cb0;
	private JCheckBox cb1;
	private JScrollPane sp;
	private JLabel l0;
	private JLabel l1;
	
	public void setColumnHeaderVisible(boolean b) {
		this.cb0.setSelected(b);
		this.sp.setColumnHeaderView(b ? this.l0 : null);
	}
	
	public void setRowHeaderVisible(boolean b) {
		this.cb1.setSelected(b);
		this.sp.setRowHeaderView(b ? this.l1 : null);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==this.cb0) {
			this.setColumnHeaderVisible(cb0.isSelected());
		} else if(ae.getSource()==this.cb1) {
			this.setRowHeaderVisible(cb1.isSelected());
		}
	}
	
	public JScrollPaneTest() {
		cb0 = new JCheckBox("Column Header");
		cb0.addActionListener(this);
		cb1 = new JCheckBox("Row Header");
		cb1.addActionListener(this);
		
		JPanel p0 = new JPanel();
		p0.add(cb0);
		p0.add(cb1);
		
		l0 = new JLabel("Column Header");
		l0.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		l0.setBackground(new Color(192, 192, 255));
		l1 = new JLabel("Row Header");
		l1.setBorder(BorderFactory.createLineBorder(Color.RED));
		l1.setBackground(new Color(255, 192, 192));
		
		JTextArea ta = new JTextArea();
		ta.setBackground(new Color(192, 255, 192));
		
		sp = new JScrollPane(ta);
//		sp.setColumnHeaderView(l0);
//		sp.setRowHeaderView(l1);
		
		this.frame = new JFrame();
		this.frame.getContentPane().add(sp, BorderLayout.CENTER);
		this.frame.getContentPane().add(p0, BorderLayout.NORTH);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(800, 600);
		this.frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JScrollPaneTest();
			}
		});
	}

}

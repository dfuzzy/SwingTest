package jinternalframe;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class JInternalFrameTest {
	private JFrame frame;
	private JDesktopPane desktop;
	private Vector<JInternalFrame> iframeList;
	
	private int gridCols;
	private int gridRows;
	
	public int getGridCols() {
		return gridCols;
	}

	public void setGridCols(int gridCols) {
		if(gridCols<=0) throw new IllegalArgumentException("must be greater than 0!");
		this.gridCols = gridCols;
	}

	public int getGridRows() {
		return gridRows;
	}

	public void setGridRows(int gridRows) {
		if(gridRows<=0) throw new IllegalArgumentException("must be greater than 0!");
		this.gridRows = gridRows;
	}
	
	public void resetGrid() {
		int gridWidth = (int)(1d * this.desktop.getWidth() / this.getGridCols());
		int gridHeight = (int)(1d * this.desktop.getHeight() / this.getGridRows());
		
		for(int i=0; i<this.iframeList.size(); i++) {
			this.iframeList.get(i).setBounds(gridWidth * (i%this.getGridCols()), gridHeight * (i/this.getGridCols()), gridWidth, gridHeight);
		}
	}
	
	private void initGUI() {
		this.frame = new JFrame();
		this.frame.setTitle(this.getClass().getName());
		this.frame.setSize(1024, 768);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.desktop = new JDesktopPane();
		this.frame.getContentPane().add(new JScrollPane(this.desktop), BorderLayout.CENTER);
		this.frame.setVisible(true);
		
		this.iframeList = new Vector<JInternalFrame>();
		
		for(int y=0; y<this.getGridRows(); y++) {
			for(int x=0; x<this.getGridCols(); x++) {
				JInternalFrame ifr = new JInternalFrame();
				ifr.setTitle(String.format("JInternalFrame %d", x+this.getGridCols()*y));
				ifr.setVisible(true);
				this.iframeList.addElement(ifr);
				this.desktop.add(ifr);
			}
		}
		
		this.resetGrid();
	}
	
	public JInternalFrameTest() {
		this.gridCols = 1;
		this.gridRows = 1;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JInternalFrameTest.this.initGUI();
			}
		});
	}

	public static void main(String[] args) {
		new JInternalFrameTest();
	}

}

package jtree;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/*
 * @see http://www.comp.nus.edu.sg/~cs3283/ftp/Java/swingConnect/tech_topics/treemodel/treemodel.html
 */
public class JTreeSample {
	private JFrame frame;
	private JTree tree;
	
	public JTreeSample() {
		this.tree = new JTree(new TreeModel() {
			@Override
			public Object getRoot() {
				return new File(".");
			}

			@Override
			public Object getChild(Object parent, int index) {
				return new File((File)parent, ((File)parent).list()[index]);
			}

			@Override
			public int getChildCount(Object parent) {
				return ((File)parent).list().length;
			}

			@Override
			public boolean isLeaf(Object node) {
				return ((File)node).isFile();
			}

			@Override
			public void valueForPathChanged(TreePath path, Object newValue) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int getIndexOfChild(Object parent, Object child) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void addTreeModelListener(TreeModelListener l) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeTreeModelListener(TreeModelListener l) {
				// TODO Auto-generated method stub
				
			}
			
		}) {
			private static final long serialVersionUID = -6376602284239040034L;

			public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
				return ((File)value).getName();
			}
		};
		
		this.frame = new JFrame();
		this.frame.getContentPane().add(this.tree, BorderLayout.CENTER);
		
		this.frame.setSize(640, 480);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JTreeSample();
			}
		});
	}

}

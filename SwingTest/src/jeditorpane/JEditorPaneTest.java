package jeditorpane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeModelListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.PlainView;
import javax.swing.text.Position.Bias;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.ViewFactory;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AbstractDocument.LeafElement;
import javax.swing.text.View;

public class JEditorPaneTest {
	private JFrame frame;
	private JEditorPane editorPane;
	private JTree tree;
	
	private static class MyAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private static class MyEditorKit extends DefaultEditorKit {
		public Document createDefaultDocument() {
			return new PlainDocument() {
				public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
					JEditorPaneTest.log.log(Level.FINE, "offs={0}, str={1}, a={2}", new Object[] { offs, str, a });
					super.insertString(offs, str, a);
				}
				
				public void remove(int offs, int len) throws BadLocationException {
					JEditorPaneTest.log.log(Level.FINE, "offs={0}, len={1}", new Object[] { offs, len });
					super.remove(offs, len);
				}
				protected Element createLeafElement(Element parent, AttributeSet a, int p0, int p1) {
					SimpleAttributeSet as = (a==null ? new SimpleAttributeSet() : new SimpleAttributeSet(a));
					as.addAttribute("color", new Color((int)(Math.random()*16777216)));
					
					this.writeLock();
					LeafElement elm = (LeafElement)super.createLeafElement(parent, as, p0, p1);
					this.writeUnlock();
//					LeafElement elm = new LeafElement(parent, as, p0, p1);
//					AttributeSet as = elm.getAttributes();
//					for(Enumeration<?> enm = elm.getAttributes().getAttributeNames(); enm.hasMoreElements(); ) {
//						Object key = enm.nextElement();
//						log.log(Level.INFO, "{0}={1}", new Object[] { key, as.getAttribute(key) });
//					}
					log.log(Level.INFO, elm.toString());
					return elm;
				}
			};
		}
		public ViewFactory getViewFactory() {
			return MyEditorKit.defaultFactory;
		}
		private static final ViewFactory defaultFactory = new ViewFactory() {
			@Override
			public View create(Element elem) {
				return new MyTextView(elem);
			}
		};
	}
	
	private static class MyParagraphView extends View {
		Font f;
		FontMetrics fm;
		
		MyParagraphView(Element elm) {
			super(elm);
			this.f = new Font(Font.MONOSPACED, Font.PLAIN, 9);
			this.fm = this.getContainer().getFontMetrics(this.f);
		}

		@Override
		public float getPreferredSpan(int axis) {
		switch (axis) {
			case View.X_AXIS:
				return this.getContainer().getWidth();
			case View.Y_AXIS:
				return this.getElement().getElementCount() * this.fm.getHeight();
			default:
				throw new IllegalArgumentException("Invalid axis: " + axis);
			}
		}

		@Override
		public void paint(Graphics g, Shape allocation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Shape modelToView(int pos, Shape a, Bias b) throws BadLocationException {
			Element elm = this.getElement();
			return null;
		}

		@Override
		public int viewToModel(float x, float y, Shape a, Bias[] biasReturn) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	private static class MyTextView extends PlainView {
		public void paint(Graphics g, Shape a) {
			Rectangle rect = a.getBounds();
			Object o = this.getElement().getAttributes().getAttribute("color");
			JEditorPaneTest.log.log(Level.INFO, this.getElement().toString(), "");
			if(o instanceof Color) {
				g.setColor((Color)o);
				JEditorPaneTest.log.log(Level.INFO, o.toString(), "");
			} else {
				g.setColor(Color.RED);
			}
			g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
			JEditorPaneTest.log.log(Level.INFO, rect.toString(), "");
			super.paint(g, a);
		}
		public MyTextView(Element elm) {
			super(elm);
		}
	}
	
	private static class MyCaret extends DefaultCaret {
		public void paint(Graphics g) {
			Point p = this.getMagicCaretPosition();
			if(p==null) {
				return;
			}
			g.setColor(Color.RED);
			g.drawOval(p.x, p.y, 16, 16);
		}
	}
	
	private static class MyTreeModel implements TreeModel {

		@Override
		public Object getRoot() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getChild(Object parent, int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getChildCount(Object parent) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isLeaf(Object node) {
			// TODO Auto-generated method stub
			return false;
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
	}
	
	public JEditorPaneTest() {
		
		this.editorPane = new JEditorPane();
//		this.editorPane.setCaret(new MyCaret());
		this.editorPane.setEditorKit(new MyEditorKit());
		this.editorPane.setText("Hello!\nThis is Sample.");
		this.editorPane.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				JEditorPaneTest.log.log(Level.INFO, e.toString());
				JEditorPaneTest.this.tree.treeDidChange();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				JEditorPaneTest.this.tree.treeDidChange();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				JEditorPaneTest.this.tree.treeDidChange();
			}
			
		});
		
		// JTree
		this.tree = new JTree((TreeNode)this.editorPane.getDocument().getDefaultRootElement());
		this.tree.setComponentPopupMenu(new JPopupMenu("JPopupMenu"));
		this.tree.getComponentPopupMenu().add(new JMenuItem(new AbstractAction("Reload") {
			@Override
			public void actionPerformed(ActionEvent e) {
				((AbstractDocument)JEditorPaneTest.this.editorPane.getDocument()).dump(System.out);
//				JEditorPaneTest.this.tree.treeDidChange();
//				JEditorPaneTest.dumpDocument(JEditorPaneTest.this.editorPane.getDocument());
			}
		}));
		
		log.log(Level.INFO, "Caret={0}", this.editorPane.getCaret().getClass().getName());
		log.log(Level.INFO, "Document={0}", this.editorPane.getDocument().getClass().getName());
		log.log(Level.INFO, "EditorKit={0}", this.editorPane.getEditorKit().getClass().getName());
		
		this.frame = new JFrame();
		this.frame.setSize(640, 480);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().add(
				new JSplitPane(
						JSplitPane.HORIZONTAL_SPLIT,
						new JScrollPane(this.tree),
						new JScrollPane(this.editorPane)
				)
		);
		this.frame.setVisible(true);
	}
	
	public static void dumpDocument(Document doc) {
		for(Element elm : doc.getRootElements()) {
			System.out.println(doc.toString());
			JEditorPaneTest.dumpElement(elm, 1);
		}
	}
	
	public static void dumpElement(Element elm, int level) {
		System.out.print("          ".substring(0, level) + elm.toString().replaceAll("[\r\n]", ""));
		dumpAttributeSet(elm.getAttributes());
		System.out.println();
		for(int i=0; i<elm.getElementCount(); i++) {
			JEditorPaneTest.dumpElement(elm.getElement(i), level+1);
		}
	}
	
	public static void dumpAttributeSet(AttributeSet att) {
		if(att==null || att.getAttributeCount()==0) {
			System.out.println("[]");
			return;
		}
		StringBuffer sb = new StringBuffer(128);
		sb.append("[");
		for(Enumeration<?> enm=att.getAttributeNames(); enm.hasMoreElements(); ) {
			Object name = enm.nextElement();
			sb.append(" ");
			sb.append(name.toString());
			sb.append("=");
			sb.append(att.getAttribute(name));
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" ]");
		System.out.print(sb.toString());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JEditorPaneTest();
			}
		});
	}
	
	public static Logger log = Logger.getLogger("JEditorPaneTestLogger");
}

package jtable;

import java.awt.*;
import javax.swing.*;

/*
 * @see https://community.oracle.com/thread/1395446
 */
public class TableRowHeader extends JList
{
     private JTable table;

     public TableRowHeader(JTable table)
     {
          this.table = table;

          setAutoscrolls( false );
          setCellRenderer(new RowHeaderRenderer());
          setFixedCellHeight(table.getRowHeight());
          setFixedCellWidth(50);
          setFocusable( false );
          setModel( new TableListModel() );
          setOpaque( false );
          setSelectionModel( table.getSelectionModel() );
     }

     /*
      *  Use the table to implement the ListModel
      */
     class TableListModel extends AbstractListModel
     {
          public int getSize()
          {
               return table.getRowCount();
          }

          public Object getElementAt(int index)
          {
               return String.valueOf(index + 1);
          }
     }

     /*
      *  Use the table row header properties to render each cell
      */
     class RowHeaderRenderer extends DefaultListCellRenderer
     {
          RowHeaderRenderer()
          {
               setHorizontalAlignment(CENTER);
               setOpaque(true);
               setBorder(UIManager.getBorder("TableHeader.cellBorder"));
               setFont(table.getTableHeader().getFont());
               setBackground(table.getTableHeader().getBackground());
               setForeground(table.getTableHeader().getForeground());
          }

          public Component getListCellRendererComponent(
               JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
          {
               if (isSelected)
               {
                    setBackground( table.getSelectionBackground() );
               }
               else
               {
                    setBackground( table.getTableHeader().getBackground() );
               }

               setText( (value == null) ? "" : value.toString() );

               return this;
          }
     }

     public static void main(String[] args)
     {
          JTable table = new JTable( 5, 10 );
          JScrollPane scrollPane = new JScrollPane( table );
          scrollPane.setRowHeaderView( new TableRowHeader( table) );

          JFrame frame = new JFrame("Row Header Test");
          frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
          frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
          frame.pack();
          frame.setLocationRelativeTo( null );
          frame.setVisible(true);
     }
}

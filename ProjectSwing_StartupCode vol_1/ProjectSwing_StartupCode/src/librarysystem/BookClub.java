package librarysystem;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;



@SuppressWarnings("serial")
public class BookClub extends JFrame implements MessageableWindow {

   String[] links;
   JList<ListItem> linkList;
   JPanel cards;
   public static JTextArea statusBar = new JTextArea("Welcome to the Library!");
   
   LoginPanel lp;
   AllBookTitles abip;
   //boolean startup = true;
   
   //list items
   ListItem loginListItem = new ListItem("Login", true);
   ListItem addBookItem = new ListItem("Add Book", false);
   ListItem checkoutBook = new ListItem("Checkout Book", false);
   ListItem addNewMember = new ListItem("Add New Member", false);
   ListItem addCopyOfBook = new ListItem("Add Copy Book", false);
   ListItem viewCheckOutRecords = new ListItem("View Checkout Records", false);
   ListItem viewOverdue = new ListItem("View Overdue List", false);
   
   
   ListItem[] librarianItems = {loginListItem, addBookItem, checkoutBook, viewCheckOutRecords, viewOverdue};
   ListItem[] adminItems = {loginListItem, addNewMember, addCopyOfBook};
   
   public ListItem[] getLibrarianItems() {
	   return librarianItems;
   }
   public ListItem[] getAdminItems() {
	   return adminItems;
   }
   
   public JList<ListItem> getLinkList() {
	   return linkList;
   }

   
   public BookClub() {
	  Util.adjustLabelFont(statusBar, Util.DARK_BLUE, true);
      setSize(600, 450);
      
      createLinkLabels();
      createMainPanels();
      CardLayout cl = (CardLayout)(cards.getLayout());
      linkList.addListSelectionListener(event ->
      	{
    	  String value = linkList.getSelectedValue().getItemName();
    	  boolean allowed = linkList.getSelectedValue().highlight();
    	  System.out.println(value + " " + allowed);
    	  
    	  //System.out.println("selected = " + value);
    	  //cl = (CardLayout)(cards.getLayout());
	      statusBar.setText("");
	      if(!allowed) {
    		  value = loginListItem.getItemName();
    		  linkList.setSelectedIndex(0);
    	  }
	      if(value.equals("View Titles")) abip.updateData();
	      cl.show(cards,value);
	   
   
      	});
      

      // set up split panes

      JSplitPane innerPane 
          = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
      innerPane.setDividerLocation(180);
      JSplitPane outerPane 
          = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, statusBar);
      outerPane.setDividerLocation(350);
      add(outerPane, BorderLayout.CENTER);
      lp.setBookClub(this);
  
   }
   
   public JTextArea getStatusBar() {
 	  return statusBar;
   }
   
   public void createLinkLabels() {
	    DefaultListModel<ListItem> model = new DefaultListModel<>();
	    
		model.addElement(loginListItem);
		model.addElement(addBookItem);
		model.addElement(checkoutBook);
		model.addElement(viewCheckOutRecords);
		model.addElement(viewOverdue);
		model.addElement(addNewMember);
		model.addElement(addCopyOfBook);
	
		linkList = new JList<ListItem>(model);
	    linkList.setCellRenderer(new DefaultListCellRenderer() {

			@SuppressWarnings("rawtypes")
			@Override
			public Component getListCellRendererComponent(JList list, 
					Object value, int index,
					boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, 
						value, index, isSelected, cellHasFocus);
				if (value instanceof ListItem) {
					ListItem nextItem = (ListItem) value;
					setText(nextItem.getItemName());
					if (nextItem.highlight()) {
						setForeground(Util.LINK_AVAILABLE);
					} else {
						setForeground(Util.LINK_NOT_AVAILABLE);
					}
					if (isSelected) {
						setForeground(Color.BLACK);
						setBackground(new Color(236,243,245));
						//setBackground(Color.WHITE);
					}
				} 
				return c;
			}

		});
   }
 
   public void createMainPanels() {
	   //login 
	   lp = new LoginPanel();
	   JPanel loginPanel = lp.getMainPanel();
	   
	   
	   //add book
	   AddBookPanel abp = new AddBookPanel();
	   JPanel addBookPanel = abp.getMainPanel();
	   
	   
	   //all book IDs
	   abip = new AllBookTitles();
	   JPanel allTitlesPanel = abip.getMainPanel();
	   
	   
	   cards = new JPanel(new CardLayout());
//		model.addElement(loginListItem);
//		model.addElement(addBookItem);
//		model.addElement(checkoutBook);
//		model.addElement(viewCheckOutRecords);
//		model.addElement(viewOverdue);
//		model.addElement(loginListItem);
//		model.addElement(addNewMember);
//		model.addElement(addCopyOfBook);
       cards.add(loginPanel, loginListItem.getItemName());
       cards.add(addBookPanel, addBookItem.getItemName());
       cards.add(allTitlesPanel, checkoutBook.getItemName());
    
       
   }
   @Override
   public void updateData() {
	// nothing to do
	
}
}
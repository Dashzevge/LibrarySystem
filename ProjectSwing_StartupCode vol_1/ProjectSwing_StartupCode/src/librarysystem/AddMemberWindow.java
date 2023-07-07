package librarysystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.ControllerInterface;
import business.SystemController;
import librarysystem.CreateNewBookWindow.BackToMainListener;

public class AddMemberWindow extends JFrame implements LibWindow {
	
	private static final long serialVersionUID = 1L;
	public static final AddMemberWindow INSTANCE = new AddMemberWindow();
    ControllerInterface ci = new SystemController();
	private boolean isInitialized = false;
	
	private JPanel mainPanel, topPanel, middlePanel, fContainer, lContainer, idContainer, telContainer, lowerPanel;
	
	private JTextField first, last, telephone, id;
	
	private JLabel firstL, lastL, telL, idL;
	
	private AddMemberWindow() {}

	@Override
	public void init() {
		this.setSize(500, 500);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);	
		mainPanel.add(lowerPanel, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
		isInitialized = true;
		
	}
	
	public void defineTopPanel() {
		topPanel = new JPanel();
		JLabel AllIDsLabel = new JLabel("Add Library Member");
		Util.adjustLabelFont(AllIDsLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(AllIDsLabel);
	}
	
	public void defineMiddlePanel() {
		middlePanel = new JPanel();
		fContainer = new JPanel();
		lContainer = new JPanel();
		idContainer = new JPanel();
		telContainer = new JPanel();
		
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
		
		fContainer.setLayout(fl);
		lContainer.setLayout(fl);
		idContainer.setLayout(fl);
		telContainer.setLayout(fl);
		middlePanel.setLayout(fl);
		
		idL = new JLabel("Member Id: ");
		id = new JTextField(4);
		idContainer.add(idL);
		idContainer.add(id);
		
		firstL = new JLabel("First Name: ");
		first = new JTextField(12);
		fContainer.add(firstL);
		fContainer.add(first);
		
		lastL = new JLabel("Last Name: ");
		last = new JTextField(12);
		lContainer.add(lastL);
		lContainer.add(last);
		
		telL = new JLabel("Telephone: ");
		telephone = new JTextField(10);
		telContainer.add(telL);
		telContainer.add(telephone);
		
		middlePanel.add(fContainer);
		middlePanel.add(lContainer);
		middlePanel.add(idContainer);
		middlePanel.add(telContainer);
		
	}
	
	public void defineLowerPanel() {
		JButton backToMainButn = new JButton("<= Back to Main");
		JButton addNewBookBtn = new JButton("Add new Book =>");
		backToMainButn.addActionListener(new BackToMainListener());
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));;
		lowerPanel.add(backToMainButn);
		lowerPanel.add(addNewBookBtn);
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
		
	}


	class BackToMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
    		
		}
	}
}

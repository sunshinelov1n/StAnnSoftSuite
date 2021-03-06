package ccf.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import custom.ErrorDialog;
import ccf.CCFData;
import database.DatabaseStore;
import database.DbWorker;

public class CCFEmailDialog extends JDialog{

	/**
	 *
	 */
	private static final long serialVersionUID = 4957309745869936744L;
	private ArrayList<CCFData> data;
	private JComboBox<String> emailList;
	private JLabel emailDisplay;
	private JTextField emailField;
	private HashMap<String, String> emailMap;

	public CCFEmailDialog()
	{
		setResizable(false);
		JButton btnSave = new JButton("Save");

//		setLayout(new GridLayout(2,3));
		getContentPane().setLayout(new BorderLayout());
		((JPanel)getContentPane()).setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		emailMap = new HashMap<String, String>();
		emailList = new JComboBox<String>();
		emailDisplay = new JLabel("\tNo Email Listed\t");
		emailField = new JTextField("Email");

		JPanel topPanel = new JPanel(new GridLayout(1,2));
		topPanel.add(emailList);
		topPanel.add(emailDisplay);
		
		JPanel centerPanel = new JPanel(new GridLayout(3,1));
		centerPanel.add(new JLabel("Please Enter New Email Address:"));
		centerPanel.add(emailField);
//		add(emailList);
//		add(emailDisplay);
		getContentPane().add(topPanel,BorderLayout.NORTH);
		getContentPane().add(centerPanel);
//		add(emailField);
		getContentPane().add(btnSave, BorderLayout.SOUTH);

		btnSave.addActionListener(new SaveListener());
		
		emailList.addActionListener(new DropDownListener());
		
		setSize(500, 170);

//		populateDropBox();
	}

	public CCFEmailDialog(ArrayList<CCFData> dataList)
	{
		this();
		this.data = dataList;

	}
	
	public void addNames(ArrayList<CCFData> dataList)
	{
		emailMap.clear();
		this.data = dataList;
		populateDropBox();
	}

	private void populateDropBox() {

		if(data != null)
		{
			for(CCFData data : this.data)
			{
				emailMap.put(data.getName(), data.getEmailAddress());
			}

			for(String fullName: emailMap.keySet())
			{
				emailList.addItem(fullName);
			}
		}
	}
	

	private class DropDownListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox dropDown = (JComboBox)e.getSource();
			String name = (String)dropDown.getSelectedItem();
			emailField.setText(emailMap.get(name));
			emailDisplay.setText(emailMap.get(name));
			System.out.println("Name =" + name);

		}

	}

	private class SaveListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name = (String)emailList.getSelectedItem();
			System.out.println("Name = " + name);

			if(name != null)
			{
				emailMap.put(name, emailField.getText());
			}
			
			emailDisplay.setText(emailMap.get(name));
			
			try {
			DbWorker dbWorker = new DbWorker();
			dbWorker.updateCCF(name, emailMap.get(name));
			dbWorker.dbClose();
		} catch (SQLException e1) {
			new ErrorDialog(e1);
			e1.printStackTrace();
		}

		}

	}

//	public void disableDialog(boolean state) 
//	{
//		for(Component comp: getComponents())
//		{
//			comp.setEnabled(state);
//		}
//		repaint();
//		
//	}


}

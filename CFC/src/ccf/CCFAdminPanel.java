package ccf;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.MainFrame;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.search.SearchFactory;

public class CCFAdminPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1647988074485239841L;
	private final MainFrame mainGui;
	private JXTable resultTable;
	private CCFAdminTableModel adminModel;

	public CCFAdminPanel(MainFrame mainGui) {
		this.mainGui = mainGui;

		JPanel btnPanel = new JPanel(new GridLayout(1,2));
		JButton btnSave = new JButton("Save Changes");
		JButton btnCancel = new JButton("Cancel Edit");
		JButton btnFind = new JButton("Find");

		setBorder(BorderFactory.createTitledBorder("CCF Admin"));
		setLayout(new BorderLayout());

		btnFind.addActionListener(new FindActionListener());
		btnPanel.add(btnSave);
		btnPanel.add(btnCancel);
		btnPanel.add(btnFind);

		adminModel = new CCFAdminTableModel(CCFAdminTableModel.adminColumnNames, 0);
		resultTable = new JXTable(adminModel);
		resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		add(btnPanel, BorderLayout.NORTH);
		add(new JScrollPane(resultTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.CENTER);

	}

	public void displayAll()
	{
		resultTable.packAll();
		mainGui.setPanel(this);
	}

	public void displayAll(ArrayList<CCFData> retrieveCCFData) {
		for(CCFData data: retrieveCCFData)
		{
			adminModel.addRow(new CCFAdminFieldData(data).getObjArray());
		}
		displayAll();

	}

	public class SaveActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Saving Changes");

		}

	}

	public class CloseActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);

		}

	}

	private class FindActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			SearchFactory mySearch = new SearchFactory();
			mySearch.showFindInput(resultTable, resultTable.getSearchable());

		}

	}



}

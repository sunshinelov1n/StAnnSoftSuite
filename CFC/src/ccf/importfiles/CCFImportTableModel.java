package ccf.importfiles;

import javax.swing.table.DefaultTableModel;

import com.healthmarketscience.jackcess.Table;

/**
 * This is the custom table model for the CCF Table.
 * @author AL
 *
 */
public class CCFImportTableModel extends DefaultTableModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 8470548068140479664L;

	// Add "\u2713" for check symbol.
	protected final static String[] columnNames = {"ID", "Student ID",
		"Parish ID", "Date Of Birth",
		"Function", "Status",
		"Last Name", "First Name",
		"Full Name", "Class Offered ID",
		"Hours Credited", "Class Date",
		"Title", "Instructor", "Experience",
	"Course Level"};




	public CCFImportTableModel(Table table)
	{
		super(columnNames,0);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}


}

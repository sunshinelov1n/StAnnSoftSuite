package ccf;

import dialog.ImportDialog;

public class CCFImportDialog extends ImportDialog {

	private static final long serialVersionUID = 2976549852767653724L;

	public CCFImportDialog()
	{
		super("Select CCF File to Import", new CCFActionListener());
	}

}
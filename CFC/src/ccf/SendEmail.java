package ccf;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

import custom.ErrorDialog;
import database.DatabaseStore;
import database.DbWorker;

public class SendEmail {
	public SendEmail()
	{
		ArrayList<String> result = new ArrayList<String>();
		try {
			DbWorker db = new DbWorker();
			result = db.retrieveCCFEmails();
			
			String output = (result.toString().replace("[", "")).replace("]", "").replace(" ", "");
			
			
			Desktop.getDesktop().browse(new URI("mailto:"+output+ "?subject=Info"));
		} catch (IOException | URISyntaxException | SQLException e) {
			new ErrorDialog(e);
			e.printStackTrace();
		}
	}

}

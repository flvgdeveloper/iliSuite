package iliSuite.plugin.ili2gpkg.view;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import base.controller.IController;
import base.dbconn.AbstractConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Button;

public class DatabaseOptionsController implements IController, Initializable {
	@FXML
	private ResourceBundle applicationBundle;

	private AbstractConnection connection;

	private boolean createSchema;

	@FXML
	private TextField txtFile;

	@FXML
	private Button btnBrowse;
	
	private List<Node> listOfRequired;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		applicationBundle = arg1;
		listOfRequired = new ArrayList<>();
		listOfRequired.add(txtFile);
	}

	@Override
	public Map<String, String> getParams() {
		Map<String, String> result = null;

		if (validateRequiredFields()) { // TODO Agregar validaci�n de campos

			String file = txtFile.getText();

			Map<String, String> params = new HashMap<String, String>();

			params.put("dbfile", file);

			connection.setConnectionParams(params);

			result = new HashMap<String, String>();

			if (file != null)
				result.put("--dbfile", file);

		}

		return result;
	}

	@Override
	public void setConnection(AbstractConnection connection) {
		this.connection = connection;
	}

	public void setCreateSchema(boolean createSchema) {
		this.createSchema = createSchema;
	}

	public void onClickBrowse(ActionEvent e) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.addAll(new ExtensionFilter(applicationBundle.getString("datafile.extension.xtf"), "*.gpkg"));

		Window window = ((Node) e.getSource()).getScene().getWindow();

		File selectedFile = null;

		if (createSchema) {
			fileChooser.setTitle(applicationBundle.getString("datafile.saveAs"));
			selectedFile = fileChooser.showSaveDialog(window);
		} else {
			fileChooser.setTitle(applicationBundle.getString("datafile.choose"));
			selectedFile = fileChooser.showOpenDialog(window);
		}

		if (selectedFile != null)
			txtFile.setText(selectedFile.getAbsolutePath());

	}
	
	protected boolean validateRequiredFields(){
		boolean toValid = true;
		for (Node n : listOfRequired) {
			if (n instanceof TextField) {
				if (((TextField) n).getText().isEmpty()) {
					((TextField) n).setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
					((TextField) n).setTooltip(new Tooltip(applicationBundle.getString("general.required")));
					((TextField) n).setOnKeyReleased(event -> {
						((TextField) n).setStyle(null);
						((TextField) n).setTooltip(null);
						});
					toValid = false;
				}
			}
		}
		return toValid;
	}

}
package ai.ilisuite.impl.ili2pg;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import ai.ilisuite.impl.PanelCustomizable;
import ai.ilisuite.impl.ili2pg.view.SchemaImportPanelController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class SchemaImportPanel implements PanelCustomizable{
	
	private SchemaImportPanelController customPanel;
	
	@Override
	public Parent getPanel() {
		Parent cp = null;
		ResourceBundle bundle = ResourceBundle.getBundle("ai.ilisuite.impl.ili2pg.resources.application");
		FXMLLoader loader = new FXMLLoader(Ili2pgImpl.class.getResource("/ai/ilisuite/impl/ili2pg/view/SchemaImportPanel.fxml"), bundle);
		
		customPanel = new SchemaImportPanelController();
		
		loader.setController(customPanel);
		
		try {
			cp = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cp;
	}

	@Override
	public Map<String, String> getParams() {
		return customPanel.getParams();
	}
	
	@Override
	public List<String> getParamsForRemove() {
		return customPanel.getParamsForRemove();
	}

	@Override
	public String getName() {
		ResourceBundle bundle = ResourceBundle.getBundle("ai.ilisuite.impl.ili2pg.resources.application");
		return bundle.getString("customPanel.Title");
	}
}

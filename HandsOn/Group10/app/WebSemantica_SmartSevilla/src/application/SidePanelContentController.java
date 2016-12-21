package application;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class SidePanelContentController implements Initializable {

	@FXML private JFXButton b1;
	@FXML private JFXButton b2;
	@FXML private JFXButton b3;
	@FXML private JFXButton exit;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}    

	@FXML
	private void transportPanel(ActionEvent event) {
		JFXButton btn = (JFXButton) event.getSource();
		System.out.println(btn.getText());

		if(btn.getText().equals("Museos"))
		{
			MainController.paneMuseosP.setVisible(true);
			MainController.paneMonumentosP.setVisible(false);
			MainController.paneConocenosP.setVisible(false);
			MainController.homeP.setVisible(false);
		}

		if(btn.getText().equals("              Lugares de Interes"))
		{
			MainController.paneMonumentosP.setVisible(true);
			MainController.paneMuseosP.setVisible(false);
			MainController.paneConocenosP.setVisible(false);
			MainController.homeP.setVisible(false);

		}
		
		if(btn.getText().equals("Salir"))
		{
			System.exit(0);
		}
		
		if(btn.getText().equals("       Conocenos"))
		{
			MainController.paneConocenosP.setVisible(true);
			MainController.paneMonumentosP.setVisible(false);
			MainController.paneMuseosP.setVisible(false);
			MainController.homeP.setVisible(false);

		}

	}
	@FXML
	private void exit(ActionEvent event) {
		System.exit(0);
	}



}

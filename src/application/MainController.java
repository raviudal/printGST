package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController {
	@FXML
	AnchorPane anchorPane1 = new AnchorPane();
	@FXML
	Button ButtonPurchase = new Button();
	@FXML
	Button ButtonBilling = new Button();
	@FXML
	Button ButtonFareBilling = new Button();
	@FXML
	Button ButtonCustomer = new Button();
	@FXML
	Button ButtonItems = new Button();
	@FXML
	Button ButtonDealer = new Button();
	@FXML
	Button ButtonReports = new Button();
	//setting event listner for buttons
	public void ButtonBilling(ActionEvent event) {
		anchorPane1.getChildren().clear();
		try {
			anchorPane1.getChildren().add(FXMLLoader.load(getClass().getResource("Billing.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ButtonPurchase(ActionEvent event) {
		anchorPane1.getChildren().clear();
		try {
			anchorPane1.getChildren().add(FXMLLoader.load(getClass().getResource("Purchase.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ButtonFareBilling(ActionEvent event) {
		anchorPane1.getChildren().clear();
		try {
			anchorPane1.getChildren().add(FXMLLoader.load(getClass().getResource("Purchase.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ButtonCustomer(ActionEvent event) {
		anchorPane1.getChildren().clear();
		try {
			anchorPane1.getChildren().add(FXMLLoader.load(getClass().getResource("Customer.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ButtonItems(ActionEvent event) {
		anchorPane1.getChildren().clear();
		try {
			anchorPane1.getChildren().add(FXMLLoader.load(getClass().getResource("Items.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ButtonDealer(ActionEvent event) {
		anchorPane1.getChildren().clear();
		try {
			anchorPane1.getChildren().add(FXMLLoader.load(getClass().getResource("Dealer.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ButtonReports(ActionEvent event) {
		anchorPane1.getChildren().clear();
		try {
			anchorPane1.getChildren().add(FXMLLoader.load(getClass().getResource("Reports.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ButtonCustomerDeails(ActionEvent event) {
		anchorPane1.getChildren().clear();
		try {
			anchorPane1.getChildren().add(FXMLLoader.load(getClass().getResource("companyDetails.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}

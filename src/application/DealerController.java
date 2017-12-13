package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DealerController {
// inner class for entering data into the table from observablelist;
	public class Customer{
		private StringProperty name  ;
		private StringProperty gstin ;
		private StringProperty addr;
		private StringProperty contact;
		private IntegerProperty id;
		
		public String getName() {
			return name.get();
		}

		public void setName(String name) {
			this.name.set(name); 
		}

		public String getGstin() {
			return gstin.get();
		}

		public void setGstin(String gstin) {
			this.gstin.set(gstin);
		}

		public String getAddr() {
			return addr.get();
		}

		public void setAddr(String addr) {
			this.addr.set(addr);
		}
		
		public String getContact() {
			return contact.get();
		}

		public void setContact(String contact) {
			this.contact.set(contact);
		}
		public Integer getId() {
			return id.get();
		}

		public void setId(Integer id) {
			this.id.set(id); 
		}

		Customer(String name, String gstin, String addr, String contact, Integer id){
			this.name = new SimpleStringProperty(name);
			this.gstin = new SimpleStringProperty(gstin);
			this.addr = new SimpleStringProperty(addr);
			this.contact = new SimpleStringProperty(contact);
			this.id = new SimpleIntegerProperty(id);
		}
		


	}
	@FXML
	TextField dealerCustomerName = new TextField();
	String mDealerCustomerName = "";
	@FXML
	TextField dealerGSTINNumner = new TextField();
	String mDealerGSTINNumner = "";
	@FXML
	TextField dealerAdderss = new TextField();
	String mDealerAdderss = "";
	@FXML
	TextField dealerContact = new TextField();
	String mDealerContact = "";

	@FXML
	Button dealerAdddealer = new Button();

	//tableview for displaying data into table
	@FXML
	TableView<Customer> tableview = new TableView<>();
	//all the data to be displayed in the table is stored in this observable list
	ObservableList<Customer> data = FXCollections.observableArrayList();
	//all the coloumns of the table are intialized here
	@FXML
	TableColumn<Customer, String> collName = new TableColumn<>();
	@FXML
	TableColumn<Customer, String> collGstin = new TableColumn<>();
	@FXML
	TableColumn<Customer, String> collAddr = new TableColumn<>();
	@FXML
	TableColumn<Customer, String> collContact = new TableColumn<>();
	@FXML
	TableColumn<Customer, Integer> collId = new TableColumn<>();
	// getting connections
	Connection conn = Connections.b2csDBConncetion();
	
	@FXML
	public void initialize() throws SQLException{
		
		String sql = "SELECT * FROM dealer";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			data.add(new Customer(
					rs.getString("dealer_company_name"),
					rs.getString("dealer_company_addr"),
					rs.getString("dealer_company_gstn"),
					rs.getString("dealer_company_contact"),
					rs.getInt("id")
					
					));
		
		}
		
		tableview.setItems(data);
		collId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		collName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		collGstin.setCellValueFactory(new PropertyValueFactory<Customer, String>("gstin"));
		collAddr.setCellValueFactory(new PropertyValueFactory<Customer, String>("addr"));
		collContact.setCellValueFactory(new PropertyValueFactory<Customer, String>("contact"));
		
		
	}
	
//dealer_company_name,dealer_company_addr,dealer_mobile,dealer_company_gstn,dealer_company_tin
	// button Functionality
	public void dealerAddDealer(ActionEvent event) throws SQLException {
		mDealerCustomerName = dealerCustomerName.getText().toString();
		mDealerGSTINNumner = dealerGSTINNumner.getText().toString() ;
		mDealerAdderss = dealerAdderss.getText().toString();
		mDealerContact = dealerContact.getText().toString();

		String sql = "INSERT INTO dealer (dealer_company_name,dealer_company_addr,dealer_company_gstn,dealer_company_contact) VALUES (\'"
				+ mDealerCustomerName + "','" + mDealerAdderss + "','" + mDealerGSTINNumner + "','" + mDealerContact +"\')";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		//setting the observable list - data so that the table updates
		Customer cust = new Customer(mDealerCustomerName, mDealerGSTINNumner, mDealerAdderss, mDealerContact, 0);
		data.add(cust);
		
		//clearing the fields 
		dealerCustomerName.clear();
		dealerGSTINNumner.clear();
		dealerAdderss.clear();
		dealerContact.clear();

		//closing the database objects
		stmt.close();
	}
	
	public void editDealer() throws  IOException, SQLException{
		
		int id = tableview.getSelectionModel().getSelectedItem().getId();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editDealerdialoge.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		((editDealerdialogeController) fxmlLoader.getController()).getDataFrom(id);
		stage.show();
	}
	
	public void refreshDealer() throws SQLException{
		
		data.clear();
		String sql = "SELECT * FROM dealer";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			data.add(new Customer(
					rs.getString("dealer_company_name"),
					rs.getString("dealer_company_gstn"),
					rs.getString("dealer_company_addr"),
					rs.getString("dealer_company_contact"),
					rs.getInt("id")
					
					));
		
		}
		
		tableview.setItems(data);
		collId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		collName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		collGstin.setCellValueFactory(new PropertyValueFactory<Customer, String>("gstin"));
		collAddr.setCellValueFactory(new PropertyValueFactory<Customer, String>("addr"));
		collContact.setCellValueFactory(new PropertyValueFactory<Customer, String>("contact"));
		
	}
	
	public void deleteDealer() throws SQLException{
		int id = tableview.getSelectionModel().getSelectedItem().getId();
		String sql = "DELETE FROM dealer WHERE id = " + id ;
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		refreshDealer();

		
		
	}
	
	
	
}

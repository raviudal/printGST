package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.ItemsController.items;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerController {
	// inner class for haveing the customer data;;
	public class Customer {
		private IntegerProperty id;
		private StringProperty name;
		private StringProperty moblieNumber;
		private StringProperty address;
		private StringProperty gstin;
		private FloatProperty dueBalance = new SimpleFloatProperty(0f);
		private FloatProperty advanceBalance = new SimpleFloatProperty(0f);

		public Customer(String name, String moblie, String address, String gstin) {
			super();
			this.name = new SimpleStringProperty(name);
			this.moblieNumber = new SimpleStringProperty(moblie);
			this.address = new SimpleStringProperty(address);
			this.gstin = new SimpleStringProperty(gstin);
		}

		public Customer(String name, String moblie, String address, String gstin, Float dueBalance,
				Float AdvanceBalance, Integer id) {
			super();
			this.id = new SimpleIntegerProperty(id);
			this.name = new SimpleStringProperty(name);
			this.moblieNumber = new SimpleStringProperty(moblie);
			this.address = new SimpleStringProperty(address);
			this.gstin = new SimpleStringProperty(gstin);
			this.dueBalance = new SimpleFloatProperty(dueBalance);
			this.advanceBalance = new SimpleFloatProperty(AdvanceBalance);
		}

		public Integer getId() {
			return id.get();
		}

		public void setId(Integer id) {
			this.id.set(id);
			;
		}

		public String getName() {
			return name.get();
		}

		public void setName(String name) {
			this.name.set(name);
			;
		}

		public String getMoblieNumber() {
			return moblieNumber.get();
		}

		public void setMoblieNumber(String moblieNumber) {
			this.moblieNumber.set(moblieNumber);
		}

		public String getAddress() {
			return address.get();
		}

		public void setAddress(String address) {
			this.address.set(address);
			;
		}

		public String getGstin() {
			return gstin.get();
		}

		public void setGstin(String gstin) {
			this.gstin.set(gstin);
			;
		}

		public Float getDueBalance() {
			return dueBalance.get();
		}

		public void setDueBalance(Float dueBalance) {
			this.dueBalance.set(dueBalance);
		}

		public Float getAdvanceBalance() {
			return advanceBalance.get();
		}

		public void setAdvanceBalance(Float advanceBalance) {
			this.advanceBalance.set(advanceBalance);
		}

	}

	@FXML
	AnchorPane anchorPane = new AnchorPane();
	// getting connections
	Connection conn = Connections.b2csDBConncetion();
	@FXML
	TextField customerCustomerName = new TextField();
	String mCustomerName = "";
	@FXML
	TextField customerMobileNumber = new TextField();
	String mCustomerNumber = "";
	@FXML
	TextField customerAdderss = new TextField();
	String mCustomerAddress = "";
	@FXML
	TextField customerGstin = new TextField();
	String mCustomerGstin = "";

	@FXML
	Button customerAddCustomer = new Button();

	@FXML
	TableView<Customer> table = new TableView<>();
	ObservableList<Customer> data = FXCollections.observableArrayList();

	@FXML
	TableColumn<Customer, String> collid = new TableColumn<>();
	@FXML
	TableColumn<Customer, String> collname = new TableColumn<>();
	@FXML
	TableColumn<Customer, Integer> collmobile = new TableColumn<>();
	@FXML
	TableColumn<Customer, String> collgstin = new TableColumn<>();
	@FXML
	TableColumn<Customer, String> colladdr = new TableColumn<>();
	@FXML
	TableColumn<Customer, Float> collduebalance = new TableColumn<>();
	@FXML
	TableColumn<Customer, Float> colladvancebalance = new TableColumn<>();

	@FXML
	public void initialize() throws SQLException {

		String sql = "SELECT * FROM customer";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			data.add(new Customer(rs.getString("customerCustomerName"), rs.getString("customerMobileNumber"),
					rs.getString("customerAdderss"), rs.getString("customerGstinNumber"), rs.getFloat("dueBalance"),
					rs.getFloat("advanceBalance"), rs.getInt("id")));
			System.out.println(rs.getFloat("dueBalance"));
			System.out.println(rs.getFloat("advanceBalance"));
		}
		// rs.close();
		// stmt.close();
		//System.out.println(data.get(0).getDueBalance() + " " + data.get(1).getDueBalance());
		table.setItems(data);
		collid.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
		collname.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		collmobile.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("moblieNumber"));
		colladdr.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		collgstin.setCellValueFactory(new PropertyValueFactory<Customer, String>("gstin"));
		collduebalance.setCellValueFactory(new PropertyValueFactory<Customer, Float>("dueBalance"));
		colladvancebalance.setCellValueFactory(new PropertyValueFactory<Customer, Float>("advanceBalance"));

		// System.out.println(data.get(0).address);

	}

	// button Functionality
	public void customerAddCustomer(ActionEvent event) throws SQLException {
		mCustomerName = customerCustomerName.getText().toString();
		mCustomerNumber = customerMobileNumber.getText().toString();
		mCustomerGstin = customerGstin.getText().toString();
		mCustomerAddress = customerAdderss.getText().toString();

		String sql = "INSERT INTO customer (customerCustomerName, customerMobileNumber, customerAdderss, customerGstinNumber) VALUES (\'"
								+ mCustomerName + "','" + mCustomerNumber + "','" + mCustomerAddress + "','" + mCustomerGstin + "\')";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);

		Customer cust = new Customer(mCustomerName, mCustomerNumber, mCustomerAddress, mCustomerGstin, 0f, 0f, 0);
		data.add(cust);

		// clearing the fields
		customerCustomerName.clear();
		customerMobileNumber.clear();
		customerAdderss.clear();
		customerGstin.clear();

		// closing the database objects
		stmt.close();

	}

	public void editCustomer() throws IOException, SQLException {
		int itemId = table.getSelectionModel().getSelectedItem().getId();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editCustomerDialoge.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		((editCustomerDialogeController) fxmlLoader.getController()).getDataFrom(itemId);
		stage.show();
	}

	public void refreshTable() throws SQLException {
		data.clear();
		String sql = "SELECT * FROM customer";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			data.add(new Customer(rs.getString("customerCustomerName"), rs.getString("customerMobileNumber"),
					rs.getString("customerAdderss"), rs.getString("customerGstinNumber"), rs.getFloat("dueBalance"),
					rs.getFloat("advanceBalance"), rs.getInt("id")));
		}

		
		table.setItems(data);
		collid.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
		collname.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		collmobile.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("moblieNumber"));
		colladdr.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		collgstin.setCellValueFactory(new PropertyValueFactory<Customer, String>("gstin"));
		collduebalance.setCellValueFactory(new PropertyValueFactory<Customer, Float>("dueBalance"));
		colladvancebalance.setCellValueFactory(new PropertyValueFactory<Customer, Float>("advanceBalance"));
		
		stmt.close();
		rs.close();
	}
	
	public void deletecustomer() throws SQLException{
		int id = table.getSelectionModel().getSelectedItem().getId();
		String sql = "DELETE FROM customer WHERE id = " + id ;
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);

		data.clear();
		sql = "SELECT * FROM customer";
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			data.add(new Customer(rs.getString("customerCustomerName"), rs.getString("customerMobileNumber"),
					rs.getString("customerAdderss"), rs.getString("customerGstinNumber"), rs.getFloat("dueBalance"),
					rs.getFloat("advanceBalance"), rs.getInt("id")));
		}

		
		table.setItems(data);
		collid.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
		collname.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		collmobile.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("moblieNumber"));
		colladdr.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		collgstin.setCellValueFactory(new PropertyValueFactory<Customer, String>("gstin"));
		collduebalance.setCellValueFactory(new PropertyValueFactory<Customer, Float>("dueBalance"));
		colladvancebalance.setCellValueFactory(new PropertyValueFactory<Customer, Float>("advanceBalance"));
		
		stmt.close();
		rs.close();
		
		
	}
	
	
	
	
}

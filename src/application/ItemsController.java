package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.BillingController.items;
import application.CustomerController.Customer;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;




public class ItemsController {

	public class items {
		private IntegerProperty itemId;
		private StringProperty name;
		private StringProperty hsnNo;
		private StringProperty qtyType;
		private FloatProperty gstRate;
		private FloatProperty cgst;
		private FloatProperty sgst;
		private FloatProperty cess;
		private FloatProperty stock;
		private FloatProperty costPrice;
		private FloatProperty sellingPrice;

		public items() {
			super();
		}

		public items(int itemId, String name, String hsnNo, String qtyType, Float gstRate, Float cgst, Float sgst,
				Float cess, Float stock, Float costPrice, Float sellingPrice) {
			super();
			this.itemId = new SimpleIntegerProperty(itemId);
			this.name = new SimpleStringProperty(name);
			this.hsnNo = new SimpleStringProperty(hsnNo);
			this.qtyType = new SimpleStringProperty(qtyType);
			this.gstRate = new SimpleFloatProperty(gstRate);
			this.cgst = new SimpleFloatProperty(cgst);
			this.sgst = new SimpleFloatProperty(sgst);
			this.cess = new SimpleFloatProperty(cess);
			this.stock = new SimpleFloatProperty(stock);
			this.costPrice = new SimpleFloatProperty(costPrice);
			this.sellingPrice = new SimpleFloatProperty(sellingPrice);
		}

		public int getItemId() {
			return itemId.get();
		}

		public void setItemId(int itemId) {
			this.itemId.set(itemId);
		}

		public String getName() {
			return name.get();
		}

		public void setName(String name) {
			this.name.set(name);
		}

		public String getHsnNo() {
			return hsnNo.get();
		}

		public void setHsnNo(String hsnNo) {
			this.hsnNo.set(hsnNo);
		}

		public String getQtyType() {
			return qtyType.get();
		}

		public void setQtyType(String qtyType) {
			this.qtyType.set(qtyType);
		}

		public Float getGstRate() {
			return gstRate.get();
		}

		public void setGstRate(Float gstRate) {
			this.gstRate.set(gstRate);
		}

		public Float getCgst() {
			return cgst.get();
		}

		public void setCgst(Float cgst) {
			this.cgst.set(cgst);
		}

		public Float getSgst() {
			return sgst.get();
		}

		public void setSgst(Float sgst) {
			this.sgst.set(sgst);
		}

		public Float getCess() {
			return cess.get();
		}

		public void setCess(Float cess) {
			this.cess.set(cess);
		}

		public Float getStock() {
			return stock.get();
		}

		public void setStock(Float stock) {
			this.stock.set(stock);
			;
		}

		public Float getCostPrice() {
			return costPrice.get();
		}

		public void setCostPrice(Float costPrice) {
			this.costPrice.set(costPrice);
		}

		public Float getSellingPrice() {
			return sellingPrice.get();
		}

		public void setSellingPrice(Float sellingPrice) {
			this.sellingPrice.set(sellingPrice);
		}

	}

	@FXML
	Label itemsID = new Label();
	int mItemsID = 0;
	@FXML
	TextField itemsItemName = new TextField();
	String mItemsItemName = "";
	// items field for making bill
	@FXML
	TextField itemsHSNNumber = new TextField();
	String mItemsHSNNumber = "";
	@FXML
	ChoiceBox<String> qtyType = new ChoiceBox<>();
	String mQtyType = "";
	@FXML
	ChoiceBox<String> gstRate = new ChoiceBox<>();
	float mGstRate = 0;
	@FXML
	Label itemsCGST = new Label();
	float mItemsCGST = 0;
	@FXML
	Label itemsSGST = new Label();
	float mItemsSGST = 0;
	@FXML
	TextField itemsCESS = new TextField();
	float mItemsCESS = 0;
	@FXML
	Label itemsStock = new Label();
	float mItemsStock = 0;
	@FXML
	TextField itemsCostPrice = new TextField();
	float mItemsCostPrice = 0;
	@FXML
	TextField itemsSellingPrice = new TextField();
	float mItemsSellingPrice = 0;
	@FXML
	Button itemsAddItems = new Button();
	// field for grand total and save and print button
	@FXML
	Label billinGrandTotal = new Label();
	@FXML
	Button billinSaveAndPrintButton = new Button();

	@FXML
	TableView<items> table = new TableView<>();
	@FXML
	TableColumn<items, Integer> collmItemsID = new TableColumn<>();
	@FXML
	TableColumn<items, String> collName = new TableColumn<>();
	@FXML
	TableColumn<items, String> collHSNNumber = new TableColumn<>();
	@FXML
	TableColumn<items, String> collQtyType = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collGstRate = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collCGST = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collSGST = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collCESS = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collStock = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collCostPrice = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collSellingPrice = new TableColumn<>();

	ObservableList<items> data = FXCollections.observableArrayList();

	@FXML
	public void initialize() throws SQLException {
		// Array of quantity
		String[] qty = { "BAG-BAGS", "BOX-BOX", "PCS-PIECES", "BTL-BOTTLES", "CTN-CARTONS", "KGS-KILOGRAMS",
				"LTR-LITRE" };
		qtyType.getItems().addAll(qty);
		String[] gst = { "0.0", "0.25", "5.0", "12.0", "18.0", "28.0" };
		gstRate.getItems().addAll(gst);

		// sql part
		String sql = "SELECT * FROM items";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			data.add(new items(rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_hsn"),
					rs.getString("qty_type"), rs.getFloat("gst_rate"), rs.getFloat("cgst"), rs.getFloat("sgst"),
					rs.getFloat("cess"), rs.getFloat("stock"), rs.getFloat("cost_price"),
					rs.getFloat("Selling_price")));
		}

		table.setItems(data);
		collmItemsID.setCellValueFactory(new PropertyValueFactory<items, Integer>("itemId"));
		collmItemsID.setSortType(TableColumn.SortType.ASCENDING);
		collName.setCellValueFactory(new PropertyValueFactory<items, String>("name"));
		collHSNNumber.setCellValueFactory(new PropertyValueFactory<items, String>("hsnNo"));
		collQtyType.setCellValueFactory(new PropertyValueFactory<items, String>("qtyType"));
		collGstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("gstRate"));
		collCGST.setCellValueFactory(new PropertyValueFactory<items, Float>("cgst"));
		collSGST.setCellValueFactory(new PropertyValueFactory<items, Float>("sgst"));
		collCESS.setCellValueFactory(new PropertyValueFactory<items, Float>("cess"));
		collStock.setCellValueFactory(new PropertyValueFactory<items, Float>("stock"));
		collCostPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("costPrice"));
		collSellingPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("sellingPrice"));
		
	}

	// getting connections
	Connection conn = Connections.b2csDBConncetion();

	// button Functionality
	public void itemsAdditems(ActionEvent event) throws SQLException {

		// mItemsID = 10320;//itemsID.getText().toString();
		mItemsItemName = itemsItemName.getText().toString();
		mItemsHSNNumber = itemsHSNNumber.getText().toString();
		mQtyType = qtyType.getSelectionModel().getSelectedItem().toString();
		mGstRate = Float.parseFloat(gstRate.getSelectionModel().getSelectedItem().toString());
		// mItemsCGST
		// mItemsSGST
		mItemsCESS = Float.parseFloat(itemsCESS.getText().toString());
		// mItemsStock
		mItemsCostPrice = Float.parseFloat(itemsCostPrice.getText().toString());
		mItemsSellingPrice = Float.parseFloat(itemsSellingPrice.getText().toString());
		mItemsCGST = (mItemsCostPrice * (mGstRate) / 2) / 100;
		mItemsSGST = (mItemsCostPrice * (mGstRate) / 2) / 100;

		String sql = "INSERT INTO items (item_name,item_hsn,qty_type,gst_rate,cgst,sgst,cess,cost_price,Selling_price) VALUES (\'"
				// + mItemsID + "','"
				+ mItemsItemName + "','" + mItemsHSNNumber + "','" + mQtyType + "','" + mGstRate + "','" + mItemsCGST
				+ "','" + mItemsSGST + "','" + mItemsCESS + "','" + mItemsCostPrice + "','" + mItemsSellingPrice
				+ "\')";

		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);

		items a = new items(mItemsID, mItemsItemName, mItemsHSNNumber, mQtyType, mGstRate, mItemsCGST, mItemsSGST,
				mItemsCESS, mItemsStock, mItemsCostPrice, mItemsSellingPrice);
		data.add(a);
	}

	public void deleteItems() throws SQLException{
		//deleting from the database
		String sql;
		Statement stmt;
		ResultSet  rs;
		if(table.getSelectionModel().getSelectedItem().getItemId()==0){
			//if we are trying to delete the very resent entry then we will update data list
			 sql = "SELECT * FROM items";
			 stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			data.clear();
			while(rs.next()){
				data.add(new items(rs.getInt("item_id"),
						rs.getString("item_name"),
						rs.getString("item_hsn"),
						rs.getString("qty_type"),
						rs.getFloat("gst_rate"),
						rs.getFloat("cgst"),
						rs.getFloat("sgst"),
						rs.getFloat("cess"),
						rs.getFloat("stock"),
						rs.getFloat("cost_price"), 
						rs.getFloat("Selling_price")));
			}
		}else{
		
		int id = table.getSelectionModel().getSelectedItem().getItemId() ;
		sql = "DELETE FROM items WHERE item_id = " + id;
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		}
		//updating ilst after deleting
		
		
		 sql = "SELECT * FROM items";
		 stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		data.clear();
		while(rs.next()){
			data.add(new items(rs.getInt("item_id"),
					rs.getString("item_name"),
					rs.getString("item_hsn"),
					rs.getString("qty_type"),
					rs.getFloat("gst_rate"),
					rs.getFloat("cgst"),
					rs.getFloat("sgst"),
					rs.getFloat("cess"),
					rs.getFloat("stock"),
					rs.getFloat("cost_price"), 
					rs.getFloat("Selling_price")));
		}
		
		
		stmt.close();
			
	}
	
	public void editItems() throws SQLException, IOException{
		
		int itemId = table.getSelectionModel().getSelectedItem().getItemId() ;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editItems.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		((editItemsController) fxmlLoader.getController()).getItemId(itemId);
		stage.show();
	}

	public void refreshTableData() throws SQLException{
		// sql part	
				data.clear();
				String sql = "SELECT * FROM items";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					data.add(new items(rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_hsn"),
							rs.getString("qty_type"), rs.getFloat("gst_rate"), rs.getFloat("cgst"), rs.getFloat("sgst"),
							rs.getFloat("cess"), rs.getFloat("stock"), rs.getFloat("cost_price"),
							rs.getFloat("Selling_price")));
				}

				
				table.setItems(data);
				collmItemsID.setCellValueFactory(new PropertyValueFactory<items, Integer>("itemId"));
				collmItemsID.setSortType(TableColumn.SortType.ASCENDING);
				collName.setCellValueFactory(new PropertyValueFactory<items, String>("name"));
				collHSNNumber.setCellValueFactory(new PropertyValueFactory<items, String>("hsnNo"));
				collQtyType.setCellValueFactory(new PropertyValueFactory<items, String>("qtyType"));
				collGstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("gstRate"));
				collCGST.setCellValueFactory(new PropertyValueFactory<items, Float>("cgst"));
				collSGST.setCellValueFactory(new PropertyValueFactory<items, Float>("sgst"));
				collCESS.setCellValueFactory(new PropertyValueFactory<items, Float>("cess"));
				collStock.setCellValueFactory(new PropertyValueFactory<items, Float>("stock"));
				collCostPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("costPrice"));
				collSellingPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("sellingPrice"));
				
				stmt.close();
				rs.close();
		
	}

}

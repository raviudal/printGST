package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.controlsfx.control.textfield.TextFields;

import application.ItemsController.items;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

public class PurchaseController {
	Connection conn = Connections.b2csDBConncetion();
	// Top level textField field

	public class items {
		private IntegerProperty itemCode;
		private StringProperty itemName;
		private StringProperty hsn;
		private StringProperty quantity;
		private FloatProperty price;
		private FloatProperty gstAmount;
		private FloatProperty igstAmount;
		private FloatProperty total;

		public items(Integer itemCode, String itemName, String hsn, String quantity, Float price, Float gstAmount,
				Float igstAmount, Float total) {
			super();
			this.itemCode = new SimpleIntegerProperty(itemCode);
			this.itemName = new SimpleStringProperty(itemName);
			this.hsn = new SimpleStringProperty(hsn);
			this.quantity = new SimpleStringProperty(quantity);
			this.price = new SimpleFloatProperty(price);
			this.gstAmount = new SimpleFloatProperty(gstAmount);
			this.igstAmount = new SimpleFloatProperty(igstAmount);
			this.total = new SimpleFloatProperty(total);
		}

		public items() {
			super();

		}

		public Integer getItemCode() {
			return itemCode.get();
		}

		public void setItemCode(Integer itemCode) {
			this.itemCode.set(itemCode);
		}

		public String getItemName() {
			return itemName.get();
		}

		public void setItemName(String itemName) {
			this.itemName.set(itemName);
		}

		public String getHsn() {
			return hsn.get();
		}

		public void setHsn(String hsn) {
			this.hsn.set(hsn);
		}

		public String getQuantity() {
			return quantity.getValue();
		}

		public void setQuantity(String quantity) {
			this.quantity.set(quantity);
		}

		public Float getPrice() {
			return price.floatValue();
		}

		public void setPrice(Float price) {
			this.price.set(price);
		}

		public Float getgstAmount() {
			return gstAmount.floatValue();
		}

		public void setgstAmount(Float gstAmount) {
			this.gstAmount.set(gstAmount);
		}

		public Float getigstAmount() {
			return igstAmount.floatValue();
		}

		public void setigstAmount(Float gstAmount) {
			this.igstAmount.set(gstAmount);
		}

		public Float getTax() {
			return igstAmount.floatValue();
		}

		public void setTax(Float tax) {
			this.igstAmount.set(tax);
		}

		public Float getTotal() {
			return total.floatValue();
		}

		public void setTotal(Float total) {
			this.total.set(total);
		}

	}

	float gandTotal = 0.0f;
	@FXML
	TextField purchaseDealerName = new TextField();
	@FXML
	TextField purchaseGSTINNumber = new TextField();
	@FXML
	TextField purchaseInvoiceNumber = new TextField();
	@FXML
	DatePicker datePicker = new DatePicker();
	

	@FXML
	TextField itemCode = new TextField();
	@FXML
	TextField itemName = new TextField();
	@FXML
	TextField hsn = new TextField();
	@FXML
	TextField quantity = new TextField();
	@FXML
	ComboBox<String> qtyChoose = new ComboBox<>();
	@FXML
	TextField price = new TextField();
	@FXML
	TextField gstAmount = new TextField();
	@FXML
	TextField igstAmount = new TextField();
	@FXML
	TextField total = new TextField();

	// END of top level field
	@FXML
	Label purchaseGrandTotal = new Label();
	Button purchareButton = new Button();

	// tabel and collmun datafiled
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
	TableColumn<items, Float> collPrice = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collgstAmount = new TableColumn<>();
	@FXML
	TableColumn<items, Float> colligstAmount = new TableColumn<>();
	@FXML
	TableColumn<items, Float> collTotal = new TableColumn<>();

	ObservableList<items> tableData = FXCollections.observableArrayList();

	String sql;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	@FXML
	public void initialize() throws SQLException {

//   date format change
		
		datePicker.setConverter(new StringConverter<LocalDate>()
		{
		    private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

		    @Override
		    public String toString(LocalDate localDate)
		    {
		        if(localDate==null)
		            return "";
		        return dateTimeFormatter.format(localDate);
		    }

		    @Override
		    public LocalDate fromString(String dateString)
		    {
		        if(dateString==null || dateString.trim().isEmpty())
		        {
		            return null;
		        }
		        return LocalDate.parse(dateString,dateTimeFormatter);
		    }
		});
		
		
		
		
		// Array of quantity
		String[] qty = { "BAG", "BOX", "PCS", "BTL", "CTN", "KGS", "LTR" };
		// "BAG-BAGS", "BOX-BOX", "PCS-PIECES", "BTL-BOTTLES", "CTN-CARTONS",
		// "KGS-KILOGRAMS", "LTR-LITRE"
		qtyChoose.getItems().addAll(qty);

		// dealer details
		ObservableList<String> dealerNameData = FXCollections.observableArrayList();
		sql = "SELECT dealer_company_name FROM dealer";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dealerNameData.add(rs.getString("dealer_company_name"));
		}
		TextFields.bindAutoCompletion(purchaseDealerName, dealerNameData);
		purchaseDealerName.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (!newValue) {
					try {
						String subSQL = "SELECT dealer_company_gstn FROM dealer WHERE dealer_company_name = '"
								+ purchaseDealerName.getText().toString() + "'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(subSQL);
						rs.next();
						purchaseGSTINNumber.setText(rs.getString("dealer_company_gstn"));
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		ObservableList<String> dealerGstinData = FXCollections.observableArrayList();
		sql = "SELECT dealer_company_gstn FROM dealer";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dealerGstinData.add(rs.getString("dealer_company_gstn"));
		}
		TextFields.bindAutoCompletion(purchaseGSTINNumber, dealerGstinData);
		System.out.println(" " + dealerNameData.get(1));
		purchaseGSTINNumber.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (!newValue) {
					try {
						String subSQL = "SELECT dealer_company_name FROM dealer WHERE dealer_company_gstn = '"
								+ purchaseGSTINNumber.getText().toString() + "'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(subSQL);
						rs.next();
						purchaseDealerName.setText(rs.getString("dealer_company_name"));
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		// seting the data for item code //items detalis ///
		ObservableList<Integer> idData = FXCollections.observableArrayList();
		sql = "SELECT * FROM items";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			idData.add(rs.getInt("item_id"));
		}
		TextFields.bindAutoCompletion(itemCode, idData);
		itemCode.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldVal, Boolean newVal) {
				if (!newVal) {
					String subSQL = "SELECT * FROM items WHERE item_id = " + itemCode.getText().toString();
					try {
						rs = stmt.executeQuery(subSQL);
						rs.next();
						itemName.setText(rs.getString("item_name"));

						hsn.setText(rs.getString("item_hsn"));
						qtyChoose.setValue(
								rs.getString("qty_type")/* .substring(0, 3) */);
						price.setText(String.valueOf(rs.getFloat("cost_price")));
						
						quantity.focusedProperty().addListener(new ChangeListener<Boolean>() {

							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								// TODO Auto-generated method stub
								if (!newValue) {
									try {
										total.setText(String.valueOf(Float.parseFloat(quantity.getText())
												* Float.parseFloat(price.getText())));
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						});

						price.focusedProperty().addListener(new ChangeListener<Boolean>() {

							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								// TODO Auto-generated method stub
								if (!newValue) {
									try {
										total.setText(String.valueOf(Float.parseFloat(quantity.getText())
												* Float.parseFloat(price.getText())));
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}
						});

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		//adding tax gst and igst to total =-=listner
		gstAmount.focusedProperty().addListener(new ChangeListener<Boolean>(){

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method stub
				float totaltemp = Float.valueOf(total.getText().toString());
				float gstTemp = Float.valueOf(gstAmount.getText().toString());
				if(!arg2){
					total.setText(Float.toString(totaltemp + gstTemp));
					
				}
			}
			
		});
		igstAmount.focusedProperty().addListener(new ChangeListener<Boolean>(){

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method stub
				float totaltemp = Float.valueOf(total.getText().toString());
				float igstTemp = Float.valueOf(igstAmount.getText().toString());
				if(!arg2){
					total.setText(Float.toString(totaltemp + igstTemp));
					
				}
			}
			
		});

	}

	public void addItemsToTable(ActionEvent event) throws SQLException {
		addItemToStock();
		// here same class is used wich is used for billingController
		tableData.add(new items(Integer.parseInt(itemCode.getText().toString()), itemName.getText().toString(),
				hsn.getText().toString(),
				quantity.getText().toString() + " " + qtyChoose.getSelectionModel().getSelectedItem().toString(),
				Float.parseFloat(price.getText().toString()), // it is but it is
																// price
				Float.parseFloat(gstAmount.getText().toString()), Float.parseFloat(igstAmount.getText().toString()),
				Float.parseFloat(total.getText().toString())));
		table.setItems(tableData);
		collmItemsID.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
		collName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		collHSNNumber.setCellValueFactory(new PropertyValueFactory<>("hsn"));
		collQtyType.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		collPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		collgstAmount.setCellValueFactory(new PropertyValueFactory<>("gst"));
		colligstAmount.setCellValueFactory(new PropertyValueFactory<>("igst"));
		collTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		gandTotal = gandTotal + Float.parseFloat(total.getText().toString());
		purchaseGrandTotal.setText("Rs. " + gandTotal);

	}

	public void savePurchaseDetails() throws SQLException {
		
		String dealer_name = purchaseDealerName.getText().toString();
		String gstin = purchaseGSTINNumber.getText().toString();
		String invoice_no = purchaseInvoiceNumber.getText().toString();
		String date = datePicker.getEditor().getText();
		float amount = gandTotal;

		sql = "INSERT INTO purchase_invoice (dealer_name, gstin, invoice_no, date, amount) VALUES ('" + dealer_name
				+ "','" + gstin + "','" + invoice_no + "','" + date + "'," + amount + ")";
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);

		sql = "INSERT INTO purchase_invoice_details (invoice_no, item_name, item_code, hsn, quantity, price, gst_amount, igst_amount, total) VALUES (?,?,?,?,?,?,?,?,?)";

		pstmt = conn.prepareStatement(sql);

		for (items x : tableData) {

			String itemName = x.getItemName();
			String itemCode = String.valueOf(x.getItemCode());
			String hsn = x.getHsn();
			String quantity = x.getQuantity();
			float price = x.getTotal();
			float gstAmount = x.getgstAmount();
			float isgstAmount = x.getigstAmount();
			float total = x.getTotal();

			pstmt.setString(1, invoice_no);
			pstmt.setString(2, itemName);
			pstmt.setString(3, itemCode);
			pstmt.setString(4, hsn);
			pstmt.setString(5, quantity);
			pstmt.setFloat(6, price);
			pstmt.setFloat(7, gstAmount);
			pstmt.setFloat(8, isgstAmount);
			pstmt.setFloat(9, total);

			pstmt.executeUpdate();

		}
		purchaseDealerName.clear();
		purchaseGSTINNumber.clear();
		purchaseInvoiceNumber.clear();
		
		itemCode.clear();
		itemName.clear();
		hsn.clear();
		quantity.clear();
		price.clear();
		gstAmount.setText("0");
		igstAmount.setText("0");
		total.clear();
	}

	public void deleteItemFromTable() {
		items del = table.getSelectionModel().getSelectedItem();

		gandTotal = gandTotal - del.getTotal();
		purchaseGrandTotal.setText("Rs. " + gandTotal);
		tableData.remove(del);
	}
	
	public void addItemToStock() throws SQLException{
		String sql1 = null;
		int id = Integer.valueOf(itemCode.getText().toString());
		float qtyTmp = Float.valueOf(quantity.getText().toString());
		sql1 = "UPDATE items SET stock = " + qtyTmp +" WHERE item_id = "+ id;
		stmt = conn.createStatement();
		stmt.executeUpdate(sql1);
		
	}

}

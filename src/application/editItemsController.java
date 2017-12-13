package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

public class editItemsController {
	@FXML
	private TextField itemName;
	@FXML
	private TextField itemHSN;
	@FXML
	private TextField stock;
	@FXML
	private TextField sgstAmount;
	@FXML
	private TextField cgstAmount;
	@FXML
	private TextField costPrice;
	@FXML
	private TextField sellingPrice;
	@FXML
	private ComboBox<String> gstRate;
	@FXML
	private ComboBox<String> qtyTyp;

	int itemId = 0;
	String sql ="";
	Connection conn = Connections.b2csDBConncetion();
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	// Event Listener on Button.onAction
	@FXML
	public void updateDetails(ActionEvent event) throws SQLException {

		sql = "UPDATE items SET item_name = ?, item_hsn = ?, qty_type = ?, gst_rate = ?, cgst = ?, sgst = ?, stock = ?, cost_price = ?, selling_price = ? WHERE items.item_id = " + itemId;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, itemName.getText().toString() );
		pstmt.setString(2, itemHSN.getText().toString());
		pstmt.setString(3, qtyTyp.getSelectionModel().getSelectedItem().toString());
		pstmt.setFloat(4, Float.valueOf(gstRate.getSelectionModel().getSelectedItem().toString()));
		pstmt.setFloat(5, Float.valueOf(cgstAmount.getText().toString()));
		pstmt.setFloat(6, Float.valueOf(sgstAmount.getText().toString()));
		pstmt.setFloat(7, Float.valueOf(stock.getText().toString()));
		pstmt.setFloat(8, Float.valueOf(costPrice.getText().toString()));
		pstmt.setFloat(9, Float.valueOf(sellingPrice.getText().toString()));
		
		pstmt.executeUpdate();
		
	
		
	}
	
	public void getItemId(int intID) throws SQLException{
		itemId = intID;
		String[] qty = { "BAG-BAGS", "BOX-BOX", "PCS-PIECES", "BTL-BOTTLES", "CTN-CARTONS", "KGS-KILOGRAMS",
		"LTR-LITRE" };
		String[] gst = { "0.0", "0.25", "5.0", "12.0", "18.0", "28.0" };
		gstRate.getItems().setAll(gst);
		qtyTyp.getItems().setAll(qty);
		sql = "SELECT * FROM items WHERE item_id = " + intID + " LIMIT 1";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();
		itemName.setText(rs.getString("item_name"));
		itemHSN.setText(rs.getString("item_hsn"));
		stock.setText(String.valueOf(rs.getFloat("stock")));
		sgstAmount.setText(String.valueOf(rs.getFloat("sgst")));
		cgstAmount.setText(String.valueOf(rs.getFloat("cgst")));
		costPrice.setText(String.valueOf(rs.getFloat("cost_price")));
		sellingPrice.setText(String.valueOf(rs.getFloat("selling_price")));
		gstRate.getSelectionModel().select(rs.getString("gst_rate"));
		qtyTyp.getSelectionModel().select(rs.getString("qty_type"));
		
		
	}
}

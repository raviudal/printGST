package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class editCustomerDialogeController {
	@FXML
	private AnchorPane gstinNumber;
	@FXML
	private TextField name;
	@FXML
	private TextField mobile;
	@FXML
	private TextField gstin;
	@FXML
	private TextField address;
	@FXML
	private TextField dueBalance;
	@FXML
	private TextField advanceBalance;

	int custId;
	String sql ="";
	Connection conn = Connections.b2csDBConncetion();
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	// Event Listener on Button.onAction
	@FXML
	public void updateDetails(ActionEvent event) throws SQLException {
		sql = "UPDATE customer SET customerCustomerName = ?, customerMobileNumber = ?, customerGstinNumber = ?, customerAdderss = ?, advanceBalance = ?, dueBalance = ? WHERE id = " + custId;
		System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name.getText().toString() );
		pstmt.setString(2, mobile.getText().toString());
		pstmt.setString(3, gstin.getText().toString());
		pstmt.setString(4, address.getText().toString());
		pstmt.setString(5, advanceBalance.getText().toString());
		pstmt.setString(6, dueBalance.getText().toString());
		
		pstmt.executeUpdate();
		
		//closing
		
		Stage stage = (Stage)  gstinNumber.getScene().getWindow();
		stage.close();
	}
	
	public void getDataFrom(int id) throws SQLException{
		custId =id;
		sql = "SELECT * FROM customer WHERE id = " + id + " LIMIT 1";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();
		name.setText(rs.getString("customerCustomerName"));
		mobile.setText(rs.getString("customerMobileNumber"));
		gstin.setText(rs.getString("customerGstinNumber"));
		address.setText( rs.getString("customerAdderss") );
		dueBalance.setText( rs.getString("dueBalance") );
		advanceBalance.setText( rs.getString("advanceBalance") );
		
		
	}
	
	
}

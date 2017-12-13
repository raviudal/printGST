package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

public class editDealerdialogeController {
	@FXML
	private TextField name;
	@FXML
	private TextField gstin;
	@FXML
	private TextField contact;
	@FXML
	private TextField address;

	
	int custId;
	String sql ="";
	Connection conn = Connections.b2csDBConncetion();
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	// Event Listener on Button.onAction
	@FXML
	public void updateDetails(ActionEvent event) throws SQLException {
		sql = "UPDATE dealer SET dealer_company_name = ?, dealer_company_addr = ?, dealer_company_gstn = ?, dealer_company_contact = ? WHERE id = " + custId;
		System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name.getText().toString() );
		pstmt.setString(2, address.getText().toString());
		pstmt.setString(3, gstin.getText().toString());
		pstmt.setString(4, contact.getText().toString());
				
		pstmt.executeUpdate();
		
		//closing
		
		Stage stage = (Stage)  name.getScene().getWindow();
		stage.close();
	}
	
	public void getDataFrom(int id) throws SQLException{
		custId =id;
		sql = "SELECT * FROM dealer WHERE id = " + id + " LIMIT 1";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();
		name.setText(rs.getString("dealer_company_name"));
		contact.setText(rs.getString("dealer_company_addr"));
		gstin.setText(rs.getString("dealer_company_gstn"));
		address.setText( rs.getString("dealer_company_contact") );
		
	}
}

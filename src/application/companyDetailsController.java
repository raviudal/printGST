package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.CustomerController.Customer;
import javafx.event.ActionEvent;

public class companyDetailsController {
	@FXML
	private TextField companyName;
	@FXML
	private TextField companyAddress;
	@FXML
	private TextField companyGstin;
	@FXML
	private TextField companyContact;
	@FXML
	private Label hidden;
	
	Connection conn = Connections.b2csDBConncetion();
	
	String sql;
	Statement stmt;
	ResultSet rs ;

	
	@FXML
	public void initialize() throws SQLException{
		sql = "SELECT * FROM company_details";
		stmt = conn.createStatement();
		stmt.execute(sql);
		rs = stmt.executeQuery(sql);
		rs.next();
		companyName.setText(rs.getString("name"));
		companyAddress.setText(rs.getString("address"));
		companyGstin.setText(rs.getString("gstin"));
		companyContact.setText(rs.getString("contact"));
		
		
	}

	
	
	@FXML
	public void addUpdateCompanyDetails(ActionEvent event) throws SQLException {
		String name = companyName.getText();
		String address = companyAddress.getText();
		String gdtin = companyGstin.getText();
		String contact = companyContact.getText();
		
		String sql1 = "UPDATE company_details SET name = '" + name + "' , address = '" + address +"', `gstin` = '" + gdtin +"', `contact` = '"+ contact+"' WHERE company_details.id = 1";
		stmt = conn.createStatement();
		stmt.executeUpdate(sql1);
		hidden.setVisible(true);
	}
}

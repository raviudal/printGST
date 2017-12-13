package application;

import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.BillingController.items;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class printBillController {
	@FXML
	private AnchorPane printPane;
	@FXML
	private Text companyAddress;
	@FXML
	private Label companyName;
	@FXML
	private Label companyContact;
	@FXML
	private Label companyGstin;
	@FXML
	private Label grandTotal;
	@FXML
	private Label totaltax;
	@FXML
	private Label invoiceNumber;
	@FXML
	private Label invoiceDate;
	@FXML
	private Label custName;
	@FXML
	private Label custNumber;
	@FXML
	private Label custGstin;
	@FXML
	private Label totalIgst;
	@FXML
	private Text billinAddr;
	
	@FXML
	private Label totalSgst;
	@FXML
	private Label totalCgst;
	
	
	@FXML
	TableView<items> table = new TableView<>();
	@FXML
	TableColumn<BillingController.items, Integer> collItemId = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, String> collItemName = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, String> collItemhsn = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, String> collItemQty = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collItemGstRate = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collItemPrice = new TableColumn<>();
	
	@FXML
	TableColumn<BillingController.items, Float> collSgstRate = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collSgstAmount = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collCgstRate = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collCgstAmount = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collItemIgst = new TableColumn<>();
	
	@FXML
	TableColumn<BillingController.items, Float> collItemTax = new TableColumn<>();
	@FXML
	TableColumn<BillingController.items, Float> collItemTotal = new TableColumn<>();
	
	ObservableList<BillingController.items> tableData = FXCollections.observableArrayList();
	
	Connection conn = Connections.b2csDBConncetion();
	String sql;
	Statement stmt;
	ResultSet rs;
	
	
	// Event Listener on Button.onAction
	@FXML
	public void a(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		printNode(printPane);
		//System.exit(0);
	}
	public static void printNode(final AnchorPane node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
	  
		Printer printer = Printer.getDefaultPrinter();
	    PageLayout pageLayout
	        = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
	    PrinterJob job = PrinterJob.createPrinterJob();

	     node.setPrefSize(pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight());
	    if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
	      boolean success = job.printPage(pageLayout, node);
	      if (success) {
	        job.endJob();
	      }
	    }
	  }
	

	public void setDataFromBillingController(ObservableList<BillingController.items> list, String tax, String total, String invoiceNo, String invoiceDate, String custName, String custNumber, float sgst, float cgst, float isgst, String custGstin, String biilingAddr  ) throws SQLException{
		//first we wll set the company name and data for the header
		sql = "SELECT * FROM company_details";
		stmt = conn.createStatement();
		stmt.execute(sql);
		rs = stmt.executeQuery(sql);
		rs.next();
		companyName.setText(rs.getString("name"));
		companyAddress.setText(rs.getString("address"));
		companyGstin.setText(rs.getString("gstin"));
		companyContact.setText(rs.getString("contact"));
		this.invoiceDate.setText(invoiceDate);
		this.custName.setText(custName);
		this.custNumber.setText(custNumber);
		this.custGstin.setText(custGstin);
		this.billinAddr.setText(biilingAddr);
		totalSgst.setText(String.valueOf(sgst));
		totalCgst.setText(String.valueOf(cgst));
		totalIgst.setText(String.valueOf(isgst));
		
		tableData = list;
		//////////////////////////////////
		grandTotal.setText(total);
		
		//totaltax.setText(tax);
		this.invoiceNumber.setText(invoiceNo);
		/// aadding the data to the table
		table.setItems(tableData);
		collItemId.setCellValueFactory(new PropertyValueFactory<items, Integer>("itemCode"));
		collItemName.setCellValueFactory(new PropertyValueFactory<items, String>("itemName"));
		collItemhsn.setCellValueFactory(new PropertyValueFactory<items, String>("hsn"));
		collItemQty.setCellValueFactory(new PropertyValueFactory<items, String>("quantity"));
		collItemIgst.setCellValueFactory(new PropertyValueFactory<items, Float>("igstAmount"));
		collSgstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("sgstRate"));
		collSgstAmount.setCellValueFactory(new PropertyValueFactory<items, Float>("sgstAmount"));
		collCgstRate.setCellValueFactory(new PropertyValueFactory<items, Float>("cgstRate"));
		collCgstAmount.setCellValueFactory(new PropertyValueFactory<items, Float>("cgstAmount"));
		collItemPrice.setCellValueFactory(new PropertyValueFactory<items, Float>("price"));
		collItemTotal.setCellValueFactory(new PropertyValueFactory<items, Float>("total"));
		
	}
	
}

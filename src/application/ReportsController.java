package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.BillingController.items;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReportsController {
	public class purchaseClass {
		private StringProperty dealerName;
		private StringProperty gstin;
		private StringProperty invoice;
		private StringProperty date;
		private StringProperty amount;

		public purchaseClass(String dealerName, String gstin, String invoice, String date, String amount) {
			super();
			this.dealerName = new SimpleStringProperty(dealerName);
			this.gstin = new SimpleStringProperty(gstin);
			this.invoice = new SimpleStringProperty(invoice);
			this.date = new SimpleStringProperty(date);
			this.amount = new SimpleStringProperty(amount);
		}

		public String getDealerName() {
			return dealerName.get();
		}

		public void setDealerName(String dealerName) {
			this.dealerName.set(dealerName);
		}

		public String getGstin() {
			return gstin.get();
		}

		public void setGstin(String gstin) {
			this.gstin.set(gstin);
		}

		public String getInvoice() {
			return invoice.get();
		}

		public void setInvoice(String invoice) {
			this.invoice.set(invoice);
		}

		public String getDate() {
			return date.get();
		}

		public void setDate(String date) {
			this.date.set(date);
		}

		public String getAmount() {
			return amount.get();
		}

		public void setAmount(String amount) {
			this.amount.set(amount);
		}

	}

	public class sellingClass {
		// here purchaseVariableName Shoud be there but sellingVariableName is
		// there but this cannot be change so will be using this only
		private StringProperty sellingInvoiceNumber;
		private StringProperty sellingInvoiceDate;
		private StringProperty sellingCustomerName;
		private StringProperty sellingCustomerContact;
		private StringProperty sellingTaxableAmount;
		private StringProperty sellingAmount;

		public sellingClass(String sellingInvoiceNumber, String sellingInvoiceDate, String sellingCustomerName,
				String sellingCustomerContact, String sellingTaxableAmount, String sellingAmount) {
			super();
			this.sellingInvoiceNumber = new SimpleStringProperty(sellingInvoiceNumber);
			this.sellingInvoiceDate = new SimpleStringProperty(sellingInvoiceDate);
			this.sellingCustomerName = new SimpleStringProperty(sellingCustomerName);
			this.sellingCustomerContact = new SimpleStringProperty(sellingCustomerContact);
			this.sellingTaxableAmount = new SimpleStringProperty(sellingTaxableAmount);
			this.sellingAmount = new SimpleStringProperty(sellingAmount);
		}

		public String getSellingInvoiceNumber() {
			return sellingInvoiceNumber.get();
		}

		public void setSellingInvoiceNumber(String sellingInvoiceNumber) {
			this.sellingInvoiceNumber.set(sellingInvoiceNumber);
		}

		public String getSellingInvoiceDate() {
			return sellingInvoiceDate.get();
		}

		public void setSellingInvoiceDate(String sellingInvoiceDate) {
			this.sellingInvoiceDate.set(sellingInvoiceDate);
		}

		public String getSellingCustomerName() {
			return sellingCustomerName.get();
		}

		public void setSellingCustomerName(String sellingCustomerName) {
			this.sellingCustomerName.set(sellingCustomerName);
		}

		public String getSellingCustomerContact() {
			return sellingCustomerContact.get();
		}

		public void setSellingCustomerContact(String sellingCustomerContact) {
			this.sellingCustomerContact.set(sellingCustomerContact);
		}

		public String getSellingTaxableAmount() {
			return sellingTaxableAmount.get();
		}

		public void setSellingTaxableAmount(String sellingTaxableAmount) {
			this.sellingTaxableAmount.set(sellingTaxableAmount);
		}

		public String getSellingAmount() {
			return sellingAmount.get();
		}

		public void setSellingAmount(String sellingAmount) {
			this.sellingAmount.set(sellingAmount);
		}

	}

	@FXML
	private TextField invoiceSearchBox = new TextField();

	@FXML
	private DatePicker fromDate = new DatePicker();
	@FXML
	private DatePicker toDate = new DatePicker();

	@FXML
	private AnchorPane anchorPane1;
	@FXML
	private TableView<purchaseClass> purchaseTable = new TableView<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseDealerName = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseGstin = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseInvoice = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseDate = new TableColumn<>();
	@FXML
	private TableColumn<purchaseClass, String> purchaseAmount = new TableColumn<>();


	@FXML
	private TableView<sellingClass> sellingTable = new TableView<>();
	@FXML
	private TableColumn<sellingClass, String> sellingInvoiceNumber = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingInvoiceDate = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingCustomerName = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingCustomerContact = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingTaxableAmount = new TableColumn<>();
	@FXML
	private TableColumn<sellingClass, String> sellingAmount = new TableColumn<>();
	
	@FXML
	private Label datanotfound = new Label();
	private Label datanotfounddate = new Label();

	ObservableList<purchaseClass> purchaseData = FXCollections.observableArrayList();
	ObservableList<sellingClass> sellingData = FXCollections.observableArrayList();

	Connection conn = Connections.b2csDBConncetion();
	String sql;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	@FXML
	public void initialize() throws SQLException {
		// ========================== for
		// purchase=============================================================
		sql = "SELECT * FROM purchase_invoice";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			purchaseData.add(new purchaseClass(rs.getString("dealer_name"), rs.getString("gstin"),
					rs.getString("invoice_no"), rs.getString("date"), String.valueOf(rs.getFloat("amount"))));
		}
		// setting data to the colomn for purchse data;
		purchaseTable.setItems(purchaseData);

		purchaseDealerName.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
		purchaseGstin.setCellValueFactory(new PropertyValueFactory<>("gstin"));
		purchaseInvoice.setCellValueFactory(new PropertyValueFactory<>("invoice"));
		purchaseDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		purchaseAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

		// ============================== for selling
		// =====================================================
		sql = "SELECT * FROM selling_invoice";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			sellingData.add(new sellingClass(String.valueOf(rs.getInt("invoice_number")), rs.getString("invoice_date"),
					rs.getString("customer_name"), rs.getString("cutomer_number"), rs.getString("taxable_value"),
					String.valueOf(rs.getFloat("amount"))));
		}

		// setting data to the colomn for selling data;
		sellingTable.setItems(sellingData);

		sellingInvoiceNumber.setCellValueFactory(new PropertyValueFactory<>("sellingInvoiceNumber"));
		sellingInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("sellingInvoiceDate"));
		sellingCustomerName.setCellValueFactory(new PropertyValueFactory<>("sellingCustomerName"));
		sellingCustomerContact.setCellValueFactory(new PropertyValueFactory<>("sellingCustomerContact"));
		sellingTaxableAmount.setCellValueFactory(new PropertyValueFactory<>("sellingTaxableAmount"));
		sellingAmount.setCellValueFactory(new PropertyValueFactory<>("sellingAmount"));

		//=======for setting date format consistent with mysql database which is yyyy-mm-dd
		fromDate.setConverter(new StringConverter<LocalDate>()
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
		toDate.setConverter(new StringConverter<LocalDate>()
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
		


	}

	public void showDetailedInfoselling(ActionEvent Event) throws SQLException {

		String invoiceNo = sellingTable.getSelectionModel().getSelectedItem().getSellingInvoiceNumber();
		int invoicnumbner = Integer.valueOf(invoiceNo);

		// for getting information about the invoice
		sql = "SELECT * FROM selling_invoice WHERE invoice_number = " + invoiceNo;
		System.out.println(sql);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();

		String custName = rs.getString("customer_name");
		String custNumber = rs.getString("cutomer_number");
		String custGstin = rs.getString("customer_gstin");
		String custadde = rs.getString("billing_addr");
		float cgst = rs.getFloat("total_cgst")  ;
		float sgst = rs.getFloat("total_sgst") ;
		float isgst = rs.getFloat("total_igst");
		float totalBillableAmount = rs.getFloat("amount");
		float totalTax = rs.getFloat("taxable_value");
		// for getting date in correct format
		sql = "SELECT DATE_FORMAT(invoice_date, '%d/%m/%Y') FROM selling_invoice WHERE invoice_number = " + invoiceNo;
		System.out.println(sql);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();
		String date = rs.getString(1);
		// for getting the invoice details
		sql = "SELECT * FROM selling_invoice_detail WHERE invoice_number = " + invoiceNo;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		ObservableList<BillingController.items> tableData = FXCollections.observableArrayList();

		BillingController x = new BillingController();

		while (rs.next()) {
			tableData.add(x.new items(0, rs.getString("item_name"), " ", rs.getString("quantity"), rs.getFloat("price"),
					rs.getFloat("cgst_rate"), rs.getFloat("cgst_amount"), rs.getFloat("sgst_rate"),
					rs.getFloat("sgst_amount"), rs.getFloat("igst"), // isgst
					rs.getFloat("total")));
		}
		// System.out.println(tableData.get(1).getItemName());
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("print.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			((printBillController) fxmlLoader.getController()).setDataFromBillingController(tableData,
					String.valueOf(totalTax), String.valueOf(totalBillableAmount), String.valueOf(invoicnumbner), date,
					custName, custNumber, cgst, sgst, isgst, custGstin, custadde);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showDetailedInfoPurchase(ActionEvent Event) {
		/*
		 * try { FXMLLoader fxmlLoader = new
		 * FXMLLoader(getClass().getResource("print.fxml")); Parent root1 =
		 * (Parent) fxmlLoader.load(); Stage stage = new Stage();
		 * stage.setScene(new Scene(root1)); ((printBillController)
		 * fxmlLoader.getController()).setDataFromBillingController(tableData,
		 * String.valueOf(totalTax), String.valueOf(totalBillableAmount),
		 * String.valueOf(invoiceNo)); stage.show(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}

	public void searchFromInvoiceNumber() throws SQLException {
		int invoiceNu = Integer.parseInt(invoiceSearchBox.getText().toString());
		sql = "SELECT * FROM selling_invoice WHERE invoice_number = " + invoiceNu;
		System.out.println(sql);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		
		sellingData.clear();
		while (rs.next()) {
			sellingData.add(new sellingClass(String.valueOf(rs.getInt("invoice_number")), rs.getString("invoice_date"),
					rs.getString("customer_name"), rs.getString("cutomer_number"), rs.getString("taxable_value"),
					String.valueOf(rs.getFloat("amount"))));
		}
		
		if(sellingData.isEmpty()){
			datanotfound.setVisible(false);
		}
		
	}

	public void btwDate() throws SQLException {
		

		
		String frmDate = fromDate.getEditor().getText();
		String tDate = toDate.getEditor().getText();
		System.out.println(frmDate +" " + tDate);
		sql = "SELECT * FROM selling_invoice WHERE invoice_date <= ? AND invoice_date >= ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(2, frmDate);
		pstmt.setString(1, tDate);
		System.out.println(pstmt.toString());
		rs = pstmt.executeQuery();
		
		sellingData.clear();
		while (rs.next()) {
			sellingData.add(new sellingClass(String.valueOf(rs.getInt("invoice_number")), rs.getString("invoice_date"),
					rs.getString("customer_name"), rs.getString("cutomer_number"), rs.getString("taxable_value"),
					String.valueOf(rs.getFloat("amount"))));
		}
		
		if(sellingData.isEmpty()){
			datanotfounddate.setVisible(false);
		}
	}

}

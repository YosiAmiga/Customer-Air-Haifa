package entity;

public class Supplier {
	
	private int supplierID;
	private String supplierName;
	private String supplierEmail;
	/**
	 * @param supplierID
	 * @param supplierName
	 * @param supplierEmail
	 */
	public Supplier(int supplierID, String supplierName, String supplierEmail) {
		super();
		this.supplierID = supplierID;
		this.supplierName = supplierName;
		this.supplierEmail = supplierEmail;
	}
	public int getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierEmail() {
		return supplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}
	@Override
	public String toString() {
		return "Supplier [supplierID=" + supplierID + ", supplierName=" + supplierName + ", supplierEmail="
				+ supplierEmail + "]";
	}
	
	

}

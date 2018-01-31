public class ships_call {
	private int id;
	private String Ship;
	private String Food;
	private String Barcode;
	private String ExpDate;
	private String Country;
	private String Company;
	private String Quantity;
	
	
	public ships_call(int id,String Ship, String Food,String Barcode,String ExpDate,String Country,String Company,String Quantity){
		this.id = id;
		this.Ship = Ship;
		this.Food = Food;
		this.Barcode = Barcode;
		this.ExpDate = ExpDate;
		this.Country = Country;
		this.Company = Company;
		this.Quantity= Quantity;

	}
	
	public int getId(){
		return id;
		
	}
	
	public String getShip(){
		
		return Ship;
	}
	
	public String getFood(){
		
		return Food;
	}
	
	public String getBarcode(){
		
		return Barcode;
	}
	
	public String  getExpDate(){
		
		return ExpDate;
	}
	
	public String getCountry(){
		
		return Country;
	}
	
	public String getCompany(){
		
		return Company;
	}
	
	public String getQuantity(){
		
		return Quantity;
	}
	

	
}

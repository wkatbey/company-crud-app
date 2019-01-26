package wayne.csc4710.exercise4;

public class Company {
	
	protected int id;
	protected String name;
	protected double networth;
	protected String ceo;
	
	
	public Company() {
		initializeCompany();
	}
	
	public Company(int id, String name, double networth, 
			String ceo) {
		initializeCompany(id, name, networth, ceo);
	}
	
	public void initializeCompany() {
		id = 0;
		name = "";
		networth = 0;
		ceo = "";
	}
	
	public void initializeCompany(int id, String name, 
			double networth, String ceo) {
		this.id = id;
		this.name = name;
		this.networth = networth;
		this.ceo = ceo;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getNetworth() {
		return networth;
	}
	
	public String getCeo() {
		return ceo;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNetworth(double networth) {
		this.networth = networth;
	}
	
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
}

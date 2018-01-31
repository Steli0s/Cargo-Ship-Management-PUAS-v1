

public class adduser_call {
	private int id;
	private String name;
	private String usename;
	private String password;
	private String dipart;
	public adduser_call(int id, String name, String usename, String password, String dipart) {
		super();
		this.id = id;
		this.name = name;
		this.usename = usename;
		this.password = password;
		this.dipart = dipart;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsename() {
		return usename;
	}
	public void setUsename(String usename) {
		this.usename = usename;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDipart() {
		return dipart;
	}
	public void setDipart(String dipart) {
		this.dipart = dipart;
	}
}

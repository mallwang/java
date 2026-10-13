package funktionen;

public class Status {

	private String status;
	private String uhrzeit;
	
	public Status (String status, String uhrzeit){
		this.status = status;
		this.uhrzeit = uhrzeit;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUhrzeit() {
		return uhrzeit;
	}
	
	public void setUhrzeit(String uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
	
}

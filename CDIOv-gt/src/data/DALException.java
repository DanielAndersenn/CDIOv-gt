package data;

public class DALException extends Exception{

	private static final long serialVersionUID = 1L;
	private String meddelelse;
	
	public DALException(String meddelelse) {
		
		this.meddelelse = meddelelse;
	}
	
	public String getMeddelelse() {
		
		return meddelelse;
	}

}

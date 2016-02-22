package data;

import java.util.ArrayList;	

public class OperatoerDTO implements IOperatoerDAO{
	
	private static int oprIdCounter = 10;

	int oprId;
	String oprNavn;
	String cpr;
	String password;
	boolean isAdmin;
	
	private ArrayList<OperatoerDTO> operatoerList = new ArrayList<OperatoerDTO>();
	
	public OperatoerDTO() {
		
	}
	
	public OperatoerDTO(String oprNavn, String cpr, String password, boolean isAdmin) {
		this.oprId = oprIdCounter;
		this.oprNavn = oprNavn;
		this.cpr = cpr;
		this.password = password;
		this.isAdmin = isAdmin;
		oprIdCounter++;
	}

	@Override
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		
		OperatoerDTO toReturn = null;
		
		for(int i = 0; i < operatoerList.size(); i++)
		{
			if(operatoerList.get(i).oprId == oprId) toReturn = operatoerList.get(i);
		}
		
		return toReturn;
	}

	@Override
	public ArrayList<OperatoerDTO> getOperatoerList() throws DALException {
		// TODO Auto-generated method stub
		return operatoerList;
	}

	@Override
	public void createOperatoer(OperatoerDTO opr) throws DALException {
		operatoerList.add(opr);
	}

	@Override
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOperatoer(OperatoerDTO opr) throws DALException {
		operatoerList.remove(opr);
		
	}
	
	public String toString() {
		return "Operatør ID: " + oprId + " | Navn: " + oprNavn + " | CPR-Nummer: " + cpr + " | Adminstrator: " + isAdmin;
	}
	
}
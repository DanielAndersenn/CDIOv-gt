package data;

import java.util.ArrayList;
import java.util.Scanner;

import data.Graenseflade.State;	

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
		String newPassword;
		String newPassword2;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Type in your new password:");
		newPassword = input.nextLine();
		// Validate password
		System.out.println("Type the new password again to confirm the change:");
		newPassword2 = input.nextLine();
		if (newPassword.equals(newPassword2)){
			opr.password = newPassword;			
			System.out.println("Password has been changed. Returning to rootmenu!");
			System.out.println(opr + " | Med følgende password: " + opr.password);
			input.close();
		} else {
			System.out.println("Password is not identical. Try again.");
			input.close();
			updateOperatoer(opr);
		}
	}

	@Override
	public void deleteOperatoer(OperatoerDTO opr) throws DALException {
		if (!operatoerList.remove(opr)) {
			throw new DALException("Operator does not exist");
		} else {
			System.out.println("Deletion of user was successful.");
		}
	}
	
	public String toString() {
		return "Operatør ID: " + oprId + " | Navn: " + oprNavn + " | CPR-Nummer: " + cpr + " | Adminstrator: " + isAdmin;
	}
	
}
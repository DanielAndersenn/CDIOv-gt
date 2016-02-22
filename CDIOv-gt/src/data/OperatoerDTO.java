package data;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public boolean validPassword(OperatoerDTO opr){
		boolean status = true;
		int tq = 0;
		
		//Check if names is part of password
		String[] splited = opr.oprNavn.split("\\s+");
		if(opr.password.toLowerCase().contains(splited[0].toLowerCase())) status = false;
		if(opr.password.toLowerCase().contains(splited[1].toLowerCase())) status = false;
		
		//Check password length
		if(opr.password.length()<7) status = false;
		
		//Check for User ID
		if(opr.password.contains(Integer.toString(opr.oprId))) status = false;
		
		//The 4 categories
		boolean[] req = new boolean[4];
		Arrays.fill(req, Boolean.FALSE);

			//Check for small letter
			for(int i = 0; i < opr.password.length() ; i++) {
				char test = opr.password.charAt(i);
				if(Character.isLowerCase(test))req[0] = true;
			}
			
			//Check for capital letter
			for(int i = 0; i < opr.password.length() ; i++) {
				char test = opr.password.charAt(i);
				if(Character.isUpperCase(test))req[1] = true;
			}
			
			//Check for Number
			for(int i = 0; i < opr.password.length() ; i++) {
				char test = opr.password.charAt(i);
				int number = Character.getNumericValue(test);
				if(number >= 0 && number < 10)req[2] = true;
			}
			
			//Check for Nonalphanumeric characters
			String tegn[] = {".","-","_","+","!","?","="};
			for(int i=0; i < tegn.length ; i++ ){
				if(opr.password.contains(tegn[i]))req[3] = true;
			}
		
		for(int i=0;i<4;i++){
			if(req[i])tq++;
		}
		if(tq<3)status = false;
		
		return status;
	}
	
}
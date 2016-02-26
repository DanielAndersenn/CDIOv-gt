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
		
		int i = 0;
		
		try {
			while (true) {
				if (personer.get(i++).cpr.equals(cpr))
					return personer.get(i).hoejde;
			}
		}
		catch (IndexOutOfBoundsException e){
			throw new DALException("CPR findes ikke");
		}
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
		
		System.out.println("Type the new password again to confirm the change:");
		newPassword2 = input.nextLine();
		
		if (newPassword.equals(newPassword2)){
			validPassword(opr, newPassword);
			opr.password = newPassword;			
			System.out.println("Password has been changed. Returning to rootmenu!");
			System.out.println(opr + " | Med f�lgende password: " + opr.password);
			input.close();
		}
		else {
			System.out.println("Password is not identical. Try again.");
			input.close();
			updateOperatoer(opr);
		}
	}

	@Override
	public void deleteOperatoer(OperatoerDTO opr) throws DALException {
		
		try {
			operatoerList.remove(opr)
			System.out.println("Deletion of user was succesful");
		}
		catch (NullPointerException e){
			throw new DALException("Operatoer does not exist");
		}
	}
	
	public String toString() {
		return "Operat�r ID: " + oprId + " | Navn: " + oprNavn + " | CPR-Nummer: " + cpr + " | Adminstrator: " + isAdmin;
	}
	
	public boolean validPassword(OperatoerDTO opr, String password) throws DALException {
		boolean status = true;
		int tq = 0;
		
		//Check if names is part of password
		String[] splited = opr.oprNavn.split("\\s+");
		if(password.toLowerCase().contains(splited[0].toLowerCase())) throw new DALException("Your password contains your name");
		if(password.toLowerCase().contains(splited[1].toLowerCase())) throw new DALException("Your password contains your name");
		
		//Check password length
		if(password.length()<7) throw new DALException("Your password is too short");
		
		//Check for User ID
		if(password.contains(Integer.toString(opr.oprId))) throw new DALException("Your password contains your ID");
		
		//The 4 categories
		boolean[] req = new boolean[4];
		Arrays.fill(req, Boolean.FALSE);

			//Check for small letter
			for(int i = 0; i < password.length() ; i++) {
				char test = password.charAt(i);
				if(Character.isLowerCase(test))req[0] = true;
			}
			
			//Check for capital letter
			for(int i = 0; i < password.length() ; i++) {
				char test = password.charAt(i);
				if(Character.isUpperCase(test))req[1] = true;
			}
			
			//Check for Number
			for(int i = 0; i < password.length() ; i++) {
				char test = password.charAt(i);
				int number = Character.getNumericValue(test);
				if(number >= 0 && number < 10)req[2] = true;
			}
			
			//Check for Nonalphanumeric characters
			String tegn[] = {".","-","_","+","!","?","="};
			for(int i=0; i < tegn.length ; i++ ){
				if(password.contains(tegn[i]))req[3] = true;
			}
		
		for(int i=0;i<4;i++){
			if(req[i])tq++;
		}
		if(tq<3)throw new DALException("Your password does not forfill 3 of the 4 the requirements");
		
		return status;
	}
	
}
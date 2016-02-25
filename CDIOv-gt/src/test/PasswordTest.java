package test;

import data.DALException;
import data.OperatoerDTO;

public class PasswordTest {

	static OperatoerDTO test = new OperatoerDTO();
	
	public PasswordTest() {
	}

	public static void main(String[] args) {
		
		//Name in Pass
		OperatoerDTO opr0 = new OperatoerDTO("Test Testersen", "000000-0000", "Test123-", true); //Password: Fejl
		
		//Pass length
		OperatoerDTO opr1 = new OperatoerDTO("Test Testersen", "000000-0000", "Ab1-", true); //Password: Fejl
		
		//ID in Pass
		OperatoerDTO opr2 = new OperatoerDTO("Test Testersen", "000000-0000", "Hejven123-", true); //Password: Fejl
		
		//No small letters
		OperatoerDTO opr3 = new OperatoerDTO("Test Testersen", "000000-0000", "ABC-123", true); //Password: OK
		
		//No capital letters
		OperatoerDTO opr4 = new OperatoerDTO("Test Testersen", "000000-0000", "abc-123", true); //Password: OK
		
		//No numbers
		OperatoerDTO opr5 = new OperatoerDTO("Test Testersen", "000000-0000", "Abcasdasd-", true); //Password: OK
		
		//No nonalphanumerical
		OperatoerDTO opr6 = new OperatoerDTO("Test Testersen", "000000-0000", "Abcdasdasd123", true); //Password: OK
		
		try{
			test.validPassword(opr0);
		} catch(DALException e) {
			System.out.println(e.getMessage());
		}
		try{
			test.validPassword(opr1);
		} catch(DALException e) {
			System.out.println(e.getMessage());
		}
		try{
			test.validPassword(opr2);
		} catch(DALException e) {
			System.out.println(e.getMessage());
		}
		try{
			test.validPassword(opr3);
		} catch(DALException e) {
			System.out.println(e.getMessage());
		}
		try{
			test.validPassword(opr4);
		} catch(DALException e) {
			System.out.println(e.getMessage());
		}
		try{
			test.validPassword(opr5);
		} catch(DALException e) {
			System.out.println(e.getMessage());
		}
		try{
			test.validPassword(opr6);
		} catch(DALException e) {
			System.out.println(e.getMessage());
		}
	}

}

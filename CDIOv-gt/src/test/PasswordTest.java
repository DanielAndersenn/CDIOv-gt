package test;

import data.OperatoerDTO;

public class PasswordTest {

	static OperatoerDTO test = new OperatoerDTO();
	
	public PasswordTest() {
	}

	public static void main(String[] args) {
		
		//Name in Pass
		OperatoerDTO opr0 = new OperatoerDTO("Test Testersen", "000000-0000", "Test123-", true); //Password: Fejl
		
		//Pass length
		OperatoerDTO opr1 = new OperatoerDTO("Test Testersen", "000000-0000", "Ab1-", true); //Password: OK
		
		//ID in Pass
		OperatoerDTO opr2 = new OperatoerDTO("Test Testersen", "000000-0000", "Hejven123-", true); //Password: OK
		
		//No small letters
		OperatoerDTO opr3 = new OperatoerDTO("Test Testersen", "000000-0000", "ABC-123", true); //Password: Fejl
		
		//No capital letters
		OperatoerDTO opr4 = new OperatoerDTO("Test Testersen", "000000-0000", "abc-123", true); //Password: Fejl
		
		//No numbers
		OperatoerDTO opr5 = new OperatoerDTO("Test Testersen", "000000-0000", "Abcasdasd-", true); //Password: Fejl
		
		//No nonalphanumerical
		OperatoerDTO opr6 = new OperatoerDTO("Test Testersen", "000000-0000", "Abcdasdasd123", true); //Password: Fejl
		
		
		if(!test.validPassword(opr0))System.out.println("Test 0");
		if(!test.validPassword(opr1))System.out.println("Test 1");
		if(!test.validPassword(opr2))System.out.println("Test 2");
		if(test.validPassword(opr3)) System.out.println("Test 3");
		if(test.validPassword(opr4)) System.out.println("Test 4");
		if(test.validPassword(opr5)) System.out.println("Test 5");
		if(test.validPassword(opr6)) System.out.println("Test 6");
		
	}

}

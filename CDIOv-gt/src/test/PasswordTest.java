package test;

import data.DALException;
import data.OperatoerDTO;

public class PasswordTest {

	//static OperatoerDTO test = new OperatoerDTO();
	
	public PasswordTest() {
	}

	public static void main(String[] args) {
		boolean test1 = false;
		boolean test2 = false;
		boolean test3 = false;
		boolean test4 = true;
		
		OperatoerDTO test = new OperatoerDTO("Test Testersen", "123456-0987", "Abc02324", true); //Korrekt bruger
		
		
		//Første test som skal fejle.	
		try {
			test.validPassword(test, "Test123-");	// Navn i bruger
		} catch (DALException e) {
			test1 = true;
		} 	
		
		//Anden test som skal fejle.
		try {
			test.validPassword(test, "Ab1-"); 		// Pass for kort
		} catch (DALException e) {
			test2 = true;
		} 
		
		//Tredje test som skal fejle.
		try {
			test.validPassword(test, "Hejven10-");		// ID i pass
		} catch (DALException e) {
			test3 = true;
		} 
		
		//Denne burde IKKE fejle.
		try{
		test.validPassword(test, "ABC-123");		// Ingen små bogstaver
		test.validPassword(test, "abc-123");		// Ingen store bogstaver
		test.validPassword(test, "Abcasdasd-");		// Ingen tal
		test.validPassword(test, "Abcdasdasd123"); 	// Ingen tegn
		} catch(DALException e){
			System.out.println(e.getMeddelelse());
			test4 = false;
		}
		
		if(test1 && test2 && test3 && test4){
			System.out.println("Test succeded");
		} else {
			System.out.println("Test failed");
		}
		
	}

}

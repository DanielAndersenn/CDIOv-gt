package data;
import java.util.Scanner;


public class Graenseflade {

	private IOperatoerDAO operatoerInterface;
	private OperatoerDTO loggedInUser;
	Scanner input = new Scanner(System.in);	
	
	//States: 	
	public enum State {LOG_IN, ROOT_MENU, CREATE_USER, CHANGE_PASSWORD , WEIGH, EXIT};
	private State state = State.LOG_IN;
	
	public Graenseflade(IOperatoerDAO operatoerDTO) {
		this.operatoerInterface = operatoerDTO;
	}
	
	public void run() 
	{
		while(true)
		{
			switch(state)
			{
			case LOG_IN: logIn();
			break;
			case ROOT_MENU: rootMenu();
			break;
			case CREATE_USER: createUser();
			break;
			case CHANGE_PASSWORD: changePassword();
			break;
			case WEIGH: weigh();
			break;
			case EXIT: exit();
			}
		}
	}
	
	private void logIn() {
		
		int operatoerID; 
		String password;
		
		System.out.println("Velkommen til applikation v1.0");
		System.out.println("Indtast dit administrator operatør ID:");
		
		operatoerID = input.nextInt();
		input.nextLine();
		
		try {
			loggedInUser = operatoerInterface.getOperatoer(operatoerID);
			System.out.println(loggedInUser);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Indtast password:");
		
		password = input.nextLine();
		
		if(password.equals(loggedInUser.password)) state = State.ROOT_MENU;
		
		
	}

	private void exit() {
		System.exit(0);
		
	}

	private void weigh() {
		// TODO Auto-generated method stub
		
	}

	private void changePassword() {
		
//		String currPassword;
//		String newPassword;
//		
//		System.out.println("To change your password, first input your current password: ");
//		
//		currPassword = input.nextLine();
//		
//		if(currPassword.equals(loggedInUser.password)) 
//		{
//			System.out.println("Current password matched, type in your new password:");
//			newPassword = input.nextLine();
//			operatoerInterface.updateOperatoer(opr);
//		} else 
//		{
//			System.out.println("Existing password not matched. Returning to root menu!");
//			state = State.ROOT_MENU;
//		}
		
	}

	private void createUser() {
		String navn;
		String cpr;
		String password;
		boolean isAdmin;
		
		OperatoerDTO newUser;
		
		System.out.println("Du har valgt at oprette en ny bruger! Indtast først navnet på brugeren:");
		
		navn = input.nextLine();
		
		System.out.println("Indtast cpr-nummer:");
		
		cpr = input.nextLine();
		
		System.out.println("Indtast password:");
		
		password = input.nextLine();
		
		System.out.println("Skal brugeren være adminstrator? Input Y for ja eller N for nej:");
		
		isAdmin = (input.nextLine().equals("Y")) ? true : false;
		
		newUser = new OperatoerDTO(navn, cpr, password, isAdmin);
		
		try {
			operatoerInterface.createOperatoer(newUser);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Følgende bruger er blevet oprettet:");
		System.out.println(newUser);
		
		state = State.ROOT_MENU;
		
	}

	private void rootMenu() {
		System.out.println("Indtast nummeret på den handling du ønsker at udføre!");
		System.out.println("1. Opret ny bruger");
		System.out.println("2. Skift password");
		System.out.println("3. Afvejning");
		System.out.println("4. Afslut");
		
		int actionChoice = input.nextInt();
		
		if(actionChoice == 1) state = State.CREATE_USER; 
		if(actionChoice == 2) state = State.CHANGE_PASSWORD; 
		if(actionChoice == 3) state = State.WEIGH; 
		if(actionChoice == 4) state = State.EXIT; 
		
		input.nextLine();
		
	}
	
	
}

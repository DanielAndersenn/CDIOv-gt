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
		System.out.println("Indtast dit administrator operat�r ID:");
		
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
		
		String password;
		int taraWeight;
		int bruttoWeight;
		int userChoice;
		
		System.out.println("To weigh something put in your password");
		password = input.nextLine();
		if(password.equals(loggedInUser.password))
		{
			System.out.println("Password correct. Put in the weight of tara in kg:");
			taraWeight = input.nextInt();
			input.nextLine();
			System.out.println("Put in the gross weight in kg:");
			bruttoWeight = input.nextInt();
			input.nextLine();
			System.out.println("Net weight: " + (taraWeight+bruttoWeight));
			System.out.println("\n1. Weigh again");
			System.out.println("2. Return to rootmenu");
			userChoice = input.nextInt();
			input.nextLine();
			
			if(userChoice == 1) state = State.WEIGH;
			if(userChoice == 2) state = State.ROOT_MENU;
		} else
		{
			System.out.println("Password incorrect. Returning to rootmenu!");
			state = State.ROOT_MENU;
		}
	}

	private void changePassword() {
		
		String currPassword;
		String newPassword;
		String newPassword2;
		
		System.out.println("To change your password, first input your current password: ");
		
		currPassword = input.nextLine();
		
		if(currPassword.equals(loggedInUser.password)) 
		{
			System.out.println("Current password matched, type in your new password:");
			newPassword = input.nextLine();
			System.out.println("Type the new password again to confirm the change:");
			newPassword2 = input.nextLine();
			loggedInUser.password = newPassword;
			System.out.println("Password has been changed. Returning to rootmenu!");
			System.out.println(loggedInUser + " | Med f�lgende password: " + loggedInUser.password);
			state = State.ROOT_MENU;
		} else 
		{
			System.out.println("Existing password not matched. Returning to root menu!");
			state = State.ROOT_MENU;
		}
		
	}

	private void createUser() {
		String navn;
		String cpr;
		String password;
		boolean isAdmin;
		
		OperatoerDTO newUser;
		
		System.out.println("Du har valgt at oprette en ny bruger! Indtast f�rst navnet p� brugeren:");
		
		navn = input.nextLine();
		
		System.out.println("Indtast cpr-nummer:");
		
		cpr = input.nextLine();
		
		System.out.println("Indtast password:");
		
		password = input.nextLine();
		
		System.out.println("Skal brugeren v�re adminstrator? Input Y for ja eller N for nej:");
		
		isAdmin = (input.nextLine().equals("Y")) ? true : false;
		
		newUser = new OperatoerDTO(navn, cpr, password, isAdmin);
		
		try {
			operatoerInterface.createOperatoer(newUser);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("F�lgende bruger er blevet oprettet:");
		System.out.println(newUser);
		
		state = State.ROOT_MENU;
		
	}

	private void rootMenu() {
		System.out.println("Indtast nummeret p� den handling du �nsker at udf�re!");
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

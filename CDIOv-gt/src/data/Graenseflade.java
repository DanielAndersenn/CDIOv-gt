package data;
import java.util.ArrayList;
import java.util.Scanner;


public class Graenseflade {

	private IOperatoerDAO operatoerInterface;
	private OperatoerDTO loggedInUser;
	Scanner input = new Scanner(System.in);	
	
	//States: 	
	public enum State {LOG_IN, ROOT_MENU, CREATE_USER, CHANGE_PASSWORD , WEIGH, EXIT, DELETE_OPERATOR};
	private State state = State.LOG_IN;
	
	public Graenseflade(IOperatoerDAO operatoerDTO) {
		this.operatoerInterface = operatoerDTO;
	}
	
	public void run() throws DALException 
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
			break;
			case DELETE_OPERATOR: deleteOperator();
			}
		}
	}
	
	private void deleteOperator() throws DALException {
		// TODO Auto-generated method stub
		int oprId;
		OperatoerDTO userToDelete;
		ArrayList<OperatoerDTO> allOperators = operatoerInterface.getOperatoerList();
		System.out.println("All operators in the system");
		for(int i = 0; i < allOperators.size(); i++){
			System.out.println(allOperators.get(i));
		}
		System.out.println("Choose ID of operator that you would like to delete:");
		oprId = input.nextInt();
		userToDelete = operatoerInterface.getOperatoer(oprId);
		operatoerInterface.deleteOperatoer(userToDelete);
		
		state = State.ROOT_MENU;
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
		state = State.LOG_IN;
		
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
	
		
		System.out.println("To change your password, first input your current password: ");
		
		currPassword = input.nextLine();
		
		if(currPassword.equals(loggedInUser.password)) 
		{
			try {
				operatoerInterface.updateOperatoer(loggedInUser);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		if (loggedInUser.isAdmin)System.out.println("5. (ADMIN) Slet bruger");
		
		int actionChoice = input.nextInt();
		
		if(actionChoice == 1) state = State.CREATE_USER; 
		if(actionChoice == 2) state = State.CHANGE_PASSWORD; 
		if(actionChoice == 3) state = State.WEIGH; 
		if(actionChoice == 4) state = State.EXIT;
		if (loggedInUser.isAdmin && actionChoice == 5) state = State.DELETE_OPERATOR;
		
		input.nextLine();
		
	}
	
	
}

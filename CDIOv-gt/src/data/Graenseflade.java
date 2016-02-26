package data;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Graenseflade {

	private IOperatoerDAO operatoerInterface;
	private OperatoerDTO loggedInUser;
	Scanner input = new Scanner(System.in);	
	//States: 	
	public enum State {LOG_IN, ROOT_MENU, CREATE_USER, CHANGE_PASSWORD , WEIGH, EXIT, DELETE_OPERATOR};
	private State state = State.LOG_IN;
	
	// Constructor
	public Graenseflade(IOperatoerDAO operatoerDTO) {
		this.operatoerInterface = operatoerDTO;
	}
	
	// Run-method
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
	
	// Delete operator
	private void deleteOperator() throws DALException {
		
		int oprId;
		
		while (true) {
			try {
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
				break;
				}
			catch (DALException e){
				System.out.println(e.getMeddelelse());
			}
			catch (InputMismatchException ie) {
				System.out.println("Please give a correct type of input");
				input.next();
			}
		}
		
	}

	// Log in method
	private void logIn() throws DALException {
		int operatoerID = 0; 
		String password = "";
		
		System.out.println("Welcome to application v1.0");
			
		// Controls and verify the ID
		while (true) {
			try {
				System.out.println("Type in your ID:");
				operatoerID = input.nextInt();
				input.nextLine();
				loggedInUser = operatoerInterface.getOperatoer(operatoerID);
				System.out.println(loggedInUser);
				break;
			}
			catch (DALException ie) {
				System.out.println(ie.getMeddelelse());
			}
			catch (InputMismatchException ee) {
				System.out.print("Please give a correct type of input\n");
				input.next();
			}
		}
		
		// Controls and verify the password
		while (true) {
		
			System.out.println("Type in your password:");
			password = input.nextLine();
	
			if(password.equals(loggedInUser.password)) {
				break;
			}
			else {
				System.out.println("Password incorrect");
			}
		}
		
		state = State.ROOT_MENU;
	}

	// Exit method
	private void exit() {
		state = State.LOG_IN;
		
	}

	// Weight method
	private void weigh() {
		
		String password;
		int taraWeight;
		int bruttoWeight;
		int userChoice;
		
		System.out.println("To weigh something put in your password");
		password = input.nextLine();
		if(password.equals(loggedInUser.password))
		{
			try {
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
			}
			catch (InputMismatchException e) {
				System.out.println("Wrong type of input. Returning to rootmenu");
				state = State.ROOT_MENU;
			}
		} else
		{
			System.out.println("Password incorrect. Returning to rootmenu!");
			state = State.ROOT_MENU;
		}
	}

	// Change password method
	private void changePassword() throws DALException {
		
		String currPassword;
	
		System.out.println("To change your password, first input your current password: ");
		
		currPassword = input.nextLine();
		try {
			if(currPassword.equals(loggedInUser.password)) {
				operatoerInterface.updateOperatoer(loggedInUser);
				state = State.ROOT_MENU;
			} 
			else {
				System.out.println("Existing password not matched. Returning to root menu!");
				state = State.ROOT_MENU;
			}
		}
		catch (DALException e) {
			System.out.println(e.getMeddelelse());
		}
	}

	// Create user method
	private void createUser() throws DALException {
		String navn;
		String cpr;
		String password;
		boolean isAdmin;
		
		OperatoerDTO newUser;
		
		System.out.println("You have chosen to create a new user! Give the name of the user:");
		
		navn = input.nextLine();
		
		System.out.println("Type in the cpr-no.:");
		
		cpr = input.nextLine();
		
		System.out.println("Type in the password:");
		
		password = input.nextLine();
		
		System.out.println("Is this an administratoer? Input \"Y\" for yes or \"N\" for no:");
			
		isAdmin = (input.nextLine().equals("Y")) ? true : false;
		
		newUser = new OperatoerDTO(navn, cpr, password, isAdmin);
		
		// Verifies the password for the new user
		while(true){
		try{
			operatoerInterface.createOperatoer(newUser);
			break;
		}
		catch(DALException e){
			System.out.println(e.getMeddelelse());
			System.out.println("Try again:");
			newUser.password = input.nextLine();
		}
		}
		System.out.println("The following user have been created:");
		System.out.println(newUser);
				
		state = State.ROOT_MENU;
	}

	// Method for root menu
	private void rootMenu() {
		
	while (true) {
		try {
		System.out.println("Type in the number that corresponds with the action you want to do!");
		System.out.println("1. Create a new user");
		System.out.println("2. Change password");
		System.out.println("3. Weight");
		System.out.println("4. End login");
		if (loggedInUser.isAdmin)System.out.println("5. (ADMIN) Delete user");
		
		int actionChoice = input.nextInt();
		
		if(actionChoice == 1) state = State.CREATE_USER; 
		if(actionChoice == 2) state = State.CHANGE_PASSWORD; 
		if(actionChoice == 3) state = State.WEIGH; 
		if(actionChoice == 4) state = State.EXIT;
		if (loggedInUser.isAdmin && actionChoice == 5) state = State.DELETE_OPERATOR;
		input.nextLine();
		break;
		}
		catch (InputMismatchException e){
			System.out.println("Please give a correct type of input");
			input.nextLine();
		}
	}
	}
}

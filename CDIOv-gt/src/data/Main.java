package data;

public class Main {

	public static void main(String[] args) {
		IOperatoerDAO d = new OperatoerDTO();
		OperatoerDTO operatoer0 = new OperatoerDTO("sysadmin", "000000-0000", "Abc02324", true);
		OperatoerDTO operatoer1 = new OperatoerDTO("Daniel Andersen", "090492-3567", "hejven123", true);
		OperatoerDTO operatoer2 = new OperatoerDTO("Mathias Nielsen", "123456-7890", "hejven123", true);
		try {
			d.createOperatoer(operatoer0);
			d.createOperatoer(operatoer1);
			d.createOperatoer(operatoer2);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Graenseflade g = new Graenseflade(d);		
		g.run();

	}

}

package data;

public class Main {

	public static void main(String[] args) throws DALException {
		IOperatoerDAO d = new OperatoerDTO();
		OperatoerDTO operatoer0 = new OperatoerDTO("sys admin", "000000-0000", "Abc02324", true);
		OperatoerDTO operatoer1 = new OperatoerDTO("Daniel Andersen", "090492-3567", "Abc02324", true);
		OperatoerDTO operatoer2 = new OperatoerDTO("Mathias Nielsen", "123456-7890", "Abc02324", true);

		d.createOperatoer(operatoer0);
		d.createOperatoer(operatoer1);
		d.createOperatoer(operatoer2);

		Graenseflade g = new Graenseflade(d);		
		g.run();
	}
}

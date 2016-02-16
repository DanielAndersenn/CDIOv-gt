package data;

import java.util.ArrayList;
import java.util.List;

public interface IOperatoerDAO {

	OperatoerDTO getOperatoer(int oprId) throws DALException;
	ArrayList<OperatoerDTO> getOperatoerList() throws DALException;
	void createOperatoer(OperatoerDTO opr) throws DALException;
	void updateOperatoer(OperatoerDTO opr) throws DALException;
	void deleteOperatoer(OperatoerDTO opr) throws DALException;
}

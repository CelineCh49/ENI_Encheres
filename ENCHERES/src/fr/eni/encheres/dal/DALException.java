package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALException extends Exception {
	
    private static final long serialVersionUID = 1L;
    private List<Integer> listErrorCodes;
    
    public DALException(String pseudo, String mail, SQLException e) {
        super();
        this.listErrorCodes=new ArrayList<>();
    }

    public DALException(String string, SQLException e) {
		// TODO Auto-generated constructor stub
	}

	/**
     * Add an error code to the instance
     * @param code integer a code referenced in ErrorCodesDAL.java
     */
    public void addError(int code)
    {
        if(!this.listErrorCodes.contains(code))
        {
            this.listErrorCodes.add(code);
        }
    }

    /**
     * Returns true if the instance contains error codes and false if not
     * @return boolean
     */
    public boolean hasErrors()
    {
        return this.listErrorCodes.size()>0;
    }

    /**
     * Returns the list of the error codes stored in the instance
     * @return List
     */
    public List<Integer> getListErrorCodes()
    {
        return this.listErrorCodes;
    }

}

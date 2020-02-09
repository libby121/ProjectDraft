package Tests;

import java.sql.SQLException;

import exceptions.AccessDeniedException;
import exceptions.AdminAccessDeniedException;
import exceptions.CompanyAccessDeniedException;
import exceptions.CompanyDoesntExistException;
import exceptions.CustomerAccessDeniedException;
import facades.AdminFacade;
import Login.ClientType;
import Login.LoginManager;

public class Test {

	
	public static void main(String[] args) {
		
		//public void testAll(){
			
//			AdminFacade adminF =LoginManager.getInstance();
//			AdminFacade adminF =LoginManager.getInstance();
//			AdminFacade adminF =LoginManager.getInstance();
			
		AdminFacade admin=new AdminFacade();
		try {
			admin.login("com.admin@admin", "admin");
		} catch (SQLException e1) {
			 
		}
		//AdminFacade af=LoginManager.getInstance();
		
LoginManager log=LoginManager.getInstance();


try {
	log.login("wow@admin", "121344", ClientType.Company);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (AdminAccessDeniedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (CompanyDoesntExistException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (CompanyAccessDeniedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (CustomerAccessDeniedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (AccessDeniedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
			
			
		}
	}


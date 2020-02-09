package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {//singletone-only static method?

	private static ConnectionPool instance= new ConnectionPool();//one and only object of the class
	//created each time the program runs?...//static object?
	private ArrayList<Connection>connections=new ArrayList<Connection>();//not static..?
	//if a connection is in the list, its available-a list of ready-to-use connections
	private static final int MAX_CONNECTIONS=10;
	//constants are usually static. here it has no meaning, only one variable
	//how can you tell the number of connections?depends on the hardware?
	//final has no get/set
	private ConnectionPool(){
		//System.out.println("test");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			for (int i = 1; i <=MAX_CONNECTIONS ; i++) {
				connections.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/coupons_management_system?serverTimezone=UTC",
						"root", "1234"));	

			}


		} catch (ClassNotFoundException e) {System.out.println("Cannot find class : "+e.getMessage());
		} catch (SQLException e) {System.out.println("cannot connect to DB"+e.getMessage());
		}

	}//private ctor

	public static ConnectionPool getInstance() {
		return instance;
	}//does singleton require only static methods?


	public synchronized Connection getConnection(){//can i use syn for each method?
		while(connections.size()==0)
			try {
				wait();
			} catch (InterruptedException e) { 
			}
		Connection con= connections.get(0);
		connections.remove(con);//cannot be after return

		return con;
	}

	public synchronized void restoreConnection(Connection connection){
		connections.add(connection);
		notify();//gentle awakening the sleeping thread from connectionPool if needed
	}
	
	public void closeAllConnections(){
		for (Connection connection : connections) {
			try {
				connection.close();//connection might be already closed->try
			} catch (SQLException e) {
			}
		}
	}
}
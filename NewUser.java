import java.sql.*;

public class NewUser{
        
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
       
    public NewUser() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "News", "root", "root"); 
  
            } catch (ClassNotFoundException cfe) {
     
            } catch (SQLException e) {
                System.out.println(e);
            }
    }
 
    public void insertNewUser(String mail, String name, String pass){
		try{
			stmt = con.prepareStatement("insert into users values(?,?,?)");
			stmt.setString(1,  mail);
			stmt.setString(2, name);
			stmt.setString(3, pass);
	
			stmt.execute();
		}
		catch(SQLException s){
			System.out.println(s.getLocalizedMessage());
		} finally{
            try {
            	con.close();
            } catch(Exception ee){
            	System.out.println("Error while Closing the Connection ......"+ee.getMessage());
            }
        }
	}

	public String checkUser(String mail){

        String result = "null";

        try {

            stmt = con.prepareStatement("select password from users where mail=?");
            stmt.setString(1,mail);
         
            if(stmt.execute()) {
                rs = stmt.getResultSet();
                result = "Hello";
                if (rs.next()) {
                    result = "true";
                }else{
                	result = "false";
                }
            }
            else{
                result = "false";
                System.out.println("no execution .....");
            }
        }
        catch (Exception ee){

            System.out.println("Error while executing the query ......"+ee.getMessage());
            result = ee.getMessage();
            

        } finally{
            try {
            	con.close();
            } catch(Exception ee){
            	System.out.println("Error while Closing the Connection ......"+ee.getMessage());
            }
        }
        return result;
    }
}

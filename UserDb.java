import java.sql.*;

class UserDb {

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public UserDb() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "News", "root", "root");

        } catch (ClassNotFoundException cfe) {

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public String selectQuery(String mail, String option){

        String result=null;

        try {

            if (option.equals("password")){
            stmt = con.prepareStatement("select password from users where mail=?");
            stmt.setString(1,mail);
            } else{
               stmt = con.prepareStatement("select username from users where mail=?");
            stmt.setString(1,mail); 
            }

            if(stmt.execute()) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    result =rs.getString(1);
                }
            }
            else{
                System.out.println("no execution .....");
            }
        }
        catch (Exception ee){

            System.out.println("Error while executing the query ......"+ee.getMessage());
            result = "Error ";

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

/**
 * @class Query.java
 * @author Louis Wong
 */

package DAO;

import java.sql.Statement;
import java.sql.ResultSet;
import static DAO.DBConnection.conn;

public class Query {
    private static String query;
    private static Statement stmt;
    private static ResultSet result;

    /**
     *
     * @param q string of SQL query statment
     */
    public static void makeQuery(String q){
        query =q;
        try{
            stmt=conn.createStatement();
            // determine query execution
            if(query.toLowerCase().startsWith("select"))
                result=stmt.executeQuery(q);
            if(query.toLowerCase().startsWith("delete")||query.toLowerCase().startsWith("insert")||query.toLowerCase().startsWith("update"))
                stmt.executeUpdate(q);

        }
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /**
     *
     * @return SQL result
     */
    public static ResultSet getResult(){
        return result;
    }
}

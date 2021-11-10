package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class jdbcHelper {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String user = "sa";
    static String password = "123456";
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=STIA";
    
    static {
        try {
            Class.forName(driver);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt;
        if(sql.trim().startsWith("{")) {
            stmt = conn.prepareCall(sql);
        } else {
            stmt = conn.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i+1, args[i]);
        }
        return stmt;
            
    }
    
    public static ResultSet query(String sql, Object...args) throws SQLException {
        PreparedStatement stmt = getStmt(sql, args);
        return stmt.executeQuery();
    }
    
    public static Object value(String sql, Object...args) throws SQLException {
        try {
            ResultSet rs = query(sql, args);
            if(rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int Update(String sql, Object...args) throws SQLException {
        try {
            PreparedStatement stmt = getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}

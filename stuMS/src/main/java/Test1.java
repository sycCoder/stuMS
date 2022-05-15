import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class Test1 {
    public static void main(String[] args)throws SQLException {
        String url="jdbc:mysql://localhost:3306/stuMS";
        String user="syc";
        String password="syc8326190";

        String sql="select * from test_table";
        Connection connection = DriverManager.getConnection(url,user,password);
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getString("name"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
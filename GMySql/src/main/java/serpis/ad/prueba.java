package serpis.ad;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;



public class prueba {
	
	private static Connection connection;
	
	public static void main(String[] args) throws SQLException {
		//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba?user=root&password=sistemas");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		insert();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from categoria");
		while(resultSet.next())                                                                                                                                                                 
			System.out.printf("%s %s\n", resultSet.getObject(1), resultSet.getObject(2));
		statement.close(); //Implicit resultSet.close()
		connection.close();
	}
	private static void insert() throws SQLException {
		String insertSql= "insert into categoria (nombre) values (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
		preparedStatement.setObject(1, "categoría" + LocalDateTime.now());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	private static void load() throws SQLException {
		String selectSql = "select * from value (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
		preparedStatement.close();
		}

		private static void delete(int id){
		String deleteSql="delete from categoria where id=?";
		PreparedStatement preparedStatement;

		try {
		preparedStatement=connection.prepareStatement(deleteSql);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		}catch (SQLException e) {
		System.out.println(e);
		System.out.println("No se puede por el foreign key");
		}
		}
}

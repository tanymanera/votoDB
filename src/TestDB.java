import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class TestDB {

	public static void main(String[] args) {
//		0-importato tra i build mysql-connector-java-8.0.12.jar

//		1-define the connection URL
		String jdbcURL = "jdbc:mysql://localhost/voti?user=root&password=root"
				+ "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

//		2-establish the connection, attenzione a importare java.sql o javax.sql
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);

//			3-create a statement; import java.sql
			Statement statement = conn.createStatement();
			String sql = "SELECT nome, voto " + "FROM mio_libretto " + "WHERE voto > 28;";

//			4-execute a query or update
			ResultSet res = statement.executeQuery(sql);

//			5-process the result
			while (res.next()) {
				String nome = res.getString("nome");
				int voto = res.getInt("voto");
				System.out.format("Nell'esame %s conseguito voto %d.\n", nome, voto);
			}

			statement.close();

//			seconda query
			statement = conn.createStatement();
			sql = "INSERT INTO `voti`.`mio_libretto` (`nome`, `voto`) VALUES ('Geometria', '28');";
			statement.executeUpdate(sql);
			statement.close();

//			6-close the connection
			conn.close();
		} catch (SQLIntegrityConstraintViolationException ie) {
			System.out.println("Esame Geometria già registrato.");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

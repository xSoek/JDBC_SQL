package modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConexionBD {
	Connection myConn = null;
	Statement stm = null;
	ResultSet rst = null;

	public ConexionBD() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("config.ini"));
			String usr = props.getProperty("usr");
			String pwd = props.getProperty("pwd");
			String url = props.getProperty("url");
			Class.forName("com.mysql.cj.jdbc.Driver");
			myConn = DriverManager.getConnection(url, usr, pwd);
//		conexion.close();

		} catch (ClassNotFoundException cnfe) {
			System.out.println(" _- Driver JDBC no encontrado -_");
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			System.out.println(" _- Error al conectarse a la BD -_");
			sqle.printStackTrace();
		} catch (Exception e) {
			System.out.println(" _– Error de Conexión con MySQL -_");
			e.printStackTrace();
		}
	}

	public Connection getConn() {

		return myConn;

	}
}
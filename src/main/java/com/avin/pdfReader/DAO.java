package com.avin.pdfReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.postgresql.util.PGobject;

public class DAO {
	String DEFAULT_TABLE_NAME = "country";

	void insertToDB(String jsonString) {
		String connectionUrl = "jdbc:postgresql://localhost:5432/avin";
		String sql = "INSERT INTO " + DEFAULT_TABLE_NAME + "(data) values (?)";
		String user = "postgres";
		String password = "aravind";
		try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {
			PGobject jsonObject = new PGobject();
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement(sql);
			jsonObject.setType("json");
			jsonObject.setValue(jsonString);
			pstmt.setObject(1, jsonObject);
			Boolean bool = pstmt.execute();
			System.out.println(bool);
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}

}

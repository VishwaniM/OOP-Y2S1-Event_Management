package net.javaguides.venuemanagement.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.javaguides.venuemanagement.model.Venue;


public class VenueDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/eventmanager";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Ams@98217";

	private static final String INSERT_VENUE_SQL = "INSERT INTO venue" + "  (name, address, phone, capacity, charge) VALUES "
			+ " (?, ?, ?, ?, ?);";

	private static final String SELECT_VENUE_BY_ID = "select id,name,address,phone,capacity,charge from venue where id =?";
	private static final String SELECT_ALL_VENUE = "select * from venue";
	private static final String DELETE_VENUE_SQL = "delete from venue where id = ?;";
	private static final String UPDATE_VENUE_SQL = "update venue set name = ?,address= ?, phone =?,capacity =?,charge =? where id = ?;";

	public VenueDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return connection;
	}

	public void insertVenue(Venue venue) throws SQLException {
		System.out.println(INSERT_VENUE_SQL);
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VENUE_SQL)) {
			preparedStatement.setString(1, venue.getName());
			preparedStatement.setString(2, venue.getAddress());
			preparedStatement.setString(3, venue.getPhone());
			preparedStatement.setString(4, venue.getCapacity());
			preparedStatement.setString(5, venue.getCharge());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Venue selectVenue(int id) {
		Venue venue = null;
		//Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VENUE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			//Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String capacity = rs.getString("capacity");
				String charge = rs.getString("charge");
				venue = new Venue(id, name, address, phone, capacity, charge);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return venue;
	}

	public List<Venue> selectAllVenue() {

		
		List<Venue> venue = new ArrayList<>();
		//Establishing a Connection
		try (Connection connection = getConnection();

				//Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VENUE);) {
			System.out.println(preparedStatement);
			//Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			//Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String capacity = rs.getString("capacity");
				String charge = rs.getString("charge");
				venue.add(new Venue(id, name, address, phone, capacity, charge));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return venue;
	}

	public boolean deleteVenue(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_VENUE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateVenue(Venue venue) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_VENUE_SQL);) {
			statement.setString(1, venue.getName());
			statement.setString(2, venue.getAddress());
			statement.setString(3, venue.getPhone());
			statement.setString(4, venue.getCapacity());
			statement.setString(5, venue.getCharge());
			statement.setInt(6, venue.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}




package wayne.csc4710.exercise4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wayne.csc4710.exercise4.Company;

public class CompanyDAO {
	private String url;
	private String username;
	private String password;
	private Connection connection;
	
	public CompanyDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	protected void connect() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			
			connection = DriverManager.getConnection(url, username, password);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
	public boolean insertCompany(Company company) throws SQLException {
		String update = "INSERT INTO companyTable (id, name, networth, ceo) "
					+ "VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = connection.prepareStatement(update);
		statement.setInt(1, company.getId());
		statement.setString(2, company.getName());
		statement.setDouble(3, company.getNetworth());
		statement.setString(4, company.getCeo());
		
		boolean isRowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		
		return isRowInserted;
	}
	
	public List<Company> listAllCompanies() throws SQLException {
		
		List<Company> companyList = new ArrayList<>();
		
		String query = "SELECT * FROM companyTable";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			double networth = resultSet.getDouble("networth");
			String ceo = resultSet.getString("ceo");
			
			Company company = new Company(id, name, networth, ceo);
			companyList.add(company);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return companyList;
	}
	
	public boolean deleteCompany(Company company) throws SQLException {
		String update = "DELETE FROM companyList WHERE id = ?";
		
		connect();
		
		PreparedStatement statement = connection.prepareStatement(update);
		
		statement.setInt(1, company.getId());
		
		boolean isRowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		
		return isRowDeleted;
	}
	
	public boolean updateCompany(Company company) throws SQLException {
		String update = "UPDATE companyList SET name = ?, networth = ?, ceo = ?";
		update += " WHERE id = ?";
		connect();
		
		PreparedStatement statement = connection.prepareStatement(update);
		statement.setString(1, company.getName());
		statement.setDouble(2, company.getNetworth());
		statement.setString(3, company.getCeo());
		
		boolean isRowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		
		return isRowUpdated;
	}
	
	public Company getCompany(int id) throws SQLException {
		Company company = null;
		String query = "SELECT * FROM companyList WHERE id = ?";
		connect();
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1,  id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String name = resultSet.getString("name");
			double networth = resultSet.getDouble("networth");
			String ceo = resultSet.getString("ceo");
			
			company = new Company(id, name, networth, ceo);
		}
		
		resultSet.close();
		statement.close();
		
		return company;
	}
	

	
	
}
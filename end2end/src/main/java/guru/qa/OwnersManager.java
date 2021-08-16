package guru.qa;

import guru.qa.domain.Owner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class OwnersManager {

	private DataSource ds = DataSourceProvider.INSTANCE.getDataSource();

//	INSERT INTO owners (id, firstName, lastName, adress, city, phone)
//	VALUES ("","","","","","");

	public int createOwner(Owner owner) {

		String rawSQLRequest = "INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(rawSQLRequest, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, owner.getFirstName());
			ps.setString(2, owner.getLastName());
			ps.setString(3, owner.getAddress());
			ps.setString(4, owner.getCity());
			ps.setString(5, owner.getPhone());
			ps.executeUpdate();

			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
 	}

	public void deleteOwner(int id) {

		String rawSQLRequest = "DELETE FROM owners WHERE id = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(rawSQLRequest)) {
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Owner> findByLastName(String lastName){
		List<Owner> result = new ArrayList<>();
		String rawSQLRequest = "SELECT * FROM owners WHERE last_name = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(rawSQLRequest)) {
			ps.setString(1, lastName);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()){
				result.add(Owner.builder()
					.firstName(resultSet.getString("first_name"))
					.lastName(resultSet.getString("last_name"))
					.address(resultSet.getString("address"))
					.phone(resultSet.getString("telephone"))
					.city(resultSet.getString("city"))
					.build());
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}



}

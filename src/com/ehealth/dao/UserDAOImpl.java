package com.ehealth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ehealth.models.User;

public class UserDAOImpl implements UserDAO {

	Connection conn;
	PreparedStatement ps;
	@Override
	public int insertUserRegistrationToDB(User user) {
		int status = 0;
		try {
			conn = DBConnection.getConnection();
			ps=conn.prepareStatement("insert into users (username, email, password) values(?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			status = ps.executeUpdate();
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public User getUserFromDB(String username, String password) {
		User user = new User();
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("Select * from users where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(4));
				user.setEmail(rs.getString(3));
				user.setFirstName(rs.getString(5));
				user.setLastName(rs.getString(6));
				user.setDate_of_birth(rs.getString(7));
				user.setInsuranceType(rs.getString(8));
				user.setInsuranceName(rs.getString(9));
				user.setHealth_problem(rs.getString(10));
				user.setHealth_information(rs.getString(11));
			}
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return user;
	}
	
	@Override
	public boolean checkUsernameExistedInDB(String username) {
		boolean usernameExisted = false;
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("select * from users where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usernameExisted = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return usernameExisted;
	}
	@Override
	public int insertUserInformationToDB(User user) {
		int status = 0;
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("update users set firstName=? , lastName=? , date_of_birth = ?, insuranceName=?, insuranceType=?, health_problem=?, health_information=?  where username = ?");
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getDate_of_birth());
			ps.setString(4, user.getInsuranceName());
			ps.setString(5, user.getInsuranceType());
			ps.setString(6, user.getHealth_problem());
			ps.setString(7, user.getHealth_information());
			ps.setString(8, user.getUsername());
			status = ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	public int insertUserHealthProblemToDB(String healthProblem, String username) {
		int status = 0;
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("update users set health_problem=?, where username = ?");
			ps.setString(1, healthProblem);
			ps.setString(2, username);
			status = ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}

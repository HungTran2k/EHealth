package com.ehealth.dao;
import com.ehealth.models.User;

public interface UserDAO {
	public int insertUserRegistrationToDB(User user);
	public User getUserFromDB(String username, String password);
	boolean checkUsernameExistedInDB(String username);
	public int insertUserInformationToDB(User user);
	public int insertUserHealthProblemToDB(String healthProblem, String username);
}

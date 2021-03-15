package com.ehealth.dao;
import java.util.List;
import com.ehealth.models.User;

public interface UserDAO {
	public int insertUserRegistrationToDB(User user);
	public User getUserFromDB(String username, String password);
	boolean checkUsernameExistedInDB(String username);
	public int insertUserInformationToDB(User user);
	public int insertUserHealthProblemToDB(String healthProblem, String username);
	public List<User>getAllUsers();
	public void EditUser(String email, String password, String insuranceType, String insuranceName, String username, String healthInformation);
	public void deleteUserInList(User user);
}

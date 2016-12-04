package lms.dao;

public interface UserDAO {

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);

	Integer getUserAttempts(String username);

}
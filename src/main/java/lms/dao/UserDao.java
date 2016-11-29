package lms.dao;

public interface UserDao {

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);

	Integer getUserAttempts(String username);

}
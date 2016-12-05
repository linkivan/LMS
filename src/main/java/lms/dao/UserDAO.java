package lms.dao;

import java.util.List;

import lms.model.UIUserModel;
import lms.model.UserModel;

public interface UserDAO {

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);

	Integer getUserAttempts(String username);

	UIUserModel getUserByUsername(String username);

	UIUserModel getUserById(String userId);

	List<UIUserModel> getStudentsOfCourse(int courseId);

	List<UIUserModel> getInstructorsOfCourse(int courseId);

	boolean deleteUserOfCourse(int userId, int courseId);

	boolean addUserToCourse(int userId, int courseId);

	boolean isUserinCourse(int userId, int courseId);
}
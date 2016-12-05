package lms.service;

import java.util.List;

import lms.model.UIUserModel;
import lms.model.UserModel;

public interface UserService {
	UIUserModel getUserByUsername(String username);

	UIUserModel getUserById(int userId);

	List<UIUserModel> getStudentsOfCourse(int courseId);

	List<UIUserModel> getInstructorsOfCourse(int courseId);

	boolean deleteUserOfCourse(int userId, int courseId);

	boolean addUserToCourse(int userId, int courseId);

	boolean isUserinCourse(int userId, int courseId);
}

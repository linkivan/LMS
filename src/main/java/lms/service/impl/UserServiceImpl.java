package lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.dao.UserDAO;
import lms.model.UIUserModel;
import lms.model.UserModel;
import lms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public UIUserModel getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	@Override
	public UIUserModel getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UIUserModel> getStudentsOfCourse(int courseId) {
		return userDAO.getStudentsOfCourse(courseId);

	}

	@Override
	public List<UIUserModel> getInstructorsOfCourse(int courseId) {
		return userDAO.getInstructorsOfCourse(courseId);
	}

	@Override
	public boolean deleteUserOfCourse(int userId, int courseId) {
		return userDAO.deleteUserOfCourse(userId, courseId);
	}

	@Override
	public boolean addUserToCourse(int userId, int courseId) {
		return userDAO.addUserToCourse(userId, courseId);
	}

	@Override
	public boolean isUserinCourse(int userId, int courseId) {
		return userDAO.isUserinCourse(userId, courseId);
	}

}

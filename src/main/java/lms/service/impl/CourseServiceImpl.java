package lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.dao.CourseDAO;
import lms.model.CourseModel;
import lms.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDAO courseDAO;

	@Override
	public int addCourse(CourseModel course) {
		// TODO Validate course
		return courseDAO.addCourse(course);
	}

	@Override
	public boolean modifyCourse(CourseModel course) {
		return courseDAO.modifyCourse(course);

	}

	@Override
	public CourseModel getCourseById(int courseId) {
		return courseDAO.getCourseById(courseId);
	}

	@Override
	public boolean deleteCourse(int courseId) {
		return courseDAO.deleteCourse(courseId);
	}

	@Override
	public List<CourseModel> getCoursesByUserId(int userId) {
		return courseDAO.getCoursesByUserId(userId);
	}

	@Override
	public List<CourseModel> getCourseByCodeAndSemester(String code, String Semester) {
		return courseDAO.getCourseByCodeAndSemester(code, Semester);
	}

}

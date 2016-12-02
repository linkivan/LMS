package lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.dao.CourseDAO;
import lms.model.Course;
import lms.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDAO courseDAO;

	@Override
	public int addCourse(Course course) {
		// TODO Validate course
		return courseDAO.addCourse(course);
	}

	@Override
	public boolean modifyCourse(Course course) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public Course getCourseById(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCourse(int courseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Course> getCoursesByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getCourseByCodeAndSemester(String code, String Semester) {
		return courseDAO.getCourseByCodeAndSemester(code, Semester);
	}

}

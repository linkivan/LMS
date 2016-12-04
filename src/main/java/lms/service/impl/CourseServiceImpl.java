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
		return courseDAO.modifyCourse(course);

	}

	@Override
	public Course getCourseById(int courseId) {
		return courseDAO.getCourseById(courseId);
	}

	@Override
	public boolean deleteCourse(int courseId) {
		return courseDAO.deleteCourse(courseId);
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

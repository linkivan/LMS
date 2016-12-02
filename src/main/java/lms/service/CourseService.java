package lms.service;

import java.util.List;

import lms.model.Course;

public interface CourseService {
	public int addCourse(Course course);

	public boolean modifyCourse(Course course);

	public Course getCourseById(int courseId);

	public List<Course> getCourseByCodeAndSemester(String code, String Semester);

	public boolean deleteCourse(int courseId);

	public List<Course> getCoursesByUserId(int userId);
}

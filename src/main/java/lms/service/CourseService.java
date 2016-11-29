package lms.service;

import java.util.List;

import lms.model.Course;

public interface CourseService {
	public boolean addCourse(Course course);

	public boolean modifyCourse(Course course);

	public Course getCourseById(int courseId);

	public boolean deleteCourse(int courseId);

	public List<Course> getCoursesByUserId(int userId);
}

package lms.service;

import java.util.List;

import lms.model.CourseModel;

public interface CourseService {
	public int addCourse(CourseModel course);

	public boolean modifyCourse(CourseModel course);

	public CourseModel getCourseById(int courseId);

	public List<CourseModel> getCourseByCodeAndSemester(String code, String Semester);

	public boolean deleteCourse(int courseId);

	public List<CourseModel> getCoursesByUserId(int userId);
}

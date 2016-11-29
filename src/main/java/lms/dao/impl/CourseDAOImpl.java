package lms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lms.dao.CourseDAO;
import lms.model.Course;

@Repository
public class CourseDAOImpl implements CourseDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// @Autowired
	// public CourseDAOImpl(DataSource dataSource) {
	// jdbcTemplate = new JdbcTemplate(dataSource);
	// }

	@Override
	public boolean addCourse(Course course) {
		String sql = "INSERT INTO course (instructor_id, room, semester, time, code)" + " VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, course.getInstructorId(), course.getRoom(), course.getSemester(), course.getTime(),
				course.getCode());
		return true;
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

}

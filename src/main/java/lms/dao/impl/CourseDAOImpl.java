package lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lms.dao.CourseDAO;
import lms.model.Course;

@Repository
public class CourseDAOImpl implements CourseDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addCourse(final Course course) {
		final String sql = "INSERT INTO course (instructor_id, room, semester, time, code)" + " VALUES (?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
				ps.setString(1, course.getInstructorId());
				ps.setString(2, course.getRoom());
				ps.setString(3, course.getSemester());
				ps.setString(4, course.getTime());
				ps.setString(5, course.getCode());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
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
	public List<Course> getCourseByCodeAndSemester(String code, String semester) {
		String sql = "SELECT * FROM course WHERE code = ? AND semester = ?";

		List<Course> courses = jdbcTemplate.query(sql, new Object[] { code, semester }, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course course = new Course();

				course.setId(rs.getInt("id"));
				course.setCode(rs.getString("code"));
				course.setSemester(rs.getString("semester"));
				course.setRoom(rs.getString("room"));
				course.setTime(rs.getString("time"));

				return course;
			}

		});

		return courses;
	}

}

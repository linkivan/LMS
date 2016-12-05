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
import lms.model.CourseModel;
import lms.model.UIUserModel;

@Repository
public class CourseDAOImpl implements CourseDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addCourse(final CourseModel course) {
		final String sql = "INSERT INTO course (room, semester, time, code)" + " VALUES (?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
				ps.setString(1, course.getRoom());
				ps.setString(2, course.getSemester());
				ps.setString(3, course.getTime());
				ps.setString(4, course.getCode());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public boolean modifyCourse(CourseModel course) {
		final String sql = "update course set room = ?, semester = ?, time = ?, code = ?" + " where id = ?";
		jdbcTemplate.update(sql, course.getRoom(), course.getSemester(), course.getTime(), course.getCode(),
				course.getId());
		return true;
	}

	@Override
	public CourseModel getCourseById(int courseId) {
		String sql = "SELECT * FROM course WHERE id = ?";

		CourseModel res = jdbcTemplate.queryForObject(sql, new Object[] { courseId }, new RowMapper<CourseModel>() {

			@Override
			public CourseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseModel course = new CourseModel();

				course.setId(rs.getInt("id"));
				course.setCode(rs.getString("code"));
				course.setSemester(rs.getString("semester"));
				course.setRoom(rs.getString("room"));
				course.setTime(rs.getString("time"));

				return course;
			}

		});

		return res;
	}

	@Override
	public boolean deleteCourse(int courseId) {
		String sql = "DELETE from course where id = ?";
		this.jdbcTemplate.update(sql, courseId);
		return true;
	}

	@Override
	public List<CourseModel> getCoursesByUserId(int userId) {
		String sql = "SELECT * FROM course LEFT JOIN users_courses " + "ON course.id=users_courses.course_id "
				+ "WHERE users_courses.user_id = ?";
		List<CourseModel> courses = jdbcTemplate.query(sql, new Object[] { userId }, new RowMapper<CourseModel>() {

			@Override
			public CourseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseModel course = new CourseModel();

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

	@Override
	public List<CourseModel> getCourseByCodeAndSemester(String code, String semester) {
		String sql = "SELECT * FROM course WHERE code = ? AND semester = ?";

		List<CourseModel> courses = jdbcTemplate.query(sql, new Object[] { code, semester },
				new RowMapper<CourseModel>() {

					@Override
					public CourseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						CourseModel course = new CourseModel();

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

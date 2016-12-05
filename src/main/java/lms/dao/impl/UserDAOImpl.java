package lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;

import lms.dao.UserDAO;
import lms.model.CourseModel;
import lms.model.UIUserModel;
import lms.model.UserModel;

public class UserDAOImpl implements UserDAO {

	private static final String SQL_USERS_UPDATE_LOCKED = "UPDATE USER SET account_non_locked = ? WHERE username = ?";
	private static final String SQL_USERS_COUNT = "SELECT count(*) FROM USER WHERE username = ?";

	private static final String SQL_USER_ATTEMPTS_GET = "SELECT login_attempts FROM USER WHERE username = ?";
	private static final String SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS = "UPDATE USER SET login_attempts = login_attempts + 1 WHERE username = ?";
	private static final String SQL_USER_ATTEMPTS_RESET_ATTEMPTS = "UPDATE USER SET login_attempts = 0 WHERE username = ?";

	private static final int MAX_ATTEMPTS = 3;

	private JdbcTemplate jdbcTemplate;

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void updateFailAttempts(String username) {

		Integer userAttempts = getUserAttempts(username);
		if (userAttempts != null) {
			if (isUserExists(username)) {
				// update attempts count, +1
				jdbcTemplate.update(SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS, username);
			}

			if (userAttempts + 1 >= MAX_ATTEMPTS) {
				// locked user
				jdbcTemplate.update(SQL_USERS_UPDATE_LOCKED, new Object[] { false, username });
				// throw exception
				throw new LockedException("User Account is locked!");
			}
		}
	}

	@Override
	public Integer getUserAttempts(String username) {
		try {
			int userAttempts = jdbcTemplate.queryForObject(SQL_USER_ATTEMPTS_GET, new Object[] { username },
					Integer.class);
			return userAttempts;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void resetFailAttempts(String username) {

		jdbcTemplate.update(SQL_USER_ATTEMPTS_RESET_ATTEMPTS, new Object[] { username });

	}

	private boolean isUserExists(String username) {

		boolean result = false;

		int count = jdbcTemplate.queryForObject(SQL_USERS_COUNT, new Object[] { username }, Integer.class);
		if (count > 0) {
			result = true;
		}

		return result;
	}

	@Override
	public List<UIUserModel> getStudentsOfCourse(int courseId) {
		String sql = "SELECT id, username, role FROM user LEFT JOIN users_courses "
				+ "ON user.id=users_courses.user_id " + "WHERE users_courses.course_id = ? and user.role= 'ROLE_STU'";
		List<UIUserModel> students = jdbcTemplate.query(sql, new Object[] { courseId }, new RowMapper<UIUserModel>() {

			@Override
			public UIUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				UIUserModel student = new UIUserModel();

				student.setRole("Student");
				student.setUsername(rs.getString("username"));
				student.setId(rs.getInt("id"));
				return student;
			}

		});
		return students;

	}

	@Override
	public List<UIUserModel> getInstructorsOfCourse(int courseId) {
		String sql = "SELECT id, username, role FROM user LEFT JOIN users_courses "
				+ "ON user.id=users_courses.user_id " + "WHERE users_courses.course_id = ? and user.role= 'ROLE_INSTR'";
		List<UIUserModel> instructors = jdbcTemplate.query(sql, new Object[] { courseId },
				new RowMapper<UIUserModel>() {

					@Override
					public UIUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						UIUserModel instructor = new UIUserModel();

						instructor.setRole("Instructor");
						instructor.setUsername(rs.getString("username"));
						instructor.setId(rs.getInt("id"));
						return instructor;
					}

				});
		return instructors;
	}

	@Override
	public UIUserModel getUserByUsername(String username) {
		String sql = "SELECT id, username, role FROM user WHERE username = ? and role <> 'ROLE_ADMIN'";
		UIUserModel res;
		try {
			res = jdbcTemplate.queryForObject(sql, new Object[] { username }, new RowMapper<UIUserModel>() {

				@Override
				public UIUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					UIUserModel user = new UIUserModel();
					String role;
					if ("ROLE_INSTR".equals(rs.getString("role"))) {
						role = "Instructor";
					} else {
						role = "Student";
					}
					user.setRole(role);
					user.setUsername(rs.getString("username"));
					user.setId(rs.getInt("id"));

					return user;
				}

			});
		} catch (EmptyResultDataAccessException e) {
			res = null;
		}
		return res;
	}

	@Override
	public UIUserModel getUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUserOfCourse(int userId, int courseId) {
		this.jdbcTemplate.update("delete from users_courses where user_id = ? and course_id = ?", userId, courseId);
		return true;
	}

	@Override
	public boolean addUserToCourse(int userId, int courseId) {
		final String sql = "INSERT INTO users_courses (user_id, course_id)"
				+ " VALUES (?, ?) ON DUPLICATE KEY UPDATE user_id=user_id";
		jdbcTemplate.update(sql, userId, courseId);

		return true;
	}

	@Override
	public boolean isUserinCourse(int userId, int courseId) {
		String sql = "SELECT COUNT(*) FROM users_courses WHERE user_id=? and course_id=?";
		Integer count = jdbcTemplate.queryForObject(sql, new Object[] { userId, courseId }, Integer.class);
		return count != 0;
	}

}
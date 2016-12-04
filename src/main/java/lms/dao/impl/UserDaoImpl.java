package lms.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;

import lms.dao.UserDAO;

@Repository
public class UserDaoImpl implements UserDAO {

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

}
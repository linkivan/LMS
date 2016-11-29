package lms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;

import lms.dao.UserDao;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	private static final String SQL_USERS_UPDATE_LOCKED = "UPDATE USER SET account_non_locked = ? WHERE username = ?";
	private static final String SQL_USERS_COUNT = "SELECT count(*) FROM USER WHERE username = ?";

	private static final String SQL_USER_ATTEMPTS_GET = "SELECT login_attempts FROM USER WHERE username = ?";
	private static final String SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS = "UPDATE USER SET login_attempts = attempts + 1 WHERE username = ?";
	private static final String SQL_USER_ATTEMPTS_RESET_ATTEMPTS = "UPDATE USER SET login_attempts = 0 WHERE username = ?";

	private static final int MAX_ATTEMPTS = 3;

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public void updateFailAttempts(String username) {

		Integer userAttempts = getUserAttempts(username);
		if (userAttempts != null) {
			if (isUserExists(username)) {
				// update attempts count, +1
				getJdbcTemplate().update(SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS, username);
			}

			if (userAttempts + 1 >= MAX_ATTEMPTS) {
				// locked user
				getJdbcTemplate().update(SQL_USERS_UPDATE_LOCKED, new Object[] { false, username });
				// throw exception
				throw new LockedException("User Account is locked!");
			}
		}
	}

	@Override
	public Integer getUserAttempts(String username) {
		try {
			int userAttempts = getJdbcTemplate().queryForObject(SQL_USER_ATTEMPTS_GET, new Object[] { username },
					Integer.class);
			return userAttempts;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void resetFailAttempts(String username) {

		getJdbcTemplate().update(SQL_USER_ATTEMPTS_RESET_ATTEMPTS, new Object[] { username });

	}

	private boolean isUserExists(String username) {

		boolean result = false;

		int count = getJdbcTemplate().queryForObject(SQL_USERS_COUNT, new Object[] { username }, Integer.class);
		if (count > 0) {
			result = true;
		}

		return result;
	}

}
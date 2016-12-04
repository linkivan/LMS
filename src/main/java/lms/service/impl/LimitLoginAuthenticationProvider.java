package lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import lms.dao.UserDAO;

public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	UserDAO userDetailsDAO;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {

			Authentication auth = super.authenticate(authentication);

			// if reach here, means login success, else exception will be thrown
			// reset the user_attempts
			userDetailsDAO.resetFailAttempts(authentication.getName());

			return auth;

		} catch (BadCredentialsException e) {

			userDetailsDAO.updateFailAttempts(authentication.getName());
			throw e;

		} catch (LockedException e) {

			String error = "";
			Integer userAttempts = userDetailsDAO.getUserAttempts(authentication.getName());

			error = "Have attempted " + userAttempts + " times! User account is locked! Please contact admin!";

			throw new LockedException(error);
		}

	}

	/**
	 * @return the userDetailsDao
	 */
	public UserDAO getUserDetailsDAO() {
		return userDetailsDAO;
	}

	/**
	 * @param userDetailsDao
	 *            the userDetailsDao to set
	 */
	public void setUserDetailsDAO(UserDAO userDetailsDAO) {
		this.userDetailsDAO = userDetailsDAO;
	}

}
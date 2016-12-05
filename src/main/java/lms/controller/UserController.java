package lms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lms.model.CourseModel;
import lms.model.UIMenu;
import lms.model.UIUserModel;
import lms.model.UserModel;
import lms.service.CourseService;
import lms.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/course/{id}/people", method = RequestMethod.GET)
	public ModelAndView coursePeoplePage(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView();
		List<UIUserModel> instructors = userService.getInstructorsOfCourse(id);
		List<UIUserModel> students = userService.getStudentsOfCourse(id);
		CourseModel course = courseService.getCourseById(id);
		model.addObject("uiMenu", new UIMenu(course.getCode(), 2, true));
		model.addObject("course", course);
		model.addObject("instructors", instructors);
		model.addObject("students", students);
		model.setViewName("coursePeople");
		return model;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/course/{id}/people/new", method = RequestMethod.GET)
	public ModelAndView courseAddPeoplePage(@PathVariable("id") int id, String username) {
		ModelAndView model = new ModelAndView();
		model.addObject("id", id);

		UIUserModel user = userService.getUserByUsername(username);
		if (user != null && userService.isUserinCourse(user.getId(), id)) {
			model.addObject("msg", username + " is already in this course!");
		}
		CourseModel course = courseService.getCourseById(id);
		model.addObject("uiMenu", new UIMenu(course.getCode(), 2, true));
		model.addObject("course", course);
		model.addObject("user", user);
		model.setViewName("courseAddPeople");
		return model;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/course/{id}/people", method = RequestMethod.POST)
	public String courseAddPeoplePage(@PathVariable("id") int id, int userId) {
		userService.addUserToCourse(userId, id);
		return "redirect:/course/" + id + "/people";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/course/{id}/people/{userid}", method = RequestMethod.DELETE)
	public String courseDeletePeople(@PathVariable("id") int id, @PathVariable("userid") int userId) {
		userService.deleteUserOfCourse(userId, id);
		return "redirect:/course/" + id + "/people";
	}
}
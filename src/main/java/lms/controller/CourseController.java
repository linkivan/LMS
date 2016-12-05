package lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import lms.model.CourseModel;
import lms.model.UserModel;
import lms.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public ModelAndView coursesPage(String code, String semester, Authentication authentication) {
		ModelAndView model = new ModelAndView();
		List<CourseModel> courses;
		if ("[ROLE_ADMIN]".equals(authentication.getAuthorities().toString())) {
			courses = courseService.getCourseByCodeAndSemester(code, semester);
		} else {
			courses = courseService.getCoursesByUserId(((UserModel) authentication.getPrincipal()).getId());
		}
		model.addObject("courses", courses);
		model.setViewName("courses");
		return model;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/courses/new", method = RequestMethod.GET)
	public ModelAndView coursesForm() {
		ModelAndView model = new ModelAndView();
		model.setViewName("coursesForm");
		return model;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/courses", method = RequestMethod.POST)
	public String addCourses(CourseModel course) {
		int id = courseService.addCourse(course);
		return "redirect:/course/" + id;

	}

	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
	public ModelAndView coursePage(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView();
		CourseModel course = courseService.getCourseById(id);
		model.addObject("course", course);
		model.setViewName("courseInfo");
		return model;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
	public String deleteCourse(@PathVariable("id") int id) {
		courseService.deleteCourse(id);
		return "redirect:/courses/";

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/course/{id}/info", method = RequestMethod.GET)
	public ModelAndView courseModifyForm(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView();
		CourseModel course = courseService.getCourseById(id);
		model.addObject("course", course);
		model.setViewName("courseInfoForm");
		return model;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
	public String modifyCourse(@PathVariable("id") int id, CourseModel course) {
		course.setId(id);
		courseService.modifyCourse(course);
		return "redirect:/course/" + id;

	}

}
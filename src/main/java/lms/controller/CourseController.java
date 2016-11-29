package lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import lms.model.Course;
import lms.service.CourseService;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView coursesPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("coursesForm");
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public RedirectView addCourses(Course course) {

		courseService.addCourse(course);
		return new RedirectView("courses");

	}
}
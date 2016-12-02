package lms.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import lms.model.Course;
import lms.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public ModelAndView coursesPage(String code, String semester) {
		ModelAndView model = new ModelAndView();
		List<Course> courses = courseService.getCourseByCodeAndSemester(code, semester);
		model.addObject("courses", courses);
		model.setViewName("courses");
		return model;
	}

	@RequestMapping(value = "/courses/new", method = RequestMethod.GET)
	public ModelAndView coursesForm() {
		ModelAndView model = new ModelAndView();
		model.setViewName("coursesForm");
		return model;
	}

	@RequestMapping(value = "/courses", method = RequestMethod.POST)
	public String addCourses(Course course) {

		int id = courseService.addCourse(course);
		return "redirect:/course/" + id;

	}

	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
	public ModelAndView coursePage(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView();
		model.addObject("id", id);
		model.setViewName("course");
		return model;

	}

	@RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteCourse(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView();
		List<Course> courses = Collections.emptyList();
		model.addObject("courses", courses);
		model.addObject("msg", "Coures deleted!");
		model.setViewName("courses");
		return model;

	}

}
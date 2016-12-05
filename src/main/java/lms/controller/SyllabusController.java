package lms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lms.model.AssignmentModel;
import lms.model.CourseModel;
import lms.model.FileModel;
import lms.model.UIMenu;
import lms.model.UIUserModel;
import lms.model.UserModel;
import lms.service.CourseService;
import lms.service.FileService;
import lms.service.UserService;

@Controller
@RequestMapping(value = "/course/{courseId}")
public class SyllabusController {
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FileService fileService;

	@RequestMapping(value = { "/syllabus" }, method = RequestMethod.GET)
	public ModelAndView syllabusPage(@PathVariable("courseId") int courseId) {
		ModelAndView model = new ModelAndView();
		CourseModel course = courseService.getCourseById(courseId);
		FileModel syllabus = fileService.getSyllabusPathByCourseId(courseId);
		model.addObject("uiMenu", new UIMenu(course.getCode(), 5, true));
		model.addObject("syllabus", syllabus);
		model.addObject("course", course);
		model.setViewName("syllabus");
		return model;
	}

	@RequestMapping(value = { "/syllabus" }, method = RequestMethod.POST)
	public String uploadSyllabus(@PathVariable("courseId") int courseId, @RequestParam CommonsMultipartFile fileUpload,
			Authentication authentication) {
		ModelAndView model = new ModelAndView();
		CourseModel course = courseService.getCourseById(courseId);
		FileItem syllabus = fileUpload.getFileItem();
		fileService.createSyllabus(syllabus, course, authentication.getName());
		return "redirect:/course/" + courseId + "/syllabus";
	}
}
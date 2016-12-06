package lms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lms.model.CourseModel;
import lms.model.FileModel;
import lms.model.UIMenu;
import lms.service.CourseService;
import lms.service.FileService;

@Controller
@RequestMapping(value = "/course/{courseId}")
public class SyllabusController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = { "/syllabus" }, method = RequestMethod.GET)
	public ModelAndView syllabusPage(@PathVariable("courseId") int courseId) {
		ModelAndView model = new ModelAndView();
		CourseModel  course = courseService.getCourseById(courseId);
		FileModel    syllabus = fileService.getSyllabusByCourseId(courseId);
		
		model.addObject("uiMenu", new UIMenu(course.getCode(), 5, true));
		model.addObject("syllabus", syllabus);
		model.addObject("course", course);
		model.setViewName("syllabus");
		
		return model;
	}

	@RequestMapping(value = { "/syllabus" }, method = RequestMethod.POST)
	public String uploadSyllabus(HttpServletRequest request,
								 HttpServletResponse response,
								 @PathVariable("courseId") int courseId,
								 @RequestParam CommonsMultipartFile fileUpload,
								 Authentication authentication) throws Exception {
		ModelAndView model = new ModelAndView();
		CourseModel  course = courseService.getCourseById(courseId);
		FileItem     syllabus = fileUpload.getFileItem();
		
		fileService.createSyllabus(syllabus, course, authentication.getName());
		
		return "redirect:/course/" + courseId + "/syllabus";
	}
}
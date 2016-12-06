package lms.controller;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import lms.model.AssignResponseModel;
import lms.model.AssignmentModel;
import lms.model.CourseModel;
import lms.model.FileModel;
import lms.model.UIMenu;
import lms.model.UserModel;
import lms.service.AssignmentService;
import lms.service.CourseService;
import lms.service.FileService;
import lms.service.GradeService;

@Controller
@RequestMapping(value = "/course/{courseId}")
public class AssignmentController {
	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FileService fileService;
	@Autowired
	private GradeService gradeService;

	@RequestMapping(value = "/assignments", method = RequestMethod.GET)
	public ModelAndView assignmentsPage(@PathVariable("courseId") int courseId) {
		ModelAndView model = new ModelAndView();
		List<AssignmentModel> assignments = assignmentService.getAssignmentByCourseId(courseId);
		CourseModel course = courseService.getCourseById(courseId);
		model.addObject("uiMenu", new UIMenu(course.getCode(), 2, true));
		model.addObject("course", course);
		model.addObject("assignments", assignments);
		model.setViewName("assignments");
		return model;
	}

	@Secured("ROLE_INSTR")
	@RequestMapping(value = "/assignments/new", method = RequestMethod.GET)
	public ModelAndView assignmentForm(@PathVariable("courseId") int courseId) {

		ModelAndView model = new ModelAndView();
		CourseModel course = courseService.getCourseById(courseId);
		model.addObject("uiMenu", new UIMenu(course.getCode(), 2, true));
		model.addObject("course", course);
		model.setViewName("assignmentForm");
		return model;
	}

	@Secured("ROLE_INSTR")
	@RequestMapping(value = "/assignments", method = RequestMethod.POST)
	public String addAssignment(@PathVariable("courseId") int courseId, AssignmentModel assignment,
			@RequestParam CommonsMultipartFile fileUpload, Authentication authentication) {
		assignment.setCourseId(courseId);
		int id = assignmentService.addAssignment(assignment, fileUpload.getFileItem());
		return "redirect:/course/" + courseId + "/assignment/" + id;
	}

	@RequestMapping(value = "/assignment/{id}", method = RequestMethod.GET)
	public ModelAndView assignmentPage(@PathVariable("courseId") int courseId, @PathVariable("id") int id) {
		ModelAndView model = new ModelAndView();
		AssignmentModel assignment = assignmentService.getAssignmentById(id);
		FileModel fileModel = fileService.getFileByFileId(assignment.getFileId());
		CourseModel course = courseService.getCourseById(courseId);
		model.addObject("uiMenu", new UIMenu(course.getCode(), 2, true));
		model.addObject("course", course);
		model.addObject("id", id);
		model.addObject("fileModel", fileModel);
		model.addObject("assignment", assignment);
		model.setViewName("assignment");
		return model;
	}

	@Secured("ROLE_INSTR")
	@RequestMapping(value = "/assignment/{id}", method = RequestMethod.DELETE)
	public String deleteAssignment(@PathVariable("courseId") int courseId, @PathVariable("id") int id) {
		assignmentService.deleteAssignment(id);
		return "redirect:/course/" + courseId + "/assignments/";
	}

	@Secured("ROLE_INSTR")
	@RequestMapping(value = "/assignment/{id}", method = RequestMethod.PUT)
	public String modifyAssignment(@PathVariable("courseId") int courseId, @PathVariable("id") int id,
			AssignmentModel assignment) {
		assignment.setId(id);
		assignment.setCourseId(courseId);
		assignmentService.modifyAssignment(assignment);
		return "redirect:/course/" + courseId + "/assignment/" + id;
	}
	/*
	@Secured("ROLE_STU")
	@RequestMapping(value = "/assignment/{id}", method = RequestMethod.GET)
	public String submitResponsePage(@PathVariable("id") int assignmentId, Authentication authentication){
		ModelAndView model = new ModelAndView();
		
		return null;
	}
	
	
	/*
	@Secured("ROLE_STU")
	@RequestMapping(value = "/assignment/{id}", method = RequestMethod.POST)
	public String submitAssignResponse(@PathVariable("id") int assignmentId,
			@RequestParam CommonsMultipartFile fileUpload, Authentication authentication) {

		AssignmentModel assignment = assignmentService.getAssignmentById(assignmentId);
		AssignResponseModel assignRes = new AssignResponseModel();
		CourseModel course = courseService.getCourseById(assignment.getCourseId());
		FileItem response = fileUpload.getFileItem();
		int assignResFileId = fileService.createAssignmentResponse(response, course, assignment.getName(),
				authentication.getName());
		int userId = ((UserModel) authentication.getPrincipal()).getId();
		assignRes.setFileId(assignResFileId);
		assignRes.setUserId(userId);
		assignmentService.submitAssignResponse(assignRes);

		return "redirect:/course/" + course.getId() + "/assignment/" + assignmentId;
	}*/

}
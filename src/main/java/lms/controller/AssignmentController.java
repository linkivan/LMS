package lms.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("@userService.isCurrentUserinCourse(authentication, #courseId)")
public class AssignmentController {
	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FileService fileService;
	@Autowired
	private GradeService gradeService;

	@Secured({ "ROLE_INSTR", "ROLE_STU" })
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
	public ModelAndView assignmentForm(@PathVariable("courseId") int courseId, Authentication authentication) {

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
		return "redirect:/course/" + courseId + "/assignments";
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
	@RequestMapping(value = "/assignment/{id}", method = RequestMethod.POST)
	public String modifyAssignment(@PathVariable("courseId") int courseId, @PathVariable("id") int id,
			AssignmentModel assignment, @RequestParam CommonsMultipartFile fileUpload) {
		assignment.setId(id);
		assignment.setCourseId(courseId);
		assignmentService.modifyAssignment(assignment, fileUpload.getFileItem());
		return "redirect:/course/" + courseId + "/assignments";
	}

	@Secured("ROLE_STU")
	@RequestMapping(value = "/assignment/{id}/response", method = RequestMethod.GET)
	public ModelAndView submitResponsePage(@PathVariable("courseId") int courseId, @PathVariable("id") int assignmentId,
			Authentication authentication) {
		ModelAndView model = new ModelAndView();
		CourseModel course = courseService.getCourseById(courseId);
		AssignmentModel assignment = assignmentService.getAssignmentById(assignmentId);
		AssignResponseModel r = gradeService.getByUserIdAndAssignId(((UserModel) authentication.getPrincipal()).getId(),
				assignmentId);
		FileModel file = null;
		if (r != null) {
			file = fileService.getFileByFileId(r.getFileId());
		}
		model.addObject("uiMenu", new UIMenu(course.getCode(), 2, true));
		model.addObject("course", course);
		model.addObject("file", file);
		model.addObject("assignment", assignment);
		model.addObject("assignmentId", assignmentId);
		model.setViewName("assignmentResponseForm");
		return model;
	}

	@Secured("ROLE_STU")

	@RequestMapping(value = "/assignment/{id}/response", method = RequestMethod.POST)
	public String submitAssignResponse(@PathVariable("id") int assignmentId, @PathVariable("courseId") int courseId,
			@RequestParam CommonsMultipartFile fileUpload, Authentication authentication,
			AssignResponseModel assignRes) {
		FileItem responseFile = fileUpload.getFileItem();
		assignRes.setAssignmentId(assignmentId);
		assignRes.setCourseId(courseId);
		assignRes.setUserId(((UserModel) authentication.getPrincipal()).getId());
		assignRes.setSubmitTime(new Timestamp(new Date().getTime()));
		assignmentService.submitAssignResponse(assignRes, responseFile);
		return "redirect:/course/" + courseId + "/assignments/";
	}

}
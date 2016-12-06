package lms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import lms.model.UIGradeBookModel;
import lms.model.UIMenu;
import lms.model.UIUserModel;
import lms.model.UserModel;
import lms.service.AssignmentService;
import lms.service.CourseService;
import lms.service.FileService;
import lms.service.GradeService;
import lms.service.UserService;

@Controller
@RequestMapping(value = "/course/{courseId}")
public class GradeController {
	@Autowired
	private GradeService gradeService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FileService fileService;
	@Autowired
	private UserService userService;
	@Autowired
	private AssignmentService assignmentService;

	@Secured({ "ROLE_INSTR", "ROLE_STU" })
	@RequestMapping(value = "/grades", method = RequestMethod.GET)
	public ModelAndView gradesPage(@PathVariable("courseId") int courseId, Authentication authentication) {
		if ("[ROLE_INSTR]".equals(authentication.getAuthorities().toString())) {
			ModelAndView model = new ModelAndView();
			List<AssignmentModel> assignments = assignmentService.getAssignmentByCourseId(courseId);
			List<UIGradeBookModel> grades = new ArrayList<UIGradeBookModel>();
			List<UIUserModel> students = userService.getStudentsOfCourse(courseId);
			for (UIUserModel student : students) {
				UIGradeBookModel gradeBook = new UIGradeBookModel();
				gradeBook.setStudent(student);
				Map<Integer, AssignResponseModel> gradeLine = new HashMap<Integer, AssignResponseModel>();
				List<AssignResponseModel> responses = gradeService.getResponsesByUserIdAndCourseId(student.getId(),
						courseId);
				for (AssignResponseModel response : responses) {
					gradeLine.put(response.getAssignmentId(), response);
				}
				gradeBook.setGrades(gradeLine);
				grades.add(gradeBook);
			}
			CourseModel course = courseService.getCourseById(courseId);
			model.addObject("uiMenu", new UIMenu(course.getCode(), 3, true));
			model.addObject("course", course);
			model.addObject("grades", grades);
			model.addObject("assignments", assignments);
			model.setViewName("grades");
			return model;
		} else {
			ModelAndView model = new ModelAndView();
			List<AssignResponseModel> assignReses = gradeService
					.getResponsesByUserIdAndCourseId(((UserModel) authentication.getPrincipal()).getId(), courseId);
			CourseModel course = courseService.getCourseById(courseId);
			model.addObject("uiMenu", new UIMenu(course.getCode(), 3, true));
			model.addObject("course", course);
			model.addObject("assignReses", assignReses);

			model.setViewName("assignReses");
			return model;
		}
	}

	// @RequestMapping(value = "/grades/{userId}", method = RequestMethod.GET)
	// public ModelAndView assignGradesPage(@PathVariable("courseId") int
	// courseId, @PathVariable("userId") int userId) {
	// ModelAndView model = new ModelAndView();
	// List<AssignResponseModel> assignReses =
	// gradeService.getResponsesByUserIdAndCourseId(userId, courseId);
	// model.addObject("assignReses", assignReses);
	// model.setViewName("assignReses");
	// return model;
	// }
	@Secured({ "ROLE_INTR" })
	@RequestMapping(value = "/assignment/{assignmentId}/student/{studentid}", method = RequestMethod.GET)
	public ModelAndView assignGradesPage(@PathVariable("courseId") int courseId,
			@PathVariable("assignmentId") int assignmentId, @PathVariable("studentid") int studentid) {

		ModelAndView model = new ModelAndView();
		CourseModel course = courseService.getCourseById(courseId);
		AssignResponseModel response = gradeService.getByUserIdAndAssignId(studentid, assignmentId);
		FileModel file = fileService.getPathByFileId(response.getFileId());
		AssignmentModel assignment = assignmentService.getAssignmentById(assignmentId);
		model.addObject("uiMenu", new UIMenu(course.getCode(), 3, true));
		model.addObject("course", course);
		model.addObject("assignmentId", assignmentId);
		model.addObject("studentid", studentid);
		model.addObject("file", file);
		model.addObject("response", response);
		model.addObject("assignment", assignment);
		model.setViewName("assignmentGrade");
		// need file download
		return model;
	}

	@Secured({ "ROLE_INTR" })
	@RequestMapping(value = "/assignment/{assignmentId}/student/{studentid}", method = RequestMethod.POST)
	public String assignGrades(@PathVariable("courseId") int courseId, @PathVariable("assignmentId") int assignmentId,
			@PathVariable("studentid") int studentid, AssignResponseModel grademodel) {
		grademodel.setAssignmentId(assignmentId);
		grademodel.setUserId(studentid);
		gradeService.gradeAssignResponse(grademodel);
		return "redirect:/course/" + courseId + "/grades";
	}
}
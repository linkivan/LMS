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
//import lms.model.UIGradeBookModel;
import lms.model.UIMenu;
import lms.model.UIUserModel;
import lms.service.AssignmentService;
import lms.service.CourseService;
import lms.service.FileService;
import lms.service.UserService;

@Controller
@RequestMapping(value = "/course/{courseId}")
public class GradeController {
	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FileService fileService;
	@Autowired
	private UserService userService;

	// @RequestMapping(value = "/grades", method = RequestMethod.GET)
	// public ModelAndView gradesPage(@PathVariable("courseId") int courseId) {
	// // ModelAndView model = new ModelAndView();
	// // List<UIGradeBookModel> grades = new ArrayList<UIGradeBookModel>();
	// // List<UIUserModel> students =
	// // userService.getStudentsOfCourse(courseId);
	// // for (UIUserModel student : students) {
	// // UIGradeBookModel gradeBook = new UIGradeBookModel();
	// // gradeBook.setStudent(student);
	// // Map<String, AssignResponseModel> gradeLine = new HashMap<String,
	// // AssignResponseModel>();
	// // List<AssignResponseModel> responses =
	// // assignmentService.getResponsesByUserIdAndCourseId(student.getId(),
	// // courseId);
	// // for (AssignResponseModel response : responses) {
	// // gradeLine.put(response.getAssignmentId(), response);
	// // }
	// // }
	// // return model;
	// }

}
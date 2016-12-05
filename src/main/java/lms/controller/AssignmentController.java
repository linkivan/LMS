package lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import lms.model.Assignment;
import lms.service.AssignmentService;

@Controller
public class AssignmentController {
	@Autowired
	private AssignmentService assignmentService;

	@RequestMapping(value = "/assignments", method = RequestMethod.GET)
	public ModelAndView assignmentsPage(int courseId) {
		ModelAndView model = new ModelAndView();
		List<Assignment> assignments = assignmentService.getAssignmentByCourseId(courseId);
		model.addObject("assignments", assignments);
		model.setViewName("assignments");
		return model;
	}

	@RequestMapping(value = "/assignments/new", method = RequestMethod.GET)
	public ModelAndView assignmentForm() {
		ModelAndView model = new ModelAndView();
		model.setViewName("assignmentForm");
		return model;
	}

	@RequestMapping(value = "/assignments", method = RequestMethod.POST)
	public String addAssignment(Assignment assignment) {
		int id = assignmentService.addAssignment(assignment);
		return "redirect:/assignment/" + id;
	}

	@RequestMapping(value = "/assignment/{id}", method = RequestMethod.GET)
	public ModelAndView assignmentPage(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView();
		Assignment assignment = assignmentService.getAssignmentById(id);
		model.addObject("id", id);
		model.addObject("assignment", assignment);
		model.setViewName("assignment");
		return model;
	}

	@RequestMapping(value = "/assignment/{id}", method = RequestMethod.DELETE)
	public String deleteAssignment(@PathVariable("id") int id) {
		assignmentService.deleteAssignment(id);
		return "redirect:/assignments/";
	}

	@RequestMapping(value = "/assignment/{id}/info", method = RequestMethod.GET)
	public ModelAndView assignmentModifyForm(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView();
		Assignment assignment = assignmentService.getAssignmentById(id);
		model.addObject("assignment", assignment);
		model.setViewName("assignmentInfoForm");
		return model;
	}

	@RequestMapping(value = "/assignment/{id}", method = RequestMethod.PUT)
	public String modifyAssignment(@PathVariable("id") int id, Assignment assignment) {
		assignment.setId(id);
		assignmentService.modifyAssignment(assignment);
		return "return:/assignment/" + id;
	}
	
}
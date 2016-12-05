package lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.dao.AssignmentDAO;
import lms.model.AssignmentModel;
import lms.model.FileModel;
import lms.service.AssignmentService;


@Service
public class AssignmentServiceImpl implements AssignmentService {
	
	@Autowired
	private AssignmentDAO assignmentDAO;

	@Override
	public int addAssignment(AssignmentModel assignment) {
		
		return assignmentDAO.addAssignment(assignment);
	}

	@Override
	public boolean modifyAssignment(AssignmentModel assignment) {
		
		return assignmentDAO.modifyAssignment(assignment);
	}

	@Override
	public boolean deleteAssignment(int assignmentId) {
		
		return assignmentDAO.deleteAssignment(assignmentId);
	}

	@Override
	public AssignmentModel getAssignmentById(int assignmentId) {
		
		return assignmentDAO.getAssignmentById(assignmentId);
	}

	@Override
	public boolean submitAssignResponse(int courseId, int fileId, FileModel file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FileModel getAssignResponse(int courseId, int fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssignmentModel> getAssignmentByCourseId(int courseId) {
		// TODO Auto-generated method stub
		return assignmentDAO.getAssignmentByCourseId(courseId);
	}

}

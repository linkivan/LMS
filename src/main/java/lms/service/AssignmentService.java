package lms.service;

import java.util.List;

import lms.model.Assignment;
import lms.model.File;

public interface AssignmentService {
	public int addAssignment(Assignment assignment);
	
	public boolean modifyAssignment(Assignment assignment);
	
	public boolean deleteAssignment(int assignmentId);
	
	public Assignment getAssignmentById(int assignmentId);
	
	public List<Assignment> getAssignmentByCourseId(int courseId);
	
	public boolean submitAssignResponse(int fileId, int fileSize, File file);
	
	public File getAssignResponse(int courseId, int fileId);
}

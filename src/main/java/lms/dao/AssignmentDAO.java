package lms.dao;

import java.util.List;

import lms.model.AssignmentModel;
import lms.model.File;

public interface AssignmentDAO {
	public int addAssignment(AssignmentModel assignment);

	public boolean modifyAssignment(AssignmentModel assignment);

	public boolean deleteAssignment(int assignmentId);

	public AssignmentModel getAssignmentById(int assignmentId);
	
	public List<AssignmentModel> getAssignmentByCourseId(int courseId);

	public boolean submitAssignResponse(int fileId, int fileSize, File file);
}

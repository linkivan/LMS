package lms.service;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import lms.model.AssignmentModel;
import lms.model.FileModel;

public interface AssignmentService {
	public int addAssignment(AssignmentModel assignment, FileItem assignmentFile);

	public boolean modifyAssignment(AssignmentModel assignment);

	public boolean deleteAssignment(int assignmentId);

	public AssignmentModel getAssignmentById(int assignmentId);

	public List<AssignmentModel> getAssignmentByCourseId(int courseId);

	public boolean submitAssignResponse(int fileId, int fileSize, FileModel file);

	public FileModel getAssignResponse(int courseId, int fileId);
}

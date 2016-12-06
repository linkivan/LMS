package lms.dao;

import java.util.List;

import lms.model.AssignResponseModel;

public interface AssignResponseDAO {
	public boolean addAssignResponse(AssignResponseModel assignRes);
	
	public boolean modifyAssignResponse(AssignResponseModel assignRes);
	
	public boolean deleteAssignResponse(int userId, int assignmentId);
	
	public AssignResponseModel getByUserIdAndAssignId(int userId, int assignmentId);
	
	public List<AssignResponseModel> getByUserIdAndCourseId(int userId, int courseId);
	
}

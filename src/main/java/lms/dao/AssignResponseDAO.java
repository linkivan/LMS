package lms.dao;

import java.util.List;

import lms.model.AssignResponseModel;

public interface AssignResponseDAO {
	public int addAssignResponse(AssignResponseModel assignRes, int fileId);
	
	public boolean updateAssignResponseById(int assignResId);
	
	public boolean deleteAssignResponseById(int assignResId);
	
	public AssignResponseModel getByUserIdAndAssignId(int userId, int assignId);
	
	public List<AssignResponseModel> getByUserId(int userId);
	
	public List<AssignResponseModel> getByCourseId(int courseId);
}

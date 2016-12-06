package lms.dao;

import java.util.List;

import lms.model.AssignResponseModel;

public interface GradeDAO {
	public boolean gradeAssignResponse(AssignResponseModel assignRes);

	public AssignResponseModel getByUserIdAndAssignId(int userId, int assignId);

	public List<AssignResponseModel> getResponsesByUserIdAndCourseId(int userId, int courseId);
}

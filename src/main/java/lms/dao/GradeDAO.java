package lms.dao;

import java.util.List;

import lms.model.AssignResponseModel;

public interface GradeDAO {
	public boolean gradeAssignResponse(AssignResponseModel assignRes);

	public List<AssignResponseModel> getResponsesByUserIdAndCourseId(int userId, int courseId);
}

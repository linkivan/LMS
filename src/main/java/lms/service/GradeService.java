package lms.service;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import lms.model.AssignResponseModel;
import lms.model.AssignmentModel;
import lms.model.FileModel;

public interface GradeService {
	public boolean gradeAssignResponse(AssignResponseModel assignRes);

	public AssignResponseModel getByUserIdAndAssignId(int userId, int assignId);

	public List<AssignResponseModel> getResponsesByUserIdAndCourseId(int userId, int courseId);

}

package lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.dao.GradeDAO;
import lms.model.AssignResponseModel;
import lms.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService {
	@Autowired
	private GradeDAO gradeDAO;

	@Override
	public boolean gradeAssignResponse(AssignResponseModel assignRes) {
		return gradeDAO.gradeAssignResponse(assignRes);
	}

	@Override
	public AssignResponseModel getByUserIdAndAssignId(int userId, int assignId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssignResponseModel> getResponsesByUserIdAndCourseId(int userId, int courseId) {

		return gradeDAO.getResponsesByUserIdAndCourseId(userId, courseId);
	}

}

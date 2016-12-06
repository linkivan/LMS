package lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.dao.AssignResponseDAO;
import lms.dao.GradeDAO;
import lms.model.AssignResponseModel;
import lms.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService {
	@Autowired
	private GradeDAO gradeDAO;
	@Autowired
	private AssignResponseDAO assignResponseDAO;

	@Override
	public boolean gradeAssignResponse(AssignResponseModel assignRes) {
		return gradeDAO.gradeAssignResponse(assignRes);
	}

	@Override
	public AssignResponseModel getByUserIdAndAssignId(int userId, int assignId) {
		return assignResponseDAO.getByUserIdAndAssignId(userId, assignId);
	}

	@Override
	public List<AssignResponseModel> getResponsesByUserIdAndCourseId(int userId, int courseId) {

		return gradeDAO.getResponsesByUserIdAndCourseId(userId, courseId);
	}

}

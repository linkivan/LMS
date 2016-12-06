package lms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lms.dao.GradeDAO;
import lms.model.AssignResponseModel;

@Repository
public class GradeDAOImpl implements GradeDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean gradeAssignResponse(AssignResponseModel assignRes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AssignResponseModel getByUserIdAndAssignId(int userId, int assignId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssignResponseModel> getResponsesByUserIdAndCourseId(int userId, int courseId) {
		String sql = "SELECT * FROM assignment_response WHERE user_id = ? AND course_id = ? ORDER BY assignment_id ASC";
		
		List<AssignResponseModel> assignReses = jdbcTemplate.query(sql, new Object[] { userId, courseId },
				new RowMapper<AssignResponseModel>() {

					@Override
					public AssignResponseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						AssignResponseModel assignRes = new AssignResponseModel();
						
						assignRes.setUserId(rs.getInt("user_id"));
						assignRes.setAssignmentId(rs.getInt("assignment_id"));
						assignRes.setCourseId(rs.getInt("course_id"));
						assignRes.setFileId(rs.getInt("file_id"));
						assignRes.setSubmitTime(rs.getTimestamp("submit_time"));
						assignRes.setGrade(rs.getInt("grade"));
						
						return assignRes;
					}

				});

		return assignReses;
	}

}

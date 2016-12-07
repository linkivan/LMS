package lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lms.dao.AssignResponseDAO;
import lms.model.AssignResponseModel;

@Repository
public class AssignResponseDAOImpl implements AssignResponseDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean addAssignResponse(final AssignResponseModel assignRes) {
		if (!isExistByUserIdAndAssignId(assignRes.getUserId(), assignRes.getAssignmentId())) {
			final String sql = "INSERT INTO assignment_response (user_id, assignment_id, course_id, file_id, submit_time, grade)"
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql, new String[] { "user_id", "assignment_id" });

					ps.setInt(1, assignRes.getUserId());
					ps.setInt(2, assignRes.getAssignmentId());
					ps.setInt(3, assignRes.getCourseId());
					ps.setInt(4, assignRes.getFileId());
					ps.setTimestamp(5, assignRes.getSubmitTime());
					ps.setInt(6, assignRes.getGrade());

					return ps;
				}

			}, keyHolder);

			return true;
		} else {
			final String sql = "UPDATE assignment_response set  file_id = ? WHERE  user_id =? and assignment_id =?";

			jdbcTemplate.update(sql, assignRes.getFileId(), assignRes.getUserId(), assignRes.getAssignmentId());
			return true;
		}
	}

	@Override
	public boolean modifyAssignResponse(AssignResponseModel assignRes) {
		String sql = "UPDATE assignment_response SET user_id = ?, assignment_id = ?, course_id = ?, file_id = ?, submit_time = ?, grade = ?";
		jdbcTemplate.update(sql, assignRes.getUserId(), assignRes.getAssignmentId(), assignRes.getCourseId(),
				assignRes.getFileId(), assignRes.getSubmitTime(), assignRes.getGrade());
		return true;
	}

	@Override
	public boolean deleteAssignResponse(int userId, int assignmentId) {
		String sql = "DELETE FROM assignment_response WHERE user_id = ? AND assignment_id = ?";
		this.jdbcTemplate.update(sql, userId, assignmentId);
		return true;
	}

	@Override
	public AssignResponseModel getByUserIdAndAssignId(int userId, int assignmentId) {
		String sql = "SELECT * FROM assignment_response WHERE user_id = ? AND assignment_id = ?";
		AssignResponseModel assignRes = null;
		if (isExistByUserIdAndAssignId(userId, assignmentId)) {
			assignRes = jdbcTemplate.queryForObject(sql, new Object[] { userId, assignmentId },
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
		}
		return assignRes;
	}

	private boolean isExistByUserIdAndAssignId(int userId, int assignmentId) {
		String sql = "SELECT count(*) FROM assignment_response WHERE user_id = ? AND assignment_id = ?";
		Integer count = jdbcTemplate.queryForObject(sql, new Object[] { userId, assignmentId }, Integer.class);
		return count != 0;
	}

	@Override
	public List<AssignResponseModel> getByUserIdAndCourseId(int userId, int courseId) {
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

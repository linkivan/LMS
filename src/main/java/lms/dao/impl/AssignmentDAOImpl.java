package lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lms.dao.AssignmentDAO;
import lms.model.Assignment;
import lms.model.File;

@Repository
public class AssignmentDAOImpl implements AssignmentDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addAssignment(final Assignment assignment) {
		final String sql = "INSERT INTO assignment (name, course_id, dueDate, status, totalScore)"
				+ " VALUES (?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
				ps.setString(1, assignment.getName());
				ps.setInt(2, assignment.getCourseId());
				ps.setString(3, assignment.getDueDate());
				ps.setString(4, assignment.getStatus());
				ps.setString(5, assignment.getTotalScore());
				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public boolean modifyAssignment(Assignment assignment) {
		String sql = "UPDATE assignment SET name = ?, dueDate = ?, status = ?, totalScore = ?, weight = ?, courese_id = ?, file_id = ?"
				+ "WHERE id = ?";
		jdbcTemplate.update(sql, assignment.getName(), assignment.getDueDate(), assignment.getStatus(),
				assignment.getTotalScore(), assignment.getWeight(), assignment.getCourseId(), assignment.getFileId(),
				assignment.getId());
		return true;
	}

	@Override
	public boolean deleteAssignment(int assignmentId) {
		String sql = "DELETE FROM assignment WHERE id = ?";
		this.jdbcTemplate.update(sql, assignmentId);
		return true;
	}

	@Override
	public Assignment getAssignmentById(int assignmentId) {
		String sql = "SELECT * FROM assignment WHERE id = ?";

		Assignment assignment = jdbcTemplate.queryForObject(sql, new Object[] { assignmentId },
				new RowMapper<Assignment>() {

					@Override
					public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
						Assignment assignment = new Assignment();

						assignment.setId(rs.getInt("id"));
						assignment.setName(rs.getString("name"));
						assignment.setDueDate(rs.getString("dueDate"));
						assignment.setStatus(rs.getString("status"));
						assignment.setTotalScore(rs.getString("totalScore"));
						assignment.setWeight(rs.getString("weight"));
						assignment.setCourseId(rs.getInt("course_id"));
						assignment.setFileId(rs.getInt("file_id"));

						return assignment;
					}

				});

		return assignment;
	}

	@Override
	public boolean submitAssignResponse(int fileId, int fileSize, File file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Assignment> getAssignmentByCourseId(int courseId) {
		String sql = "SELECT * FROM assignment WHERE course_id = ?";

		List<Assignment> assignments = jdbcTemplate.query(sql, new Object[] { courseId }, new RowMapper<Assignment>() {

			@Override
			public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Assignment assignment = new Assignment();
				
				assignment.setId(rs.getInt("id"));
				assignment.setName(rs.getString("name"));
				assignment.setDueDate(rs.getString("dueDate"));
				assignment.setStatus(rs.getString("status"));
				assignment.setTotalScore(rs.getString("totalScore"));
				assignment.setWeight(rs.getString("weight"));
				assignment.setCourseId(rs.getInt("course_id"));
				assignment.setFileId(rs.getInt("file_id"));
				
				return assignment;
			}
			
		});
		return assignments;
	}
}

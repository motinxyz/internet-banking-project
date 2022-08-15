package com.abcb.rse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.abcb.dto.IBankingRequestDTO;

public class PendingRequestsRSE implements ResultSetExtractor<List<IBankingRequestDTO>> {

	@Override
	public List<IBankingRequestDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {

		List<IBankingRequestDTO> requestsList = new ArrayList<>();

		while (rs.next()) {
			IBankingRequestDTO request = new IBankingRequestDTO();

			request.setUser_id(rs.getInt("user_id"));
			request.setAccount_number(rs.getString("account_number"));
			request.setName(rs.getString("name"));
			request.setFather_name(rs.getString("father_name"));
			request.setMother_name(rs.getString("mother_name"));
			request.setAddress(rs.getString("address"));
			request.setPhone_number(rs.getString("phone_number"));

			requestsList.add(request);
		}

		return requestsList;
	}

}

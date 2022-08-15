package com.abcb.rse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.abcb.dto.IBankingRequestDTO;

public class UserInfoRSE implements ResultSetExtractor<List<IBankingRequestDTO>> {

	@Override
	public List<IBankingRequestDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {

		List<IBankingRequestDTO> requestsList = new ArrayList<>();

		while (rs.next()) {
			IBankingRequestDTO request = new IBankingRequestDTO();

			request.setUser_id(rs.getInt("user_id"));
			request.setName(rs.getString("name"));
			request.setFather_name(rs.getString("father_name"));
			request.setMother_name(rs.getString("mother_name"));
			request.setAddress(rs.getString("address"));
			request.setEmail(rs.getString("email"));
			request.setPhone_number("phone_number");
//			request.setPassword(rs.getString("password"));

			requestsList.add(request);
		}

		return requestsList;
	}

}

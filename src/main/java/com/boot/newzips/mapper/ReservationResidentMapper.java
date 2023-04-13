package com.boot.newzips.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.ResidenceReservDTO;

@Mapper
public interface ReservationResidentMapper {

	public void insertResidentReserv(ResidenceReservDTO dto) throws Exception;
	
	public ResidenceReservDTO selectResidentReserv(String userId, String itemId) throws Exception;
	
	public void deleteResidentReserv(ResidenceReservDTO dto) throws Exception;
}
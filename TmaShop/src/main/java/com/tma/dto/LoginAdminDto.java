package com.tma.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginAdminDto {
	@NotEmpty
private String username;
	@NotEmpty
private String password ;
private Boolean remember = false ;
}

package com.smhrd.SpringBootEx02.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RepleForm {

	@NotEmpty(message = "내용은 필수항목입니다.")
	private String repleCon;
}

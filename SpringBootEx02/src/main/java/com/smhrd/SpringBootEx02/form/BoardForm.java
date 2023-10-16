package com.smhrd.SpringBootEx02.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BoardForm {

	@NotEmpty(message = "제목은 필수항목입니다.")
	@Size(max=200)
	private String boardTitle;
	
	@NotEmpty(message = "내용은 필수항목입니다.")
	private String boardCon;
	
}

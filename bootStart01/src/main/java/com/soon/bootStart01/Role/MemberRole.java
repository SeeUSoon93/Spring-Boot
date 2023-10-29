package com.soon.bootStart01.Role;

import lombok.Getter;

@Getter
public enum MemberRole {
	/*
	 * enum - 열거형을 나타내는 자바의 데이터 유형
	 * 		- 열거형은 상수들의 집합을 정의할 떄 사용함
	 * enum으로 ROLE을 설정하는 이유
	 * - 권한은 고정된 값이기 때문에 enum을 사용
	 * - 고정된 값을 관리하기 때문에 유지보수에 편리
	 * - 타입과 값 모두 안정성이 더욱 높아짐
	 */

	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");

	private MemberRole(String value) {
		this.value = value;
	}
	
	private String value;

}

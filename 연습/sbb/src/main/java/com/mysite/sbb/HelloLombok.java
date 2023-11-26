package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// final로 지정된 변수를 필요로 하는 생성자가 롬복에 의해 자동생성됨
@RequiredArgsConstructor
@Getter
public class HelloLombok {
	
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok("헬로",5);
		
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());
		
		
	}

}

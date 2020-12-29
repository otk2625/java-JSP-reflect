package app;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dto.JoinDto;
import dto.LoginDto;

public class ReflectApp {
	/*1번방식:Object dto 2번방식 : 제네릭*/
	static <T> void myReflect(T dto) {
		Method[] methods = dto.getClass().getMethods(); // 메서드를 동적으로 읽는다

		for (Method method : methods) {
			//System.out.println(method.getName()); // 메서드 이름 검색
			method.setAccessible(true);
			if(method.getName().equals("setPassword")) {
				System.out.println("존재");
				try {
					method.invoke(dto, "9876"); //메소드 접근방법
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}

		Field[] fs = dto.getClass().getDeclaredFields(); // 오브젝트만 변수로 받아주면 분석 가능
		
		for (Field f : fs) {
			
			f.setAccessible(true); // private 멤버에 접근하게 해주는 기법
			try {
//				if(f.getName().equals("password")) {
//					f.set(dto, "5678");  //필드 접근방법
//				}
				Object o = f.get(dto);
				System.out.println(o);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	public static void main(String[] args) {
		LoginDto loginDto = new LoginDto();
		loginDto.setUsername("ssar");
		loginDto.setPassword("1234");

		JoinDto joinDto = new JoinDto();
		joinDto.setUsername("ssar");
		joinDto.setPassword("1234");
		joinDto.setEmail("ssar@nate.com");
		
		ReflectApp.myReflect(loginDto); //필터 하나로 
	}

}

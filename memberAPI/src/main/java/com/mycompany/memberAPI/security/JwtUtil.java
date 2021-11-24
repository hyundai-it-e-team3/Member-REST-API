package com.mycompany.memberAPI.security;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {
	//비밀키
	private static final String secretKey = "12345";
	
	//JWT 생성
	public static String createToken(String memberId, String authority) {
		log.info("Run");
		
		String result = null;
		
		try {
			String token = Jwts.builder()
					//헤더 설정(토큰 타입, 알고리즘 종류)
					.setHeaderParam("typ", "JWT")
					.setHeaderParam("alg", "HS256")
					//토근의 유효기간 설정
					.setExpiration(new Date(new Date().getTime() + 1000*60*60*24)) //1000=1초
					//페이로드or크레임(토큰에 저장되는 데이터) 설정
					.claim("memberId", memberId)
					.claim("authority", authority)
					//서명 설정
					.signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))
					.compact();
			result = token;	
		} catch(Exception e) {		
		}
		
		return result;
	}
	
	//JWT 유효성 검사
	public static Claims validateToken(String token) {
		log.info("Run");
		
		Claims result = null;
		
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(secretKey.getBytes("UTF-8"))
					.parseClaimsJws(token) //파싱 예외 발생 구간
					.getBody();
			
			result = claims;
		} catch(Exception e) {
		}
		
		return result;
		
	}
	
	//JWT 정보 얻기
	public static String getMemberId(Claims claims) {
		log.info("Run");
		
		String memberId = claims.get("memberId", String.class);
		
		return memberId;
	}
	
	public static String getAuthority(Claims claims) {
		log.info("Run");
		
		return claims.get("authority", String.class);
	}
	
	//확인
//	public static void main(String[] args) throws Exception {
//		//토큰 생성
//		String memberId = "user";
//		String authority = "ROLE_USER";
//		String jwt = createToken(memberId, authority);
//		log.info(jwt);
//		
//		//토큰 유효성 검사
//		Claims claims = validateToken(jwt);
//		if(claims != null) {
//			log.info("유효한 토큰");
//			log.info("memberId: " + getMemberId(claims));
//			log.info("authority: " + getAuthority(claims));
//		} else {
//			log.info("유효하지 않은 토큰");
//		}
//	}
}

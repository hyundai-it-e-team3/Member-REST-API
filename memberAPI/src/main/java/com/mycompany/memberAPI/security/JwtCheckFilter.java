package com.mycompany.memberAPI.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtCheckFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("Run");
	
		//JWT 얻기
		String jwt = null;
		if(request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ")) { //Header Authorization으로 전달된 경우
			jwt = request.getHeader("Authorization").substring(7);
		} else if(request.getParameter("jwt") != null) { //QueryString으로 전달하는 경우
			//<img src="...?jwt=.../>
			jwt = request.getParameter("jwt");
		}
		log.info("jwt: " + jwt);
		
		//JWT 유효성 검사
		if(jwt != null) {
			Claims claims = JwtUtil.validateToken(jwt);
			if(claims != null) {
				log.info("유효한 토큰");
				//JWT에서 Claims 정보 얻기
				String memberId = JwtUtil.getMemberId(claims);
				String authority = JwtUtil.getAuthority(claims);
				log.info("memberId: " + memberId);
				log.info("authority: " + authority);
				//Spring Security 사용자 인증 확인
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberId, null, AuthorityUtils.createAuthorityList(authority));
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);
			}
		}
		
		//다음 필터 실행
		filterChain.doFilter(request, response);
	}

}

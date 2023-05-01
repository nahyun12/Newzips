package com.boot.newzips.mypage;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.account.AccountUserService;
import com.boot.newzips.account.UserSecurityService;
import com.boot.newzips.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/newzips")
public class MyPageUserController {
	
	@Resource
	AccountUserService accountUserService;
	
	@GetMapping("/mypage")
	public ModelAndView myPage(Principal principal) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		String userId = null;
		
		// 로그인 정보 존재하면 관심목록띄우고 아니면 로그인 진행
		try {
			userId = principal.getName();
			Optional<MemberDTO> _user = accountUserService.getUserById(userId);
			MemberDTO user = _user.get();
			mav.addObject("user", user);

		} catch (Exception e) {
			mav.setViewName("redirect:/newzips/login");
			return mav;
		}

		mav.setViewName("user/mypage_user");
		
		return mav;
		
	}
	
	@GetMapping("/checkPwd")
	public ModelAndView checkPwd(Principal principal) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		String userId = null;
		
		// 로그인 정보 존재하면 관심목록띄우고 아니면 로그인 진행
		try {
			userId = principal.getName();
			Optional<MemberDTO> _user = accountUserService.getUserById(userId);
			MemberDTO user = _user.get();
			mav.addObject("user", user);

		} catch (Exception e) {
			mav.setViewName("redirect:/newzips/login");
			return mav;
		}
				
		mav.addObject("userId",userId);
		mav.setViewName("user/checkPwd");
		
		return mav;
	}
	
	@PostMapping("/checkPwd")
	public ModelAndView checkPwd_ok(HttpServletRequest request,MemberDTO memberDTO) throws Exception {
	
		ModelAndView mav = new ModelAndView();
		String userId = memberDTO.getUserId();
		String userPwd = memberDTO.getUserPwd2();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("userPwd", userPwd);
		
		if(accountUserService.checkPassword(params)) {
			mav.setViewName("redirect:/newzips/mypage");
		
		}else {
			mav.addObject("msg","비밀번호가 일치하지 않습니다.");
		}
		
		System.out.println(memberDTO.getUserId());
		System.out.println(memberDTO.getUserAddr());
		
		mav.setViewName("user/checkPwd");
		
		return mav;
	}
	

}

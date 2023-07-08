package com.ssafy.trip.user.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.user.model.User;
import com.ssafy.trip.user.model.service.JwtService;
import com.ssafy.trip.user.model.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private JwtService jwtService;
	
	private UserService service;
	
	@Autowired
	public UserController(JwtService jwtService, UserService service) {
		super();
		this.jwtService = jwtService;
		this.service = service;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = service.login(user);
			if (loginUser != null) {
				//access, refresh token 발급
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserId());// key, data
				//refresh token db에 저장
				service.saveRefreshToken(user.getUserId(), refreshToken);
				//access-token, refresh-token, 성공 메시지를 담아서 client에게 보냄
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") String userid,
			HttpServletRequest request) {
		//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		//header에서 access-token을 뽑아내서 해당 토큰의 유효성 검사 후 사용 가능하다면 아래 코드 실행
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			//logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = service.userInfo(userid);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				//logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		//사용 불가능 토큰
		else {
			//logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			//db에 저장된 refreshtoken 삭제
			service.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			//logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		//logger.debug("token : {}, memberDto : {}", token, memberDto);
		//refresh 토큰의 유효성이 검증되었다면
		if (jwtService.checkToken(token)) {
			//userid를 통해서 db에 저장된 refreshToken을 가져와서 비교해서 같다면 아까 인증한 사람이므로
			//access token을 다시 발급한다.
			if (token.equals(service.getRefreshToken(user.getUserId()))) {
				String accessToken = jwtService.createAccessToken("userid", user.getUserId());
				//logger.debug("token : {}", accessToken);
				//logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			//logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/pass")
	public ResponseEntity<?> passCheck(@RequestBody User user) throws Exception{
		User u = service.userPassInfo(user.getUserId());
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(u.getUserPass().equals(user.getUserPass())) {
			resultMap.put("message", SUCCESS);
		}else {
			resultMap.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status) ;
	}

//	@PostMapping("/modify")
//	public String modify(@RequestBody User user, @RequestParam String changpw) {
//		return changpw;
//	}
	
//	@PostMapping("/login")
//	public String login(@ModelAttribute User user, Model model, RedirectAttributes redirAttr, HttpSession session) throws SQLException {
//		User u = service.getUser(user);
//		if(u != null) {
//			session.setAttribute("userInfo", u);
//			redirAttr.addFlashAttribute("msg", "환영");
//			return "redirect:/";
//		}else {
//			model.addAttribute("msg", "로그인 실패!!!");
//			return "index";
//		}
//	}
//	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody User user, HttpServletRequest request) throws SQLException{
		int result = service.registUser(user);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(result==1) {
			//회원 가입 성공
			status = HttpStatus.ACCEPTED;
			resultMap.put("message", SUCCESS);
		}else {
			//회원 가입 실패
			status = HttpStatus.UNAUTHORIZED;
			resultMap.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//아이디 중복 검사를 위한 메소드
	@GetMapping("/check/{userid}")
	public User checkId(@PathVariable("userid") String userid) throws Exception{
		return service.userInfo(userid);
	}
	
	//비밀번호 변경
	@PostMapping("/changePass")
	public ResponseEntity<Map<String, Object>> changePass(@RequestBody User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			int result = service.changePass(user);
			if (result == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//이름, 이메일 변경
	@PostMapping("/modify")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			int result = service.updateUser(user);
			if (result == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
//	@PostMapping("/changePass")
//	public String changePass(@RequestParam String userId, @RequestParam String userPass, @RequestParam String changepw1, Model model, RedirectAttributes redirAttr, HttpSession session) throws SQLException{
//		User u = service.getUser(User.builder().userId(userId).userPass(userPass).build());
//		if(u != null) {
//			int result = service.updateUser(User.builder().userId(userId).userPass(changepw1).build());
//			if(result==1) {
//				session.invalidate();
//				return "redirect:/";
//			}else {
//				model.addAttribute("msg", "비밀번호 변경 실패!!!");
//				return "index";
//			}
//		}else {
//			model.addAttribute("msg", "비밀번호 변경 실패!!!");
//			return "index";
//		}
//	}
//	
//	@GetMapping("/delete")
//	public String delete(HttpSession session) throws SQLException {
//		User u = (User) session.getAttribute("userInfo");
//		session.invalidate();
//		service.deleteUser(u);
//		return "redirect:/";
//	}
	
}

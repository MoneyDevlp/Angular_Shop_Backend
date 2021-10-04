package com.app.tclothes.rest.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.tclothes.entity.ERole;
import com.app.tclothes.entity.Role;
import com.app.tclothes.entity.Custommer;
import com.app.tclothes.request.LoginRequest;
import com.app.tclothes.request.SignupRequest;
import com.app.tclothes.response.JwtResponse;
import com.app.tclothes.response.MessageResponse;
import com.app.tclothes.dao.RoleDao;
import com.app.tclothes.dao.CustommerDao;
import com.app.tclothes.jwt.JwtUtils;
import com.app.tclothes.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rest/")
public class AuthRestController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustommerDao userRepository;

	@Autowired
	RoleDao roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getFullname(), userDetails.getEmail(), roles));
	}

//	@PostMapping("signup")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Username is already taken!"));
//		}
//
//		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Email is already in use!"));
//		}
//
//		// Create new user's account
//		Custommer user = new Custommer(signUpRequest.getUsername(),
//							 signUpRequest.getFullname(),
//							 signUpRequest.getEmail(),
//							 encoder.encode(signUpRequest.getPassword()));
//
//		Set<String> strRoles = signUpRequest.getRole();
//		Set<Role> roles = new HashSet<>();
//
//		if (strRoles == null) {
//			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "admin":
//					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//				case "mod":
//					Role modRole = roleRepository.findByName(ERole.ROLE_STAFF)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(modRole);
//
//					break;
//				default:
//					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(userRole);
//				}
//			});
//		}
//
//		user.setRoles(roles);
//		userRepository.save(user);
//
//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//	}

	@PostMapping("signup")
	public Map<String, Object> registerUser(@Valid @RequestBody SignupRequest signUpRequest, Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (err.hasErrors()) {
				for (int i = 0; i < err.getAllErrors().size(); i++) {
					map.put("er", err.getAllErrors().get(i).getDefaultMessage());
					return map;
				}
			} else {
				if (userRepository.existsByUsername(signUpRequest.getUsername())
						|| userRepository.existsByEmail(signUpRequest.getEmail())) {
					map.put("status", false);
					return map;
				} else {
					Custommer user = new Custommer(signUpRequest.getUsername(), signUpRequest.getFullname(),
							signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

					Set<String> strRoles = signUpRequest.getRole();
					Set<Role> roles = new HashSet<>();

					if (strRoles == null) {
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					} else {
						strRoles.forEach(role -> {
							switch (role) {
							case "admin":
								Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(adminRole);

								break;
							case "mod":
								Role modRole = roleRepository.findByName(ERole.ROLE_STAFF)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(modRole);

								break;
							default:
								Role userRole = roleRepository.findByName(ERole.ROLE_USER)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(userRole);
							}
						});
					}

					user.setRoles(roles);
					userRepository.save(user);
					map.put("status", true);
					return map;
				}
			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}
}

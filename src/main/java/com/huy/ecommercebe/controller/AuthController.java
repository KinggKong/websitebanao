package com.huy.ecommercebe.controller;

import com.huy.ecommercebe.config.JwtProvided;
import com.huy.ecommercebe.dto.request.LoginRequest;
import com.huy.ecommercebe.dto.response.AuthResponse;
import com.huy.ecommercebe.exception.UserException;
import com.huy.ecommercebe.model.Cart;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.service.impl.CartServiceImpl;
import com.huy.ecommercebe.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    UserServiceImpl userService;
    JwtProvided jwtProvided;
    CartServiceImpl cartService;
    PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandle(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        User isEmailExist = userService.findUserByEmail(email);
        if (isEmailExist != null) {
            throw new UserException("Email is already in use");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        User savedUser = userService.insertUser(createdUser);
        Cart cart = cartService.createCart(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvided.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token, "Signup Success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandle(@RequestBody LoginRequest loginRequest) throws UserException {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvided.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token, "Signin Success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}

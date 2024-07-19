package com.huy.ecommercebe.service.impl;

import com.huy.ecommercebe.config.JwtProvided;
import com.huy.ecommercebe.exception.UserException;
import com.huy.ecommercebe.model.User;
import com.huy.ecommercebe.repository.UserRepository;
import com.huy.ecommercebe.service.IUserSerivice;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserDetailsService, IUserSerivice {
    UserRepository userRepository;
    JwtProvided jwtProvided;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public User findUserById(Long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("User not found with id: " + id);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvided.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User insertUser(User user) throws UserException {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws UserException {
        return null;
    }

    @Override
    public void deleteUser(Long id) throws UserException {

    }
}

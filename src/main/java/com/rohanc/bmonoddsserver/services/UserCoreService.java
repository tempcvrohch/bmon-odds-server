/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.db.User;
import com.rohanc.bmonoddsserver.models.dto.UserDto;
import com.rohanc.bmonoddsserver.models.generic.UserPrincipal;
import com.rohanc.bmonoddsserver.models.mapper.UserMapper;
import com.rohanc.bmonoddsserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCoreService implements UserDetailsService {
  @Autowired private UserRepository userRepository;

  @Autowired private UserMapper userMapper;

  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new UserPrincipal(user);
  }

  public UserDto register(String username, String password) {
    if (isInvalidInput(username)) { // TODO: move to service
      throw new InvalidSyntaxCredentials("invalid username");
    } else if (isInvalidInput(password)) {
      throw new InvalidSyntaxCredentials("invalid password");
    }

    User existingUser = userRepository.findByUsername(username);
    if (existingUser != null) {
      throw new UsernameTakenException();
    }

    User newUser = new User(username, encoder.encode(password), 1000f);
    var persistedUser = userRepository.save(newUser);
    return userMapper.toDto(persistedUser);
  }

  private boolean isInvalidInput(String input) {
    return input.length() < 5 || input.length() > 15 || !input.matches("[A-Za-z0-9_]+");
  }

  class InvalidSyntaxCredentials extends RuntimeException {
    InvalidSyntaxCredentials(String s) {
      super(s);
    }
  }

  public class UsernameTakenException extends RuntimeException {}
}

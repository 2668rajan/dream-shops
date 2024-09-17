package com.rajan.dreamshops.security.user;

import com.rajan.dreamshops.entity.User;
import com.rajan.dreamshops.exception.ResourceNotFoundException;
import com.rajan.dreamshops.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return ShopUserDetails.buildUserDetails(user);
    }
}

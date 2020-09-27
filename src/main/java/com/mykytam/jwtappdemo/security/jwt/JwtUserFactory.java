package com.mykytam.jwtappdemo.security.jwt;

import com.mykytam.jwtappdemo.model.Role;
import com.mykytam.jwtappdemo.model.Status;
import com.mykytam.jwtappdemo.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->  new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}

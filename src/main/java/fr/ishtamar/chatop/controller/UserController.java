package fr.ishtamar.chatop.controller;

import fr.ishtamar.chatop.dto.AuthRequest;
import fr.ishtamar.chatop.dto.UserDto;
import fr.ishtamar.chatop.entity.UserInfo;
import fr.ishtamar.chatop.service.JwtService;
import fr.ishtamar.chatop.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(hidden=true)
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @Operation(summary = "register new user",responses={
            @ApiResponse(responseCode="200", description = "User successfully created"),
            @ApiResponse(responseCode="400", description = "User already exists")
    })
    @PostMapping("/register")
    public Map<String,String> addNewUser(@RequestBody UserInfo userInfo) {
        service.addUser(userInfo);
        Map<String,String>map=new HashMap<>();
        map.put("token",jwtService.generateToken(userInfo.getEmail()));
        return map;
    }

    @Operation(summary = "gets personal data from logged in user",responses={
            @ApiResponse(responseCode="200", description = "Personal data is displayed"),
            @ApiResponse(responseCode="403", description = "Access unauthorized")
    })
    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public UserDto userProfile(@RequestHeader("Authorization") String jwt) {
        return service.getUserDtoByUsername(jwtService.extractUsername(jwt.substring(7)));
    }

    @Operation(summary = "logins user and returns JWT",responses={
            @ApiResponse(responseCode="200", description = "Token successfully created"),
            @ApiResponse(responseCode="403", description = "Access unauthorized")
    })
    @PostMapping("/login")
    public Map<String,String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            Map<String,String>map=new HashMap<>();
            map.put("token",jwtService.generateToken(authRequest.getEmail()));
            return map;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}

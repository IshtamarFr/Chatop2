package fr.ishtamar.chatop.controller;

import fr.ishtamar.chatop.dto.AuthRequest;
import fr.ishtamar.chatop.dto.UserDto;
import fr.ishtamar.chatop.entity.UserInfo;
import fr.ishtamar.chatop.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.chatop.mapper.UserMapper;
import fr.ishtamar.chatop.service.JwtService;
import fr.ishtamar.chatop.service.UserInfoService;
import fr.ishtamar.chatop.service.impl.UserInfoServiceImpl;
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
public class UserController {

    @Autowired
    private UserInfoService service=new UserInfoServiceImpl();
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;

    @Operation(hidden=true)
    @GetMapping("/auth/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @Operation(summary = "register new user",responses={
            @ApiResponse(responseCode="200", description = "User successfully created"),
            @ApiResponse(responseCode="400", description = "User already exists")
    })
    @PostMapping("/auth/register")
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
    @GetMapping("/auth/me")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public UserDto userProfile(@RequestHeader(value="Authorization",required=false) String jwt) {
        return userMapper.toDto(service.getUserByUsername(jwtService.extractUsername(jwt.substring(7))));
    }

    @Operation(summary = "logins user and returns JWT",responses={
            @ApiResponse(responseCode="200", description = "Token successfully created"),
            @ApiResponse(responseCode="403", description = "Access unauthorized"),
            @ApiResponse(responseCode="404", description = "Username not found")
    })
    @PostMapping("/auth/login")
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

    @Operation(summary = "gets personal data from user by id",responses={
            @ApiResponse(responseCode="200", description = "Personal data is displayed"),
            @ApiResponse(responseCode="403", description = "Access unauthorized")
    })
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public UserDto getUserById(@PathVariable("id") final long id) throws EntityNotFoundException {
        return userMapper.toDto(service.getUserById(id));
    }
}

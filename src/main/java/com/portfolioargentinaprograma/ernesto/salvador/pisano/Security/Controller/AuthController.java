/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Controller;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Dto.JwtDto;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Dto.LoginUser;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Dto.NewUser;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Entity.Rol;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Entity.User;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Enums.RolName;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.JWT.JwtProvider;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Service.RolService;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Service.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new RespMessage("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByUserName(newUser.getUserName())) {
            return new ResponseEntity(new RespMessage("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity(new RespMessage("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(newUser.getName(), newUser.getUserName(),
                newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));

        Set<Rol> rols = new HashSet<>();
        rols.add(rolService.getByRolName(RolName.ROLE_USER).get());

        if (newUser.getRols().contains("admin")) {
            rols.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        }
        user.setRols(rols);
        userService.save(user);

        return new ResponseEntity(new RespMessage("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new RespMessage("Campos mal puetos"), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUser.getUserName(), loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

}

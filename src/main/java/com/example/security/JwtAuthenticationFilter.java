package com.example.security;

import com.auth0.jwt.JWT;
import com.example.exception.ArgumentNotValidException;
import com.example.exception.InvalidEmailException;
import com.example.exception.detail.ErrorInfo;
import com.example.service.interfaces.UserService;
import com.example.service.validation.group.Create;
import com.example.service.validation.group.Update;
import com.example.service.validation.validator.CustomValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final MessageSource messageSource;
    private final CustomValidator customValidator;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   UserService userService,
                                   MessageSource messageSource, CustomValidator customValidator) {
        this.authenticationManager = authenticationManager;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.userService = userService;
        this.messageSource = messageSource;
        this.customValidator = customValidator;
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
    @PostMapping("/login")
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        LoginModel credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> propertyViolation = customValidator.validate(credentials, Create.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }

        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                new ArrayList<>()
        );

        response.setHeader("Access-Control-Expose-Headers", "Origin, X-Requested-With, " +
                "Content-Type, Accept, Accept-Encoding, Accept-Language, Host," +
                "Referer, Connection, User-Agent, Authorization, sw-useragent, sw-version");
//        Authentication auth = authenticationManager.authenticate(authenticationToken);

        //Authenticate user

        try {
            return authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        messageSource.getMessage("message.WrongPassword", null, LocaleContextHolder.getLocale()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (NullPointerException | InternalAuthenticationServiceException e) {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        messageSource.getMessage("message.WrongUsername", null, LocaleContextHolder.getLocale()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (AccountStatusException e) {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Locked");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

        //Create Token
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
       response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
    }
}
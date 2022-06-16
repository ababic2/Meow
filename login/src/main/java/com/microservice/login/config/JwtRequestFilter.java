//package com.microservice.login.config;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.OffsetDateTime;
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    private final JwtTokenUtil jwtTokenUtil;
////    private final LogRepository logRepository;
//    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil) {
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        String token = request.getHeader("Authorization");
////        String requestMethod = request.getMethod();
////        String path = request.getRequestURI();
////        String service = request.getHeader("Service");
////        String actionStatus = "ok";
////        if (service == null)
////            service = "Management service";
////        if ("/login".equals(path) || "/register-client".equals(path) || "/register-instructor".equals(path)) {
////            LogInDB logInDB = new LogInDB(OffsetDateTime.now(), service, null, requestMethod, path, actionStatus);
////            logRepository.save(logInDB);
////            filterChain.doFilter(request, response);
////            return;
////        }
////        if (token == null || token.equals("")) {
////            actionStatus = "Unauthorized access!";
////            LogInDB logInDB = new LogInDB(OffsetDateTime.now(), service, null, requestMethod, path, actionStatus);
////            logRepository.save(logInDB);
////            response.sendError(HttpStatus.UNAUTHORIZED.value(), "No authorization header or token is present");
////            return;
////        }
////        if (token.startsWith("Bearer "))
////            token = token.replace("Bearer ", "");
////        String validateTokenMessage = validateToken(token);
////        TokenSubject userDetails = jwtTokenUtil.getTokenSubject(token);
////        System.out.println(path + " " + token);
////        if (validateTokenMessage != null) {
////            actionStatus = "Unauthorized access!";
////            LogInDB logInDB = new LogInDB(OffsetDateTime.now(), service, userDetails.getUserId(), requestMethod, path, actionStatus);
////            logRepository.save(logInDB);
////            response.sendError(HttpStatus.UNAUTHORIZED.value(), validateTokenMessage);
////            return;
////        }
////        LogInDB logInDB = new LogInDB(OffsetDateTime.now(), service, userDetails.getUserId(), requestMethod, path, actionStatus);
////        logRepository.save(logInDB);
////        filterChain.doFilter(request, response);
////    }
//
//    private String validateToken(String token) {
//        String extras = jwtTokenUtil.extractSubject(token);
//        if (extras.equals("Expired")) {
//            return "Token expired, please log in again";
//        }
//        return null;
//    }
//}

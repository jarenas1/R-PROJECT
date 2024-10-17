package com.riwi_project.Riwi_project.configuration.security.filter;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riwi_project.Riwi_project.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.riwi_project.Riwi_project.configuration.security.TokenJwtConfig.*;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter { //extendemos

    //INSTANCIAMOS PARA AUTENTICAR
    private AuthenticationManager authenticationManager;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //convertir el json a un objeto de java, EL METODO ESTE VA A TRAER EL JSON QUE LE ENVIE EL JPAUSERDETAILSSERVICE
        UserEntity userEntity = null;
        String username = null;
        String password = null;

        //traemos el usuario del json USANDO TRYCATCH PARA LOS ERRORES
        try{
            //instanciamos todo en base al request que trae el metodo, y luego en base a la entity
            userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            username = userEntity.getUsername();
            password = userEntity.getPassword();
        }catch (StreamReadException e){
            e.printStackTrace();
        }catch (DatabindException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


        //COMPARAMOS LO DE LA BASE DE DATOS CON LO QUE ENTRA ACA
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        //AUTENTICAMOS
        return authenticationManager.authenticate(authenticationToken);
    }


    //METODO PARA USAR EL JWT SIII TOOODOO SALEEE EXISTOSO (LO DE ARRIBA)

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //Treamos desde el authResult el username
        User userSecurity = (User)authResult.getPrincipal();
        String username = userSecurity.getUsername();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

        Claims claims = Jwts.claims()
                .add("authorities", new ObjectMapper().writeValueAsString(roles))
                .build();

        //CREAMOS EL TOKEN DEL USUARIO CON SUS CONFIGURACIONES
        String token = Jwts.builder().
                subject(username)
                .claims(claims) //PASAMOS LOS ROLES CREADOS EN EL CLAIM
                .signWith(SECRET_KEY)
                .expiration(new Date(System.currentTimeMillis()+3600000)) //tiempo de expiracion, actual más x tiempo
                .issuedAt(new Date()) //Fecha de creacion
                .compact();

        //devolvemos el token al usuario
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);


        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", String.format("Hola %s has iniciado sesion con exito", username));

        //PASAMOS EL MAP A JSON Y LO DEVOLVEMOS
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        //devolvemos estado
        response.setStatus(200);
    }




    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
       //se crea el map que sera el json de error
        Map<String, String> body = new HashMap<>();
        body.put("message","Error en la autenticacion, username o password incorrectos!");
        body.put("Error", failed.getMessage()); //pasamos el error que capturo failed

        //Pasamos a JSON
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType(CONTENT_TYPE);
    }
}

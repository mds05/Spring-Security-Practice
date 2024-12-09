package com.security.security.config;

import com.security.security.service.MyUserDetailsService;

import jakarta.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    
    
    public SecurityConfig(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        
    }
        
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            System.out.println("1st");
            return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                .requestMatchers("register","login")
                .permitAll()
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
                
            }
            
            
            @Bean
            public AuthenticationProvider authenticationProvider(){
                System.out.println("2nd");
                DaoAuthenticationProvider p=new DaoAuthenticationProvider();
                p.setPasswordEncoder(new BCryptPasswordEncoder(12));
                p.setUserDetailsService(userDetailsService);
                return p;
        }
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
            return config.getAuthenticationManager();
        }
    //     @Bean
    //     public BCryptPasswordEncoder passwordEncoder() {
    //         return new BCryptPasswordEncoder(12);
    // }
        // @Bean
        // public UserDetailsService userDetailsService(){
        //HARDCODED VALUES
        //     UserDetails u1=User.withDefaultPasswordEncoder().username("bro").password("boo").build();
        //     return new InMemoryUserDetailsManager(u1);
        // }
}
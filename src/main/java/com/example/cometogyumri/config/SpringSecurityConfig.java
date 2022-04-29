package com.example.cometogyumri.config;

import com.example.cometogyumri.entity.userDetail.Role;
import com.example.cometogyumri.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsImpl userDetails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                .formLogin().loginPage("/login").permitAll()
                .and().logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET,"/hotelImages").permitAll()
                .antMatchers(HttpMethod.GET,"/restaurantGetImages").permitAll()
                .antMatchers(HttpMethod.GET, "/image/**").permitAll()
                .antMatchers(HttpMethod.GET, "/hotels").permitAll()
                .antMatchers(HttpMethod.GET, "/showAddHotel").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers(HttpMethod.GET, "/restaurants").permitAll()
                .antMatchers(HttpMethod.GET, "/addHotel").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers(HttpMethod.POST, "/addHotel").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers(HttpMethod.POST, "/hotelBooking{id}").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers(HttpMethod.GET, "/hotelBooking{id}").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers(HttpMethod.GET, "/restaurantReservation").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers(HttpMethod.POST, "/reservationRestaurant").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers(HttpMethod.POST,"/restaurantComment{id}").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers(HttpMethod.GET, "/addUser").permitAll()
                .antMatchers(HttpMethod.POST, "/addUser").permitAll()
                .antMatchers(HttpMethod.GET, "/user/activate").permitAll()
                .antMatchers(HttpMethod.GET, "/addRestaurant").permitAll()
                .antMatchers(HttpMethod.POST, "/addRestaurant").permitAll()
                .antMatchers(HttpMethod.GET, "/addRestaurant").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers(HttpMethod.POST, "/addRestaurant").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers(HttpMethod.GET, "/userProfile/{id}").hasAnyAuthority(Role.ADMIN.name(),Role.USER.name())
                .antMatchers("/deleteRestaurant/{id}").hasAnyAuthority(Role.ADMIN.name())
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetails)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.sha.springbootbooksat333.security;

import com.sha.springbootbooksat333.model.Role;
import com.sha.springbootbooksat333.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;
@Configuration   //Bean tanımlıyoruz
@EnableWebSecurity  // security ye web güvenliği yaılandırması
public class SecurityConfig extends WebSecurityConfigurerAdapter {  //Güenlik yaplandırmamızı yapıyoruz

    @Value("${authentication.internal-api-key}")
    private String internalApiKey;


//************************************************************************************************
    @Autowired
    private CustomUserDetailsService userDetailsService;

//*************************************************************************************************
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService)          //user details atıyoruz
                .passwordEncoder(passwordEncoder());
    }
  //***********************************************************************************************


    @Override
    protected void configure(HttpSecurity http) throws Exception  //Kimlik doğrılaması
    {
        http.cors();        //farklı domailnlerden alma
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Session işlemi

        http.authorizeRequests()   //kaıt yolları ve oturum açma dışında
                .antMatchers("/api/authentication/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/book").permitAll()
                .antMatchers("/api/book/**").hasRole(Role.ADMIN.name())
                .antMatchers("/api/internal/**").hasRole(Role.SYSTEM_MANAGER.name())
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
     .addFilterBefore(internalApiAuthenticationFilter(), JwtAuthorizationFilter.class);

    }
//***************************************************************************************************

//Authentation manager kısmı
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

@Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {               //Birkez oluşturulacak ve uygulama boyunca ona ulaşılabilek
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }
    //************************************************************************************************


    @Bean
    public PasswordEncoder passwordEncoder()   //passwordü şifreli yapma
    {

        return new BCryptPasswordEncoder();
    }
//****************************************************************************************************

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter()
    {


        return new JwtAuthorizationFilter();
    }

    //***********************************************************************************************

    @Bean
    public InternalApiAuthenticationFilter internalApiAuthenticationFilter()
    {
        return new InternalApiAuthenticationFilter(internalApiKey);
    }












}

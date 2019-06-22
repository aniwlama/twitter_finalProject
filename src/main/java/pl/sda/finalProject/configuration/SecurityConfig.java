package pl.sda.finalProject.configuration;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.naming.AuthenticationException;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JdbcTemplate jdbcTemplate;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeRequests()
                .antMatchers("/index/**")
                    .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .anyRequest()
                .permitAll()
                .and().csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginProcessingUrl("/login-process")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/index")
                .and()
                .logout()
                .logoutSuccessUrl("/login");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication)throws Exception {

        String queryRole = "SELECT u.username, u.role, 1 from application_user u" +
                            " where u.username=?";
        String queryPassword = "SELECT u.username, u.password, 1 from application_user u "+
                            "where u.username=?";

        authentication.inMemoryAuthentication()
                .withUser("admin")
                    .password("adminowe")
                    .roles("ADMIN");

        authentication.jdbcAuthentication()
                .usersByUsernameQuery(queryPassword)
                .authoritiesByUsernameQuery(queryRole)
                .dataSource(jdbcTemplate.getDataSource());




    }

}

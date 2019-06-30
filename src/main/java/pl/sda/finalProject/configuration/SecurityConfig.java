package pl.sda.finalProject.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeRequests()
                .antMatchers("/index/**")
                    .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/")
                    .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/addpost")
                    .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/posts")
                    .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
               /* .antMatchers("/login")
                    .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")*/ // nie moze byc, bo kazdy powinien moc to zobaczyc
                .anyRequest()
                .permitAll()
                .and().csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("login")
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

        String queryRole = "SELECT u.login, u.role, 1 from user u" +
                            " where u.login=?";
        String queryPassword = "SELECT u.login, u.password, 1 from user u "+
                            "where u.login=?";

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

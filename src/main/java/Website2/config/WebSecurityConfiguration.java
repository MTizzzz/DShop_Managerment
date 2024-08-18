package Website2.config;
import Website2.service.Class.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE)
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/api/v1/product/create-product",
                        "/api/v1/product/update-product/{id}",
                        "/api/v1/product/delete-product/{id}",
                        "/api/v1/cart/**",
                        "/api/v1/categories/**",
                        "/api/v1/type/**")
                .hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.PUT,
                        "/api/v1/product/create-product",
                        "/api/v1/product/update-product/{id}",
                        "/api/v1/product/delete-product/{id}",

                        "/api/v1/order/**",
                        "/api/v1/cart/**",
                        "/api/v1/categories/**",
                        "/api/v1/type/**")
                .hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.POST,
                        "/api/register",
                        "/api/login","/api/v1/order/create-order")
                .permitAll()
                .antMatchers(HttpMethod.GET,
                        "/pages/**",
                        "/auth/**",
                        "/api/v1/product/find-all-product",
                        "/api/v1/product/find-by-id/{id}",
                        "/api/v1/order//find-all-order",
                        "/api/v1/order/find-order-by-id",
                        "/api/v1/product/find-by-id/v1/{id}")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic()
                .and().cors().and().csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // Bean annotation để chỉ ra rằng phương thức này sẽ trả về một đối tượng được quản lý bởi Spring IoC container
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Tạo đối tượng CorsConfiguration mới, chứa thông tin cấu hình CORS
        CorsConfiguration configuration = new CorsConfiguration();
        // Thiết lập danh sách các nguồn (origins) được phép truy cập vào tài nguyên trên server
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3001","http://localhost:3000")); // Chỉ cho phép nguồn từ localhost:3001
        // Thiết lập các phương thức HTTP (HTTP methods) được phép sử dụng
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")); // Cho phép các phương thức HTTP này
        // Cho phép gửi thông tin xác thực (credentials) như cookie, headers authentication
        configuration.setAllowCredentials(true);
        // Thiết lập các headers nào từ client được phép gửi đến server
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); // Chỉ cho phép các headers này
        // Tạo đối tượng UrlBasedCorsConfigurationSource để đăng ký cấu hình CORS cho các đường dẫn URL cụ thể
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Đăng ký cấu hình CORS cho tất cả các URL (/**) sử dụng cấu hình đã tạo ở trên
        source.registerCorsConfiguration("/**", configuration);
        // Trả về đối tượng CorsConfigurationSource đã được cấu hình
        return source;
    }

}

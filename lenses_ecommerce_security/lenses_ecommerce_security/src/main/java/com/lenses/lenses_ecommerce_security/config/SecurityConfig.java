package com.lenses.lenses_ecommerce_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lenses.lenses_ecommerce_security.filter.CustomAuthenticationFilter;
import com.lenses.lenses_ecommerce_security.filter.CustomAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/api/login");
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/**", "/lenses/page={number}/size={size}/**"
				, "/lenses/ordered-by-price/**", "/lenses/{id}/**", "/colors/**", "/powers/**"
				,"/categories/**", "/categories/{categoryId}/**", "/brand/{brandId}/**", "/brands/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/add/item={sku}/**"
				, "/item/{itemId}/increase-quantity/**", "/item/{itemId}/dicrease-quantity/**",
				"add/address/to-current-user/**", "save/order/payment={paymentMethodId}/**", "order/payment/order={orderId}/**", "/payment/status/order={orderId}/**").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/Cart/**", "/total/cart/{cartId}/price/**"
				, "/total/item/{itemId}/price/**", "/lenses/{sku}/price/**", "/lenses/page={number}/size={size}/ordered-by/price/**",
				"address-list/by-user/{userId}/**", "address-list/by-current-user/**").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers("/create/customer/**").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/item/{itemId}/delete**", "remove/address/{addressId}/**").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user/save/**", "/lenses/save/**",
				"/category/save/**", "/color/save/**", "/power/save**", "/brand/save/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/lenses-to-category/{categoryId}/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/lenses/{id}/delete/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	

	
}

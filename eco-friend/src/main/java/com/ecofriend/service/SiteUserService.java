package com.ecofriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecofriend.dao.SiteUserDao;
import com.ecofriend.model.SiteUser;

/**
 * Site user service
 * 
 * @author user
 *
 */
@Service
public class SiteUserService implements UserDetailsService {

	@Autowired
	private SiteUserDao siteUserDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Register the user
	 * 
	 * @param user
	 */
	public void register(SiteUser user) {
		// encrypt the password first
		user.setPassword(passwordEncoder.encode(user.getPlainPassword()));
		siteUserDao.save(user);

	}

	/**
	 * Security configuration - find the user by email
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		SiteUser user = siteUserDao.findByEmail(email);

		if (user == null) {
			return null;
		}

		List<GrantedAuthority> authorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(user.getRole().toString());
		String password = user.getPassword();

		return new User(email, password, authorities);
	}

	/**
	 * return the user based on the given email
	 * 
	 * @param email
	 * @return
	 */
	public SiteUser findUserByEmail(String email) {
		return siteUserDao.findByEmail(email);
	}

}

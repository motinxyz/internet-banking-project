package com.abcb.pe;


/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * I have taken this code from Spring Security code base 
 * to encode password without using the whole module.
 * 
 * Creative Credits:
 * Code Author: Dave Syer
 * Organization: Pivotal Software
 * Code Base: https://github.com/spring-projects/spring-security
 * 
 * */

/* Modifications I have made over the original code: 
 * 
 * 1. Changed the logger from the apache commons to Java util type
 * 2. As a result, I had to change two logging method, from warn to info
 * 
 * 3. Commented the implementation of the upgradeEncoding() method
 * 		to reduce the size of the compiled class file as I will not use
 * 		the particular method.
 * 
 * 
 * I have kept all the original code that I have changed or omitted
 * as comments to keep the integrity of the original file
 * 	
 * 
 * Possible Security Threats:
 * 	1. csrf attack
 * */

import java.security.SecureRandom;
import java.util.logging.Logger;
//import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

//
/**
 * Implementation of PasswordEncoder that uses the BCrypt strong hashing
 * function. Clients can optionally supply a "version" ($2a, $2b, $2y) and a
 * "strength" (a.k.a. log rounds in BCrypt) and a SecureRandom instance. The
 * larger the strength parameter the more work will have to be done
 * (exponentially) to hash the passwords. The default value is 10.
 *
 * @author Dave Syer
 */

@Component
public class BCryptPasswordEncoder implements PasswordEncoder {

	private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

//	private final Log logger = LogFactory.getLog(getClass());
	private final Logger logger = Logger.getLogger(getClass().getName());
	private final int strength;

	private final BCryptVersion version;

	private final SecureRandom random;

	public BCryptPasswordEncoder() {
		this(-1);
	}

	/**
	 * @param strength the log rounds to use, between 4 and 31
	 */
	public BCryptPasswordEncoder(int strength) {
		this(strength, null);
	}

	/**
	 * @param version the version of bcrypt, can be 2a,2b,2y
	 */
	public BCryptPasswordEncoder(BCryptVersion version) {
		this(version, null);
	}

	/**
	 * @param version the version of bcrypt, can be 2a,2b,2y
	 * @param random  the secure random instance to use
	 */
	public BCryptPasswordEncoder(BCryptVersion version, SecureRandom random) {
		this(version, -1, random);
	}

	/**
	 * @param strength the log rounds to use, between 4 and 31
	 * @param random   the secure random instance to use
	 */
	public BCryptPasswordEncoder(int strength, SecureRandom random) {
		this(BCryptVersion.$2A, strength, random);
	}

	/**
	 * @param version  the version of bcrypt, can be 2a,2b,2y
	 * @param strength the log rounds to use, between 4 and 31
	 */
	public BCryptPasswordEncoder(BCryptVersion version, int strength) {
		this(version, strength, null);
	}

	/**
	 * @param version  the version of bcrypt, can be 2a,2b,2y
	 * @param strength the log rounds to use, between 4 and 31
	 * @param random   the secure random instance to use
	 */
	public BCryptPasswordEncoder(BCryptVersion version, int strength, SecureRandom random) {
		if (strength != -1 && (strength < BCrypt.MIN_LOG_ROUNDS || strength > BCrypt.MAX_LOG_ROUNDS)) {
			throw new IllegalArgumentException("Bad strength");
		}
		this.version = version;
		this.strength = (strength == -1) ? 10 : strength;
		this.random = random;
	}

	@Override
	public String encode(CharSequence rawPassword) {
		if (rawPassword == null) {
			throw new IllegalArgumentException("rawPassword cannot be null");
		}
		String salt = getSalt();
		return BCrypt.hashpw(rawPassword.toString(), salt);
	}

	private String getSalt() {
		if (this.random != null) {
			return BCrypt.gensalt(this.version.getVersion(), this.strength, this.random);
		}
		return BCrypt.gensalt(this.version.getVersion(), this.strength);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (rawPassword == null) {
			throw new IllegalArgumentException("rawPassword cannot be null");
		}
		if (encodedPassword == null || encodedPassword.length() == 0) {
			this.logger.info("Empty encoded password");
			return false;
		}
		if (!this.BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
			// this.logger.warn("Encoded password does not look like BCrypt");
			this.logger.info("Encoded password does not look like BCrypt");
			return false;
		}
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}

//	@Override
//	public boolean upgradeEncoding(String encodedPassword) {
//		if (encodedPassword == null || encodedPassword.length() == 0) {
////			this.logger.warn("Empty encoded password");
//			this.logger.info("Empty encoded password");
//			return false;
//		}
//		Matcher matcher = this.BCRYPT_PATTERN.matcher(encodedPassword);
//		if (!matcher.matches()) {
//			throw new IllegalArgumentException("Encoded password does not look like BCrypt: " + encodedPassword);
//		}
//		int strength = Integer.parseInt(matcher.group(2));
//		return strength < this.strength;
//	}

	/**
	 * Stores the default bcrypt version for use in configuration.
	 *
	 * @author Lin Feng
	 */
	public enum BCryptVersion {

		$2A("$2a"),

		$2Y("$2y"),

		$2B("$2b");

		private final String version;

		BCryptVersion(String version) {
			this.version = version;
		}

		public String getVersion() {
			return this.version;
		}

	}

}
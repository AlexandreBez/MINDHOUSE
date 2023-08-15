package com.gatewayserver.filter;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import reactor.core.publisher.Mono;

/**
 * The Class AuthorizationHeaderFilter.
 */
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

	/** The env. */
	@Autowired
	Environment env;

	/**
	 * Instantiates a new authorization header filter.
	 */
	public AuthorizationHeaderFilter() {
		super(Config.class);
	}

	/**
	 * The Class Config.
	 */
	public static class Config {
		// Put configuration properties here
	}

	/**
	 * Apply.
	 *
	 * @param config the config
	 * @return the gateway filter
	 */
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {

			ServerHttpRequest request = exchange.getRequest();

			if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
			}

			String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String jwt = authorizationHeader.replace("Bearer", "");

			if (!isJwtValid(jwt)) {
				return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
			}

			return chain.filter(exchange);
		};
	}

	/**
	 * On error.
	 *
	 * @param exchange the exchange
	 * @param err the err
	 * @param httpStatus the http status
	 * @return the mono
	 */
	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);

		return response.setComplete();
	}

	/**
	 * Checks if is jwt valid.
	 *
	 * @param jwt the jwt
	 * @return true, if is jwt valid
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean isJwtValid(String jwt) {
		boolean returnValue = true;

		String subject = null;
		String tokenSecret = env.getProperty("token.secret");
		byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecret.getBytes());
		SecretKey signingKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());

		JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(signingKey).build();

		try {
			Jwt<Header, Claims> parsedToken = jwtParser.parse(jwt);
			subject = parsedToken.getBody().getSubject();
		} catch (Exception ex) {
			returnValue = false;
		}

		if (subject == null || subject.isEmpty()) {
			returnValue = false;
		}

		return returnValue;
	}
}

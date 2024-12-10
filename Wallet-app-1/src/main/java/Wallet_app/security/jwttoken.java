package Wallet_app.security;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class jwttoken {

//	private static final String secret = "lPoQwOWeDZFZNTQBFvFaoFckOZkbQLEp4PKBPDwwpiqPSAqsi5FKQXFt2e7DEO3KTPX5tV5SsMHDQuKq7ku1yXkdOGha1KxJUWrR7ce6u4SmVe+37d1Gcwk+S4VgxzChCU+iHWxTDYLL0eetUCkJuFXF0LZB489fySJIs7MHMU6ZZ8D7u1w4uVPjkVlleo9N7N28xFX2axW8rThmwSdrS7UW2W/HjVqhtKJh+KXXBbhEttZq7HYPgiNfV//daJ8c1d3Kz0R2gF6t0B9px51FTBvbyd7PivREl+QkGqSB9d4+CPS2Vra22IlAj5kwqi5bPv3ZofwJmCSdRp/+oAQydNY1VyTG20zIM1VOftdouzI=\r\n"
//			+ "";
	private static final String secret="fz_Q2uPPzTTyccVHUoBRfsR8DtR-4OOC1JMA-DpRQnw\r\n"
			+ "";
	
	
//	= "ynlzhx8/8/qw5Wtv49Jvq/lYm5xAJljAm3JvWrL5R78=\r\n"
//			+ "";
			
			//"3273357638792F423F4528482B4D6251655368566D597133743677397A244326";
	//"lPoQwOWeDZFZNTQBFvFaoFckOZkbQLEp4PKBPDwwpiqPSAqsi5FKQXFt2e7DEO3KTPX5tV5SsMHDQuKq7ku1yXkdOGha1KxJUWrR7ce6u4SmVe+37d1Gcwk+S4VgxzChCU+iHWxTDYLL0eetUCkJuFXF0LZB489fySJIs7MHMU6ZZ8D7u1w4uVPjkVlleo9N7N28xFX2axW8rThmwSdrS7UW2W/HjVqhtKJh+KXXBbhEttZq7HYPgiNfV//daJ8c1d3Kz0R2gF6t0B9px51FTBvbyd7PivREl+QkGqSB9d4+CPS2Vra22IlAj5kwqi5bPv3ZofwJmCSdRp/+oAQydNY1VyTG20zIM1VOftdouzI=\r\n";

	public String createToken(String username) {
		// TODO Auto-generated method stub
		Map<String, Object> claims=new HashMap<>();
		return generateToken(claims,username);
	}

	private String generateToken(Map<String, Object> claims, String username) {
		// TODO Auto-generated method stub
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*30))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	}

	private SecretKey getSignKey() {
		// TODO Auto-generated method stub
		byte[] key = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(key);
	}

	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public java.util.Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

	public <T> T extractClaim(String token,Function<Claims, T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private  Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

 
	public Boolean validateToken(String token,UserDetails userDetails){
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new java.util.Date());
	}
	
	
	
}

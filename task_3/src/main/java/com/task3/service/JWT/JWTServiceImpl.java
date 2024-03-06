package com.task3.service.JWT;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.task3.Entity.User;
import com.task3.Repository.iUserRepository;

@Service
public class JWTServiceImpl implements IJwtService {
	
	@Value("classpath:jwtKeys/private_key.pem")
	private Resource privatekeyResource;
	
	@Value("classpath:jwtKeys/public_key.pem")
	private Resource publickeyResource;
	
	@Autowired
	private iUserRepository userRepo;
	@Override
	public String generarteJwt(Long id) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, JOSEException {
		PrivateKey privatekey =loadprivatekey(privatekeyResource);
		Optional<User> userid = userRepo.findById(id);
		JWSSigner signer = new RSASSASigner(privatekey);
		Date now = new Date();
		JWTClaimsSet clainsSet = new JWTClaimsSet.Builder()
				.subject(id.toString())
				.subject(userid.toString())
				.issueTime(now)
				.expirationTime(new Date(now.getTime() + 14400000))
				.build();
		SignedJWT signedjwt = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), clainsSet);
		signedjwt.sign(signer);
		return signedjwt.serialize();
	}
	@Override
	public JWTClaimsSet parseJWT(String jwt) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ParseException, JOSEException {
		PublicKey publickey = loadpublicKey(publickeyResource);
		
		SignedJWT signedjwt = SignedJWT.parse(jwt);
		JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publickey);
		if (!signedjwt.verify(verifier)) {
			throw new JOSEException("invalid signature/ firma invalida");
		}
		JWTClaimsSet claimsSet = signedjwt.getJWTClaimsSet();
		if(claimsSet.getExpirationTime().before(new Date())) {
			throw new JOSEException("EXPIRED TOKEN");
			
		}
		return claimsSet;
	}
	
	private PrivateKey loadprivatekey(Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] keybytes = Files.readAllBytes(Paths.get(resource.getURI()));
		String priviteKeyPem = new String(keybytes,StandardCharsets.UTF_8)
					.replace("-----BEGIN PRIVATE KEY-----", "")
					.replace("-----END PRIVATE KEY-----", "")
					.replaceAll("\\s", "");
		
		byte[] decodekey =Base64.getDecoder().decode(priviteKeyPem);
		
		KeyFactory keyfactory = KeyFactory.getInstance("RSA");
		return keyfactory.generatePrivate(new PKCS8EncodedKeySpec(decodekey));
	}
	private PublicKey loadpublicKey(Resource resource) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		byte[] keybytes = Files.readAllBytes(Paths.get(resource.getURI()));
		String publicKeyPem = new String(keybytes,StandardCharsets.UTF_8)
					.replace("-----BEGIN PUBLIC KEY-----", "")
					.replace("-----END PUBLIC KEY-----", "")
					.replaceAll("\\s", "");
		byte[] decodekey =Base64.getDecoder().decode(publicKeyPem);
		KeyFactory keyfactory = KeyFactory.getInstance("RSA");
		
		return keyfactory.generatePublic(new X509EncodedKeySpec(decodekey));
	}
}

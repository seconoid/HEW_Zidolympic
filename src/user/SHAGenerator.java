package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAGenerator {
	private static final int STRETCH_COUNT = 10000;
	
	public static String getSHA256(String src) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256"); // ハッシュ化
			md.update(src.getBytes()); // 文字列のバイトコード
			byte[] digest = md.digest(); //変換
			for( byte b:digest) {
				sb.append( String.format("%02x", b));
			}
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String getSaltedString( String id, String pass ) {
		String salt =  getSHA256(id);
		return getSHA256( salt + pass );
	}
	
	public static String getStretchedPassword( String id, String pass ) {
		String salt = getSHA256(id);
		String hash =  "";
		for( int i=0; i<STRETCH_COUNT; i++ ) {
			hash = getSHA256( salt + hash + pass );
		}
		return hash;
	}
}
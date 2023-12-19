package helper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
//Este código utiliza el algoritmo AES con una clave secreta generada mediante PBKDF2.
// Ten en cuenta que la seguridad de un sistema de cifrado depende de cómo manejes y
// almacenes las claves, así que ten cuidado al gestionarlas en tu aplicación.
// Además, este es solo un ejemplo básico, y en entornos de producción,
// es posible que desees considerar otras consideraciones de seguridad,
// como la gestión adecuada de las claves y la configuración de los parámetros del cifrado.
public class EncriptacionDesencriptacion {

    public static String encriptar(String cadena, String claveSecreta) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        SecretKey secretKey = generarClaveSecreta(claveSecreta);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
        byte[] datosEncriptados = cipher.doFinal(cadena.getBytes());
        return Base64.getEncoder().encodeToString(datosEncriptados);
    }

    public static String desencriptar(String datosEncriptados, String claveSecreta) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        SecretKey secretKey = generarClaveSecreta(claveSecreta);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
        byte[] datosDesencriptados = cipher.doFinal(Base64.getDecoder().decode(datosEncriptados));
        return new String(datosDesencriptados);
    }

    private static SecretKey generarClaveSecreta(String claveSecreta) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(claveSecreta.toCharArray(), claveSecreta.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }

    public static void main(String[] args) {
        try {
            String cadenaOriginal = "Hola, esta es una cadena de prueba.";
            String claveSecreta = "claveSuperSecreta";

            // Encriptar la cadena
            String cadenaEncriptada = encriptar(cadenaOriginal, claveSecreta);
            System.out.println("Cadena encriptada: " + cadenaEncriptada);

            // Desencriptar la cadena
            String cadenaDesencriptada = desencriptar(cadenaEncriptada, claveSecreta);
            System.out.println("Cadena desencriptada: " + cadenaDesencriptada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

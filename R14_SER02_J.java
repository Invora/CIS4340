/*
Rule 14. Serialization (SER)
SER02-J. Sign then seal objects before sending them outside a trust boundary
 */

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import java.io.*;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

public class R14_SER02_J {

    //Compliant Solution (Sign Then Seal)
    class SerializableMap<K,V> implements Serializable {
        final static long serialVersionUID = -2648720192864531932L;
        private Map<K,V> map;

        public SerializableMap() {
            map = new HashMap<K,V>();
        }

        public Object getData(K key)  {
            return map.get(key);
        }

        public void setData(K key, V data)  {
            map.put(key, data);
        }
    }

    public class MapSerializer {
        public static SerializableMap<String, Integer> buildMap() {
            SerializableMap<String, Integer> map =
                    new SerializableMap<String, Integer>();
            map.setData("John Doe", new Integer(123456789));
            map.setData("Richard Roe", new Integer(246813579));
            return map;
        }

        public static void InspectMap(SerializableMap<String, Integer> map) {
            System.out.println("John Doe's number is " + map.getData("John Doe"));
            System.out.println("Richard Roe's number is " +
                    map.getData("Richard Roe"));
        }

        public static void main(String[] args)
                throws
                IOException, GeneralSecurityException,
                ClassNotFoundException {
            // Build map
            SerializableMap<String, Integer> map = buildMap();

            // Generate signing public/private key pair & sign map
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
            KeyPair kp = kpg.generateKeyPair();
            Signature sig = Signature.getInstance("SHA1withDSA");
            SignedObject signedMap = new SignedObject(map, kp.getPrivate(), sig);

            // Generate sealing key & seal map
            KeyGenerator generator;
            generator = KeyGenerator.getInstance("AES");
            generator.init(new SecureRandom());
            Key key = generator.generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            SealedObject sealedMap = new SealedObject(signedMap, cipher);

            // Serialize map
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream("data"));
            out.writeObject(sealedMap);
            out.close();

            // Deserialize map
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream("data"));
            sealedMap = (SealedObject) in.readObject();
            in.close();

            // Unseal map
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            signedMap = (SignedObject) sealedMap.getObject(cipher);

            // Verify signature and retrieve map
            if (!signedMap.verify(kp.getPublic(), sig)) {
                throw new GeneralSecurityException("Map failed verification");
            }
            map = (SerializableMap<String, Integer>) signedMap.getObject();

            // Inspect map
            InspectMap(map);
        }
    }
}
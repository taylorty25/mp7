import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Hash Class
public class Hash{
  byte[] data;
  public static byte[] Hash(String msg) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(msg.getBytes());
    byte[] hash = md.digest();
    this.data = hash;
} // calculateHash(String)
}

/*While a hash is a byte array, it is convenient to write a wrapper class that wraps up a byte array along with some operations we would like to perform on it. Write a class called Hash with the following public methods:

Hash(byte[] data): constructs a new Hash object that contains the given hash (as an array of bytes).
byte[] getData(): returns the hash contained in this object.
boolean isValid(): returns true if this hash meets the criteria for validity, i.e., its first three indices contain zeroes.
String toString(): returns the string representation of the hash as a string of hexadecimal digits, 2 digits per byte.
boolean equals(Object other): returns true if this hash is structurally equal to the argument.
To implement toString(), you will find the following static methods useful:

Byte.toUnsignedInt
String.format
The conversion method Byte.toUnsignedInt is necessary because all integral values in Java are signed. We do not want values past 127 to be interpreted as negative numbers, e.g., if we have the bit pattern 11111111, we want to interpret this as the value 255 not -128. String.format works like sprintf which acts like printf but writes its output to a string. You should use a format specifier that prints an integer to the screen in hexadecimal using two digits; read the documentation to discover what this format specifier is.

The equals method should check to see if:

other is an instance of Hash using the instanceof operator.
If so, it should cast other to type Hash, e.g., Hash o = (Hash) other and then use the Arrays.equals static method to perform the appropriate equality check on the two Hash object’s arrays.
*/

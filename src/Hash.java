import java.util.Arrays;

/**
 * Stores hash value.
 *
 * @author Tyrell
 * @author Reed
 */
public class Hash {
  private final byte[] data;

  /**
   * constructs a new Hash object that contains the given hash (as an array of bytes).
   *
   * @param data hash value
   */
  Hash(byte[] data) {
    this.data = data;
  } // calculateHash(String)

  /**
   * Returns the hash contained in this object.
   */
  byte[] getData() {
    return data;
  } // getData()

  /**
   * Returns true if this hash meets the criteria for validity, i.e., its first three indices contain zeroes.
   */
  boolean isValid() {
    return data[0] >> 5 == 0;
  } // isValid()

  /**
   * Returns the string representation of the hash as a string of hexadecimal digits, 2 digits per byte.
   */
  public String toString() {
    StringBuilder hex = new StringBuilder();
    for (byte b : data) {
      hex.append(String.format("%02X ", Byte.toUnsignedInt(b)));
    } // for
    return hex.toString();
  } // toString()

  /**
   * Returns true if this hash is structurally equal to the argument.
   */
  public boolean equals(Object other) {
    if (!(other instanceof Hash)) {
      return false;
    } // if
    return Arrays.equals(this.getData(), ((Hash) other).getData());
  } // boolean equals(Object other)

}
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Stores the information for the block.
 * Methods available to compute the hash and nonce.
 *
 * @author Reed
 * @author Tyrell
 */
public class Block {

  private final int blockCount;
  private final int amount;
  private final Hash previousHash;
  private long nonce;
  private Hash hash;

  /**
   * Constructs a new block and computes the nonce and hash.
   *
   * @param blockCount   index of block in blockchain
   * @param amount       transaction amount
   * @param previousHash hash of the previous block
   */
  Block(int blockCount, int amount, Hash previousHash) {
    this.blockCount = blockCount;
    this.amount = amount;
    this.previousHash = previousHash;
    findNonce();
  } // Block(int blockCount, int amount, Hash previousHash)

  /**
   * Constructs a new block and computes the hash given the nonce.
   */
  Block(int blockCount, int amount, Hash previousHash, long nonce) {
    this.blockCount = blockCount;
    this.amount = amount;
    this.previousHash = previousHash;
    this.nonce = nonce;
    this.hash = computeHash(nonce);
  } // Block(int blockCount, int amount, Hash previousHash, long nonce)

  /**
   * Hashes fields of block using provided nonce.
   */
  private Hash computeHash(long nonce) {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("sha-256");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } // try/catch
    ByteBuffer block = ByteBuffer.allocate(4);
    block.putInt(this.blockCount);
    block.putInt(this.amount);
    if (this.blockCount != 0) {
      block.put(this.previousHash.getData());
    } // if
    block.putLong(nonce);
    md.update(block.array());
    byte[] hashValue = md.digest();
    return new Hash(hashValue);
  } // computeHash

  /**
   * Finds a valid nonce and then sets the hash.
   */
  private void findNonce() {
    Hash hash;
    int nonce = 0;
    do {
      hash = computeHash(nonce++);
    } while (!hash.isValid());
    this.nonce = nonce;
    this.hash = hash;
  } // findNonce()

  /**
   * @return blockCount
   */
  int getNum() {
    return blockCount;
  }

  /**
   * @return amount
   */
  int getAmount() {
    return amount;
  } // getAmount()

  /**
   * @return nonce
   */
  long getNonce() {
    return nonce;
  } // getNonce()

  /**
   * @return previousHash
   */
  Hash getPrevHash() {
    return this.previousHash;
  } // getPrevHash()

  /**
   * @return hash
   */
  Hash getHash() {
    return this.hash;
  } // getHash()

  /**
   * @return a string representation of the block.
   */
  public String toString() {
    String representation = "Block " + this.blockCount + " ";
    representation += "(Amount: " + this.amount + ", ";
    representation += "Nonce: " + this.nonce + ", ";
    representation += "prevHash: " + this.previousHash + ", ";
    representation += "hash: " + this.hash + ")";
    return representation;
  } // toString()
}

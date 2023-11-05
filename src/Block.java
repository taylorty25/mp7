public class Block {

  int blockCount;
  int amount;
  Hash previousHash;
  long nonce;
  Hash hash;

  /**
   * Creates a new block and computes the nonce and hash.
   */
  public Block(int blockCount, int amount, Hash previousHash) {
    this.blockCount = blockCount;
    this.amount = amount;
    this.previousHash = previousHash;
    // TODO mine nonce, compute hash
  }
  /**
   * Creates a new block and computes the hash given the nonce.
   */
  public Block(int blockCount, int amount, Hash previousHash, long nonce) {
    this.blockCount = blockCount;
    this.amount = amount;
    this.previousHash = previousHash;
    this.nonce = nonce;
    // TODO compute hash
  }

  /**
   * @return blockCount
   */
  public int getNum() {
    return blockCount;
  }

  /**
   * @return amount
   */
  public int getAmount() {
    return amount;
  } // getAmount()

  /**
   * @return nonce
   */
  public long getNonce() {
    return nonce;
  } // getNonce()

  /**
   * @return previousHash
   */
  public Hash getPrevHash() {
    return this.previousHash;
  } // getPrevHash()
  /**
   * @return hash
   */
  public Hash getHash() {
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

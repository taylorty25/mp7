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
   * @return blockCount.
   */
  public int getNum() {
    return blockCount;
  }

  /**
   * @return amount.
   */
  public int getAmount() {
    return amount;
  }

  /**
   * @return nonce.
   */
  public long getNonce() {
    return nonce;
  }
}

/**
 Hash getPrevHash(): returns the hash of the previous block in the blockchain.
 Hash getHash(): returns the hash of this block.
 String toString(): returns a string representation of the block (see below).
    The string representation of a Block should be formatted as follows
    (filling in values for the things in angle brackets):
    be different depending on the order in which you hash values.
    For consistency’s sake with our testing suite,
    ensure that you update the MessageDigest object in the order specified above.
    For the first block, you should not hash anything for the previous hash value (step 3).
    Furthermore, you should also search the space of nonces by searching all possible longs in increasing order starting at zero.
 **/
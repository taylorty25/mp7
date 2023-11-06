import java.security.NoSuchAlgorithmException;


/**
 * A blockchain of Blocks.
 *
 * @author Reed
 * @author Tyrell
 */
public class BlockChain {
  private final Node first;
  private Node last;

  /**
   * Constructs a BlockChain with a starting block.
   *
   * @param initial transaction value for the first block
   */
  public BlockChain(int initial) throws NoSuchAlgorithmException {
    this.first = new Node(new Block(0, initial, null), null);
    this.last = first;
  } // BlockChain()

  /**
   * @ return A newly mined block
   */
  public Block mine(int amount) throws NoSuchAlgorithmException {
    return new Block(this.getSize(), amount, this.getHash());
  } // mine(int amount)


  /**
   * @return the length of the blockchain
   */
  private int getSize() {
    return this.last.block.getNum() + 1;
  } // getSize


  /**
   * @return the hash of the last block
   */
  public Hash getHash() {
    return this.last.block.getHash();
  } // getSize


  /**
   * Removes the last block from the chain, returning true.
   * If the chain only contains a single block,
   * then removeLast does nothing and returns false.
   */
  public boolean removeLast() {
    if (this.getSize() == 1) {
      this.last = this.last.previousNode;
      return true;
    } else {
      return false;
    } // if/else
  } // getSize

  /**
   * Adds the block to the list.
   * @throws IllegalArgumentException if the block invalidates the chain.
   */
  void append(Block block) throws IllegalArgumentException {
    boolean validHash = block.getHash().isValid();
    boolean validChain = block.getPrevHash().equals(this.last.block.getHash());
    // New index value should be equal to current size
    boolean validSize = block.getNum() == getSize();
    if (validHash && validChain && validSize) {
      this.last = new Node(block, this.last);
    } else {
      throw new IllegalArgumentException();
    } // if/else
  } // append(Block block)


  /**
   * returns a string representation of the BlockChain
   * which is simply the string representation of each of its blocks,
   * earliest to latest, one per line.
   */
  public String toString() {
    Node cursor = this.last;
    StringBuilder blocks = new StringBuilder();
    while (cursor != null) {
      blocks.append(cursor.block.toString());
      cursor = cursor.previousNode;
    } // while
    return blocks.toString();
  } // toString()


  /**
   * Node for BlockChain's singly linked list.
   */
  static class Node {
    Block block;
    Node previousNode;

    /**
     * Constructor for a Node
     */
    Node(Block block, Node previousNode) {
      this.block = block;
      this.previousNode = previousNode;
    } // Node
  } // class Node
}

/**
 * boolean isValidBlockChain(): walks the blockchain and ensures that its blocks are consistent and valid.
 * void printBalances(): prints Alexis’s and Blake’s respective balances in the form Alexis: <amt>, Blake: <amt> on a single line, e.g., Alexis: 300, Blake: 0.
 **/

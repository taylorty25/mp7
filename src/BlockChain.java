import java.io.PrintWriter;

/**
 * A blockchain of Blocks.
 *
 * @author Reed
 * @author Tyrell
 */
public class BlockChain {
  private Node last;

  /**
   * Constructs a BlockChain with a starting block.
   *
   * @param initial transaction value for the first block
   */
  public BlockChain(int initial) {
    this.last = new Node(new Block(0, initial, null), null);
  } // BlockChain()

  /**
   * @ return A newly mined block
   */
  public Block mine(int amount) {
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
  private Hash getHash() {
    return this.last.block.getHash();
  } // getSize


  /**
   * Removes the last block from the chain, returning true.
   * If the chain only contains a single block,
   * then removeLast does nothing and returns false.
   */
  boolean removeLast() {
    if (this.getSize() == 1) {
      this.last = this.last.previousNode;
      return true;
    } else {
      return false;
    } // if/else
  } // getSize

  /**
   * Adds the block to the list.
   *
   * @throws IllegalArgumentException if the block invalidates the chain.
   */
  void append(Block block) {
    if (isValidBlock(block)) {
      this.last = new Node(block, this.last);
    } else {
      throw new IllegalArgumentException();
    } // if/else
  } // append(Block block)

  /**
   * Tests validity of block.
   */
  private boolean isValidBlock(Block block) {
    boolean validHash = block.getHash().isValid();
    boolean validChain = block.getPrevHash().equals(this.last.block.getHash());
    // New index value should be equal to current size
    boolean validSize = block.getNum() == getSize();
    // TODO: make sure they have enough funds
    return validHash && validChain && validSize;
  } // isValidBlock(Block block)

  /**
   * Walks the blockchain and ensures that its blocks are consistent and valid.
   */
  boolean isValidBlockChain() {
    Node cursor = this.last;
    while (cursor != null) {
      if (!isValidBlock(cursor.block)) {
        return false;
      } else {
        cursor = cursor.previousNode;
      } // if/else
    } // while
    return true;
  } // isValidBlockChain

  /**
   * Prints Alexis’s and Blake’s balances.
   */
  void printBalances() {
    Node cursor = this.last;
    int alexis = 0;
    int blake = 0;
    while (cursor != null) {
      alexis += cursor.block.getAmount();
      blake -= cursor.block.getAmount();
      cursor = cursor.previousNode;
    } // while
    PrintWriter pen = new PrintWriter(System.out,true);
    pen.println("Alexis: " + alexis + ", Blake: " + blake);
  }

  /**
   * Returns a string representation of each block on the BlockChain.
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
  private static class Node {
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

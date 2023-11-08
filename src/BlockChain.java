import java.io.PrintWriter;

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
  public BlockChain(int initial) {
    this.first = new Node(
            new Block(0, initial, null),
            null, null);
    this.last = this.first;
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
    if (isValidBlock(block) && enoughBalance(block.getAmount())) {
      this.last.nextNode = new Node(block, this.last, null);
      this.last = this.last.nextNode;
    } else {
      throw new IllegalArgumentException();
    } // if/else
  } // append(Block block)

  /**
   * Tests validity of block.
   */
  private boolean isValidBlock(Block block) {
    boolean validHash = block.getHash().isValid();
    boolean validPrevious = block.getPrevHash().equals(this.last.block.getHash());
    boolean validSize = block.getNum() == getSize();
    boolean validAmount = enoughBalance(block.getAmount());
    return validHash && validPrevious && validSize && validAmount;
  } // isValidBlock(Block block)

  /**
   * Makes sure sender's balance is sufficient.
   * Note: This also returns false if a negative balance existed in any previous state.
   */
  private boolean enoughBalance(int amount) {
    int startingBalance = this.first.block.getAmount();
    int total = 0;
    Node cursor = this.first.nextNode;
    while (cursor != null) {
      total += cursor.block.getAmount();
      if (total < -startingBalance || total > 0) {
        // Check that the starting balance is greater than the total cash transferred
        //   the total should always be negative, and the total should always be less than
        //   the inverse of the starting balance.
        return false;
      } // if
      cursor = cursor.nextNode;
    } // while
    total += amount;
    return total >= -startingBalance && total <= 0;
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
    return enoughBalance(0);
  } // isValidBlockChain

  private int[] balances() {
    Node cursor = this.last;
    int alexis = 0;
    int blake = 0;
    while (cursor != null) {
      alexis += cursor.block.getAmount();
      blake -= cursor.block.getAmount();
      cursor = cursor.previousNode;
    } // while
    return new int[]{alexis, blake};
  }

  /**
   * Prints Alexis’s and Blake’s balances.
   */
  void printBalances() {
    PrintWriter pen = new PrintWriter(System.out, true);
    int[] balances = balances();
    pen.println("Alexis: " + balances[0] + ", Blake: " + balances[1]);
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
   * Node record for BlockChain's doubly linked list.
   */
  private static final class Node {
    private final Block block;
    private final Node previousNode;
    private Node nextNode;

    /**
     * Node constructor.
     */
    Node(Block block, Node previousNode, Node nextNode) {
      this.block = block;
      this.previousNode = previousNode;
      this.nextNode = nextNode;
    }

  }
}

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
    if (isValidBlock(block) && enoughBalance(block.getAmount())) {
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
    Node cursor = this.last;
    while (cursor.previousNode != null) {
      amount += cursor.block.getAmount();
      if (amount < 0) {
        return false;
      } else {
        cursor = cursor.previousNode;
      } // if/else
    } // while
    // Check that the starting balance is greater than the total cash transferred
    //   the total should always be negative, and the total should always be less than
    //   the inverse of the starting balance.
    int startingBalance = cursor.block.getAmount();
    return -startingBalance >= amount && amount <= 0;
    // TODO: make sure that at each block, the rest of the blockchain is valid
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
   * Node record for BlockChain's singly linked list.
   */
  private record Node(Block block, BlockChain.Node previousNode) {
  }
}

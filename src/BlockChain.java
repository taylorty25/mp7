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
   * Node for BlockChain's singly linked list.
   */
  static class Node {
    Block block;
    Node previousNode;

    Node(Block block, Node previousNode) {
      this.block = block;
      this.previousNode = previousNode;
    } // Node
  }
}

/**
 * void append(Block blk): adds this block to the list, throwing an IllegalArgumentException if this block cannot be added to the chain (because it is invalid with the rest of the blocks).
 * boolean isValidBlockChain(): walks the blockchain and ensures that its blocks are consistent and valid.
 * void printBalances(): prints Alexis’s and Blake’s respective balances in the form Alexis: <amt>, Blake: <amt> on a single line, e.g., Alexis: 300, Blake: 0.
 * String toString(): returns a string representation of the BlockChain which is simply the string representation of each of its blocks, earliest to latest, one per line.
 **/

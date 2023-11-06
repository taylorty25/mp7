/**
 * A blockchain of Blocks.
 * @author Reed
 * @author Tyrell
 */
public class BlockChain {
  /**
   BlockChain(int initial): creates a BlockChain that possess a single block the starts with the given initial amount. Note that to create this block, the prevHash field should be ignored when calculating the block’s own nonce and hash.
   Block mine(int amount): mines a new candidate block to be added to the end of the chain. The returned Block should be valid to add onto this chain.
   int getSize(): returns the size of the blockchain. Note that number of the blocks provides a convenient method for quickly determining the size of the chain.
   void append(Block blk): adds this block to the list, throwing an IllegalArgumentException if this block cannot be added to the chain (because it is invalid with the rest of the blocks).
   boolean removeLast(): removes the last block from the chain, returning true. If the chain only contains a single block, then removeLast does nothing and returns false.
   Hash getHash(): returns the hash of the last block in the chain.
   boolean isValidBlockChain(): walks the blockchain and ensures that its blocks are consistent and valid.
   void printBalances(): prints Alexis’s and Blake’s respective balances in the form Alexis: <amt>, Blake: <amt> on a single line, e.g., Alexis: 300, Blake: 0.
   String toString(): returns a string representation of the BlockChain which is simply the string representation of each of its blocks, earliest to latest, one per line.
   *
   * @param initial initial transaction value for the first block
   * */
  public BlockChain(int initial) {

  } // BlockChain()
}

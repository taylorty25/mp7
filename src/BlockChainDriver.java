import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Interactive CLI for simulating blockchain transactions.
     Valid commands:
       mine: discovers the nonce for a given transaction
       append: appends a new block onto the end of the chain
       remove: removes the last block from the end of the chain
       check: checks that the blockchain is valid
       report: reports the balances of Alexis and Blake
       help: prints this list of commands
       quit: quits the program
 *
 * @author Reed
 * @author Tyrell
 **/
public class BlockChainDriver {
  /**
   * Start CLI loop
   */
  public static void main(String[] args) {
      BlockChain blockChain;
      Scanner scanner = new Scanner(System.in);
      PrintWriter pen = new PrintWriter(System.out, true);
      String input;
      do {
        input = scanner.nextLine();
        // TODO: logic to call functions and possibly initialize blockChain with initial value
      } while (!input.contains("quit"));
  } // main()
} // class BlockChainDriver

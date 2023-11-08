import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Interactive CLI for simulating blockchain transactions.
 *
 * @author Reed
 * @author Tyrell
 **/
public class BlockChainDriver {
  /**
   * Start CLI loop
   */
  public static void main(String[] args) {
    BlockChain blockChain = new BlockChain(Integer.parseInt(args[0]));
    Block minedBlock = null;
    Scanner scanner = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);
    String input;
    do {
      pen.printf("Command? ");
      input = scanner.nextLine().strip();
      switch (input) {
        case "help" -> pen.println("""
                Valid commands:
                    mine: discovers the nonce for a given transaction
                    append: appends a new block onto the end of the chain
                    remove: removes the last block from the end of the chain
                    check: checks that the blockchain is valid
                    report: reports the balances of Alexis and Blake
                    help: prints this list of commands
                    quit: quits the program""");
        case "mine" -> {
          pen.println("Amount transferred? ");
          int amount = scanner.nextInt();
          minedBlock = blockChain.mine(amount);
        } // case
        case "append" -> {
          if (minedBlock != null) {
            blockChain.append(minedBlock);
          } else {
            pen.println("Error: no mined block");
          } // if/else
        } // case
        case "check" -> {
          if (blockChain.isValidBlockChain()) {
            pen.println("Chain is valid!");
          } else {
            pen.println("Chain is invalid!");
          } // if/else
        } // case
        case "remove" ->
          blockChain.removeLast();
        case "report" ->
          blockChain.printBalances();
      } // switch
      pen.println(blockChain);
    } while (!input.equals("quit"));
    pen.println("BlockChain simulation quit.");
    pen.close();
    scanner.close();
  } // main()
} // class BlockChainDriver

package CSprotocols;
import CSprotocols.kncockknock.KnockKnockServer;
import CSprotocols.Ruppin.RuppinServer;

public class Main {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Please provide valid number(1/2)!");
			return;
		}
			try {
				int num = Integer.parseInt(args[0]);
				if(num == 1) {
					KnockKnockServer kks = new KnockKnockServer();
					kks.activateServer();
				}
				if(num == 2) {
				RuppinServer RS = new RuppinServer();
				RS.activateServer();
				}
				else {
					System.out.println("Please provide valid number(1\2)!");
					return;
				}
			} catch (NumberFormatException e) {
			System.out.println("Please provide valid number(1\\2)!");
			}
		}
	}

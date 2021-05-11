package algorithms.structure.tree.bplus.tmp;
import java.io.File;
import java.util.Scanner;

import java.lang.Math;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int M = Integer.valueOf(args[1]);
			int B = Integer.valueOf(args[2]);
			int d = (B-8)/12;
			d=Math.max(d,2);
			BPlusTree myTree = new BPlusTree(d); 
            //System.out.print("Enter the file name with extension : ");
            
            File file = new File(args[0]);

            Scanner input = new Scanner(file);


            while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] result = line.split("\\s");
                if (result[0].equals("FIND")){
					
					if(myTree.exist(Integer.valueOf(result[1]))){
						System.out.print("YES\n");
					}
					else{
						System.out.print("NO\n");
					}
				}
				else if (result[0].equals("INSERT")){
					myTree.insert(Integer.valueOf(result[1]), 1);
				}
				else if (result[0].equals("COUNT")){
					System.out.print(myTree.find(Integer.valueOf(result[1]), Integer.valueOf(result[1])));
					System.out.print("\n");
				}
				else{
					System.out.print(myTree.find(Integer.valueOf(result[1]), Integer.valueOf(result[2])));
					System.out.print("\n");
				}
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
		//Utils.printTree(myTree);
	}

}

package algorithms.structure.tree.bplus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @Description: TODO
 * @Author: Jin.HE
 * @Date: 2021/4/21 14:22
 */
public class BPlusTreeTest {

    public static void main(String[] args) throws IOException {
//        test1();
//        test2(10000);
        test2(40000);
//        test2(1000000);
//        test2(10000000);

    }

    private static void test2(int times){

        // Create initial B+ tree
        BPlustTreeFinal bpt = new  BPlustTreeFinal(10);

        long t1 = System.nanoTime();
        for(int i=0; i< times; i++){
            bpt.insert(i, i);
        }
        long t2 = System.nanoTime();

        Random ran = new Random();
        List<Long> keys = new ArrayList<>();
        for(int i=0; i<times; i++){
            keys.add(ran.nextLong());
        }

        long t3 = System.nanoTime();
        for(int i=0; i<times; i++){
            bpt.search(keys.get(i));
        }
        long t4 = System.nanoTime();

        List<Long> keys2 = new ArrayList<>();
        for(int i=0; i<times; i++){
            keys2.add(keys.get(i) + ran.nextInt(10000));
        }

        long t5 = System.nanoTime();
        for(int i=0; i<times; i++){
//            bpt.search(keys.get(i), keys2.get(i));
            bpt.search2(keys.get(i), keys2.get(i));
        }
        long t6 = System.nanoTime();


        long t7 = System.nanoTime();
        for(int i=0; i<times; i++){
            bpt.delete(keys.get(i));
        }
        long t8 = System.nanoTime();

        System.out.println("insert " + (t2-t1) + " ns, avg: " + (t2-t1)/times + " ns");
        System.out.println("search(k) " + (t4-t3) + " ns, avg: " + (t4-t3)/times + " ns");
        System.out.println("search(k1, k2) " + (t6-t5) + " ns, avg: " + (t6-t5)/times + " ns");
        System.out.println("delete " + (t8-t7) + " ns, avg: " + (t8-t7)/times + " ns");
    }


    private static void test1(){
        // Ensure correct number of arguments
//        if (args.length != 1) {
//            System.err.println("usage: java bplustree <file_name>");
//            System.exit(-1);
//        }

        // Read from file
//        String fileName = args[0];
        String fileName = "test.txt";
        fileName = "src/algorithms/structure/tree/bplus/" + fileName;
        try {

            // Prepare to read input file
            File file = new File(System.getProperty("user.dir") + "/" + fileName);
            Scanner sc = new Scanner(file);

            // Create output file in which search results will be stored
            FileWriter logger = new FileWriter("src/algorithms/structure/tree/bplus/output_file.txt", false);
            boolean firstLine = true;

            // Create initial B+ tree
            BPlustTreeFinal bpt = null;

            // Perform an operation for each line in the input file
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replace(" ", "");
                String[] tokens = line.split("[(,)]");

                switch (tokens[0]) {

                    // Initializes an m-order B+ tree
                    case "Initialize":
                        bpt = new BPlustTreeFinal(Integer.parseInt(tokens[1]));
                        break;

                    // Insert a dictionary pair into the B+ tree
                    case "Insert":
                        bpt.insert(Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]));
                        break;

                    // Delete a dictionary pair from the B+ tree
                    case "Delete":
                        bpt.delete(Integer.parseInt(tokens[1]));
                        break;

                    // Perform a search or search operation on the B+ tree
                    case "Search":
                        String result = "";

                        // Perform search (across a range) operation
                        if (tokens.length == 3) {
                            ArrayList<Double> values = bpt.search(
                                    Integer.parseInt(tokens[1]),
                                    Integer.parseInt(tokens[2]));

                            // Record search result as a String
                            if (values.size() != 0) {
                                for (double v : values) { result += v + ", "; }
                                result = result.substring(0, result.length() - 2);
                            } else {
                                result = "Null";
                            }

                        }

                        // Perform search operation
                        else {

							/* Perform search for key, if resulting value is
							   null, then the key could not be found */
                            Double value = bpt.search(Integer.parseInt(tokens[1]));
                            result = (value == null) ? "Null" :
                                    Double.toString(value);
                        }

                        // Output search result in .txt file
                        if (firstLine) {
                            logger.write(result);
                            firstLine = false;
                        } else {
                            logger.write("\n" + result);
                        }
                        logger.flush();

                        break;
                    default:
                        throw new IllegalArgumentException("\"" + tokens[0] +
                                "\"" + " is an unacceptable input.");
                }
            }

            // Close output file
            logger.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

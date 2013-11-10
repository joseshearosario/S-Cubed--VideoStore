package video.store;

import java.io.*;

/**
 * @author Jose Andres Rosario
 * 
 * A Java program that will keep track of the members of customers, their 
 * active rentals, and the inventory of the video story using linked lists.
 * 
 * In order for the main function to run, two csv files must be imported as
 * parameters. These two files are for the movies and customers. The first 
 * argument must be the the filename for the customers database csv file in the 
 * form of [first name,last name,10 digit number] without spaces. Likewise, the 
 * second argument is for the movies database and must be written as 
 * [film name,year,reference number] or [film name,year,reference number,stock] 
 * without spaces.
 * 
 * In the running of the main function, you are given the option to add 
 * additional movies and members.
 */

public class VideoStore {
    
    /**
     * Prints out the options you have as the user of the program
     * Accepts no parameters
    */
    public static void menu () {
        System.out.println ("Welcome to Blockbuster: ");
        System.out.println ("(1) Check whether we carries a particular video");
        System.out.println ("(2) Check out a video");
        System.out.println ("(3) Check in a video");
        System.out.println ("(4) Check whether a video is in stock");
        System.out.println ("(5) Print only the titles of the videos we carry");
        System.out.println ("(6) Print all the details of the titles we carry");
        System.out.println ("(7) Check your account with your ID");
        System.out.println ("(9) Exit");
    }
    
    /**
     * Places all member info from the input file into the global membership 
     * linked list
     * @param in the buffered reader that will be used to read in the file as 
     * defined by the argument
     * @throws IOException 
     */
    public static void createMembershipList (BufferedReader in) throws IOException {
        // while the file can be read, each line of information is read
        // and is used to create a new customer
        while (in.ready()) { 
            String[] c = (in.readLine()).split(",");
            long t = Long.parseLong(c[2].trim());
            Global.members.add(new Customer (c[0], c[1], t));
        }
    }
    
    /**
     * Used after inputting all the members in the input file to manually add 
     * an additional member. 
     * @param console the buffered reader initialized in main in order to read in 
     * input from the user
     * @throws IOException 
     */
    public static void manuallyAddMember (BufferedReader console) throws IOException {
            String first;
            String last;
            long ID = 0;
            boolean correct = false;
            
             do {
                System.out.print ("First name: ");
                first = console.readLine();
                first = first.replaceAll("[^A-z]", "");
                if (first.isEmpty())
                    System.out.println ("Invalid first name");
                else
                    correct = true;
            } while (!correct);
            
            correct = false;
            
            do {
                System.out.print ("Last name: ");
                last = console.readLine();
                last = last.replaceAll("[^A-z]", "");
                if (last.isEmpty()) 
                    System.out.println ("Invalid last name");
                else
                    correct = true;
            } while (!correct);
            
            correct = false;
            
            do {
                System.out.print ("Membership ID (10 digits): ");
                String t = console.readLine().trim();
                t = t.replaceAll("[^0-9]", "");
                if (t.isEmpty() || t.length() != 10) {
                    System.out.println ("Invalid ID");
                }
                else {
                    ID = Long.parseLong(t);
                    correct = true;
                }                    
            } while (!correct);

            Global.members.add(new Customer (first, last, ID));
    }
    
    /**
     * Places all movies info from the input file into the global inventory 
     * linked list while the file is still good. Which constructor to use 
     * depends on the amount of info given in the file.
     * @param in the buffered reader that will be used to read in the file as 
     * defined by the argument
     * @throws IOException 
     */
    public static void createVideoList (BufferedReader in) throws IOException {
        // while the file can be read, each line of information is read
        // and is used to create a new video
        while (in.ready()) { 
            String[] c = (in.readLine()).split(",");
            if (c.length == 3) {
                int t1 = Integer.parseInt(c[1].trim());    
                int t2 = Integer.parseInt(c[2].trim());
                Global.inventory.insertFirst(new Video (c[0], t1, t2));
            }
            
            else if (c.length == 4) {
                int t1 = Integer.parseInt(c[1].trim());    
                int t2 = Integer.parseInt(c[2].trim());
                int t3 = Integer.parseInt(c[3].trim());
                Global.inventory.insertFirst(new Video (c[0], t1, t2, t3));
            }
        }        
    }

    /**
     * Used after inputting all the movies in the input file to manually add 
     * an additional film. 
     * @param console the buffered reader initialized in main in order to read in 
     * input from the user
     * @throws IOException 
     */
    public static void manuallyAddMovie (BufferedReader console) throws IOException {
            String film;
            int year = 0;
            int reference = 0;
            int stock =0;
            boolean correct = false;
            
            do {
                System.out.print ("Film name: ");
                film = console.readLine();
                if (film.isEmpty())
                    System.out.println ("Invalid film name");
                else
                    correct = true;
            } while (!correct);
            
            correct = false;
            
            do {
                System.out.print ("Year: ");
                String t = console.readLine().trim();
                t = t.replaceAll("[^0-9]", "");
                if (t.isEmpty() || t.length() != 4) {
                    System.out.println ("Invalid year");
                    continue;
                }
                year = Integer.parseInt(t);
                // the oldest surviving film in existence was released in 1888
                if (year < 1888) 
                    System.out.println ("Invalid year");
                else
                    correct = true;
            } while (!correct);
            
            correct = false;
            
            do {
                System.out.print ("Film ID (6 digits): ");
                String t = console.readLine().trim();
                t = t.replaceAll("[^0-9]", "");
                if (t.isEmpty() || t.length() != 6) {
                    System.out.println ("Invalid ID");
                }
                else {
                    reference = Integer.parseInt(t);
                    correct = true;
                }   
            } while (!correct);
            
            correct = false;
            
            do {
                System.out.print ("Stock (Minimum 1): ");
                String t = console.readLine().trim();
                t = t.replaceAll("[^0-9]", "");
                if (t.isEmpty())
                    System.out.println ("Invalid inventory");
                else {
                    stock = Integer.parseInt(t);
                    if (stock < 1)
                        System.out.println ("Invalid inventory");
                    else
                        correct = true;
                }
            } while (!correct);
            
            Global.inventory.insertFirst(new Video (film, year, reference, stock));
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        /**
         * program ends if two arguments are not used
         */
        if (args.length != 2) {
            System.out.println ("Invalid amount of arguments");
            return;
        }
              
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in)); 
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String text;
        createMembershipList(in);
                
        /**
         * Allows for the user to add in as many additional members as they see 
         * fit. Beyond this point, you cannot add new members.
         */
        do {
            System.out.print ("Would you like to manually add a member (y/n)? ");
            text = console.readLine();
            
            if (text.compareToIgnoreCase("y") == 0) {
                manuallyAddMember(console);
            }
            
        } while (text.compareToIgnoreCase("y") == 0);
        
        /**
         * Prints out the member list.
         */
        for (int z = 0; z < Global.members.size(); z++)
            ((Customer) Global.members.get(z)).print();
        System.out.println();
        
        in = new BufferedReader(new FileReader(args[1]));
        createVideoList(in);
        
        /**
         * Allows for the user to add in as many additional films as they see 
         * fit. Beyond this point, you cannot add new movies.
         */
        do {
            System.out.print ("Would you like to manually add a film (y/n)? ");
            text = console.readLine();
            
            if (text.compareToIgnoreCase("y") == 0) {
                manuallyAddMovie(console);
            }
        } while (text.compareToIgnoreCase("y") == 0);
        
        /**
         * Prints out the film list.
         */
        Global.inventory.print();
        System.out.println();
        
        int choice = 9;
        do {
            menu();
            System.out.print ("Enter your choice: ");
            String temp = console.readLine();
            temp = temp.trim();
            temp = temp.replaceAll("[^0-9]", "");
            choice = Integer.parseInt(temp);
            
            switch (choice) {
                case 1:
                    System.out.print ("What is the name of the movie: ");
                    String i = console.readLine();
                    Video v = Global.inventory.search(i);
                    // if film is in the store's inventory, v will not be null
                    if (v != null)
                        System.out.println ("We carry " + i);
                    else
                        System.out.println ("We don't carry " + i);
                    break;
                case 2:
                    System.out.print ("What is your 6-digit ID: ");
                    i = console.readLine();
                    boolean isCustomer = false;
                    for (int j = 0; j < Global.members.size(); j++) {
                        // if we found a match between the members and the given 
                        // id
                        if (((Customer) Global.members.get(j)).getMembershipNumber() == Long.parseLong(i)) {
                            isCustomer = true;
                            System.out.print ("What is the name of the movie: ");
                            i = console.readLine();
                            ((Customer) Global.members.get(j)).rentVideo(i);
                            j = Global.members.size();
                        }                           
                    }
                    if (!isCustomer)
                        System.out.println ("You may not be a member of Blockbuster oryour entry is invalid");
                    break;
                case 3:
                    System.out.print ("What is your 6-digit ID: ");
                    i = console.readLine();
                    isCustomer = false;
                    for (int j = 0; j < Global.members.size(); j++) {
                        // if we found a match between the members and the given 
                        // id
                        if (((Customer) Global.members.get(j)).getMembershipNumber() == Long.parseLong(i)) {
                            isCustomer = true;
                            System.out.print ("What is the name of the movie: ");
                            i = console.readLine();
                            ((Customer) Global.members.get(j)).returnVideo(i);
                            j = Global.members.size();
                        }                           
                    }
                    if (!isCustomer)
                        System.out.println ("You may not be a member of Blockbuster oryour entry is invalid");
                    break;
                case 4:
                    System.out.print ("Enter the title: ");
                    String name = console.readLine();
                    v = Global.inventory.search(name);
                    // if film is in the store's inventory, v will not be null
                    if (v != null) {
                        if (v.isAvailable())
                            System.out.println (v.getMovieName() + " is in stock.");
                        else 
                            System.out.println (v.getMovieName() + " is not in stock.");
                    }
                    else
                        System.out.println ("We don't carry " + name + ".");
                    break;
                case 5:
                    // print the titles in the store
                    Global.inventory.printTitles();
                    break;
                case 6:
                    // print the titles and their details in the store
                    Global.inventory.print();
                    break;
                case 7:
                    // output the information in a valid account
                    System.out.print ("What is your 6-digit ID: ");
                    i = console.readLine();
                    isCustomer = false;
                    for (int j = 0; j < Global.members.size(); j++) {
                        if (((Customer) Global.members.get(j)).getMembershipNumber() == Long.parseLong(i)) {
                            isCustomer = true;
                            ((Customer) Global.members.get(j)).print();
                            ((Customer) Global.members.get(j)).printRentedVideos();
                            j = Global.members.size();
                        }                           
                    }
                    if (!isCustomer)
                        System.out.println ("You may not be a member of Blockbuster oryour entry is invalid");
                    break;
                case 9:
                    System.out.println ("Thank you for shopping at Blockbuster");
                    break;
                default:
                    System.out.println ("Invalid entry\n");
                    
            }
            System.out.println();
        } while (choice != 9);
    }
}
package finalproject;

/**
 * This project is used to establish a data structure which contains the various
 * employees within a singly linked list of rankings which are stored within a hash
 * table for ease of access.
 *
 * @author Nabil Azamy and Derek Holtberg
 * @version 5/12/15
 */
public class FinalProject 
{

    /**
     * This Main method first establishes the Employee listing objects to be used.
     * Then inserts them into ranked singly linked lists. Followed by storing those
     * ranked linked lists into a hashed structure for manipulation.
     *  
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //President Listing
        EmployeeListing President = new EmployeeListing(39605,"Jack Johnson",
                "Pres@SaphireInt.com","Orlando,Florida");
        //Vice Presidents
        EmployeeListing VicePresident1 = new EmployeeListing(59403,"Randy Thompson",
                "ViceChair@SaphireInt.com","Orlando,Florida");
        EmployeeListing VicePresident2 = new EmployeeListing(69583,"Jessica Stevenson",
                "JessIsCharge@SaphireInt.com","Orlando,Florida");
        //Comany Managers
        EmployeeListing Manager1 = new EmployeeListing(10293,"Chris Devens",
                "Mnba@SaphireInt.com","Jacksonville,Florida");
        EmployeeListing Manager2 = new EmployeeListing(68503,"Mike Berger",
                "BergerTime@SaphireInt.com","Tampa,Florida");
        EmployeeListing Manager3 = new EmployeeListing(69703,"Matt Berger",
                "WhatRises@SaphireInt.com","Miami,Florida");
        EmployeeListing Manager4 = new EmployeeListing(60894,"Jess O'Mara",
                "OMare@SaphireInt.com","Cape Coral,Florida");
        //Company Base Employees
        EmployeeListing BaseEmployee1 = new EmployeeListing(79402,"Steve Garf",
                "Ronal@SaphireInt.com","Jacksonville,Florida");
        EmployeeListing BaseEmployee2 = new EmployeeListing(29496,"Neil Gearson",
                "wanker@SaphireInt.com","Jacksonville,Florida");
        EmployeeListing BaseEmployee3 = new EmployeeListing(10293,"Francis Eaderson",
                "Jeanson@SaphireInt.com","Tampa,Florida");
        EmployeeListing BaseEmployee4 = new EmployeeListing(78592,"Noel Jackson",
                "OperHam@SaphireInt.com","Tampa,Florida");
        EmployeeListing BaseEmployee5 = new EmployeeListing(49302,"Juan Treason",
                "CasinoWaan@SaphireInt.com","Miami,Florida");
        EmployeeListing BaseEmployee6 = new EmployeeListing(67281,"Al Stanson",
                "HomeFrank@SaphireInt.com","Miami,Florida");
        EmployeeListing BaseEmployee7 = new EmployeeListing(28391,"George Clinton",
                "Quarter@SaphireInt.com","Cape Coral,Florida");
        EmployeeListing BaseEmployee8 = new EmployeeListing(69802,"Walter Johnson",
                "Penope@SaphireInt.com","Cape Coral,Florida");
 
        //Creating Singly Linked Lists of Employee Catagories
        SinglyLinkedList Prez = new SinglyLinkedList("President");
        Prez.insert(President);
        
        SinglyLinkedList VicePrez = new SinglyLinkedList("Vice President");
        VicePrez.insert(VicePresident1);
        VicePrez.insert(VicePresident2);
        
        SinglyLinkedList Manager = new SinglyLinkedList("Manager");
        Manager.insert(Manager1);
        Manager.insert(Manager2);
        Manager.insert(Manager3);
        Manager.insert(Manager4);
        
        SinglyLinkedList Base = new SinglyLinkedList("Base Employee");
        Base.insert(BaseEmployee1);
        Base.insert(BaseEmployee2);
        Base.insert(BaseEmployee3);
        Base.insert(BaseEmployee4);
        Base.insert(BaseEmployee5);
        Base.insert(BaseEmployee6);
        Base.insert(BaseEmployee7);
        Base.insert(BaseEmployee8);

        //Instantiate a Hash Table to Store the Employee Catagories
        Hashed hash = new Hashed(40);
        
        //Inserting the Employee categories into the Hash Table
        System.out.println("Insert Employee Linked Lists:\n");
        hash.insert(Prez);
        hash.insert(VicePrez);
        hash.insert(Manager);
        hash.insert(Base);
        System.out.println(hash);
        
        //Fetch Vice President Category Linked List within the Hash Table
        System.out.println("\nFetch Company Vice Presidents:\n");
        System.out.println(hash.fetch("Vice President"));
        
        //Update President Category from Jack Johnson to Bill Raderson 
        System.out.println("\nUpdate Company President:\n");
        System.out.println("******Former President****** \n" + hash.fetch("President")); //Fetch old President
        
        EmployeeListing NewPresident = new EmployeeListing(39605,"Bill Raderson",
                "NewPres@SaphireInt.com","Orlando,Florida");
        SinglyLinkedList NewPrez = new SinglyLinkedList("President"); //Create new Linked List
        NewPrez.insert(NewPresident); // Insert new president listing
        hash.update(NewPrez); //Update Current President Listing
        System.out.println("******New President****** \n" + hash.fetch("President"));
        
        //Fire Manager Chris from the linked list and output from the hash table
        System.out.println("\nFire Manager Chris Devens:\n");
        System.out.println("Former Managers: \n" + hash.fetch("Manager"));
        Manager.delete("Chris Devens"); // Removes Chris Devens from Manager Singly LInked List
        System.out.println("New Managers: \n" + hash.fetch("Manager"));
        
        //Promote Base Employee to a manager position
        System.out.println("\nPromote Base Employee Steve Garf to Manager:\n");
        System.out.println("Before Promotion:\n");
        System.out.println("******Managers******\n" + hash.fetch("Manager") + "\n");
        System.out.println("******Base Employees******\n" + hash.fetch("Base Employee") + "\n");
        
        EmployeeListing Manager5 = BaseEmployee1; //Create new Manager
        Base.delete("Steve Garf"); //Delete former base employee
        Manager.insert(Manager5); //Insert new Manager
        System.out.println("After Promotion:\n");
        System.out.println("******Managers******\n" + hash.fetch("Manager") + "\n");
        System.out.println("******Base Employees******\n" + hash.fetch("Base Employee") + "\n");
        
        
        
        
    }
    
}

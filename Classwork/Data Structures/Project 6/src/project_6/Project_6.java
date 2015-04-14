package project_6;

/**
 * This is a 
 * 
 * @author Nabil Azamy & Derek
 */
public class Project_6
{

 /**
  * The main method demonstrates the insert, list, delete, fetch, and update commands of both the 
  * Singly linked list and iterator subclass.
  * 
  * @param args the command line arguments
  */
    public static void main(String[] args) 
    {
        SinglyLinkedListIterator list = new SinglyLinkedListIterator();
        ProfessorListing professorListing;
        
        list.insert( new ProfessorListing("Bill Evens", "Chemistry", "Science Literature",
        "Science@gmail.com") );
        list.insert( new ProfessorListing("Jill Stevens", "Math", "Discerete Mechanics",
        "FrySci@msn.com") );
        list.insert( new ProfessorListing("Johanis Finkter", "Physics", "Fluid Dynamics",
        "KornGuy@yahoo.com") );
        list.insert( new ProfessorListing("Sally Darken", "English", "Roman Literature",
        "AllRoadsGal@hotmail.com") );
        
        
        System.out.println("\nIterator will run through the inserted Singly Linked List Professors:");
        while ( list.i.hasNext() )
        {
            System.out.println( list.i.next() );
        }
        
        list.i.reset();
        
        
        System.out.println("\nProfessor Sall Darken will be removed from the list:");
        list.delete("Sally Darken");
        System.out.print(list);
        
        
        System.out.println("\nProfessor Sall Darken will be added once again to the list:");
        list.insert( new ProfessorListing("Sally Darken", "English", "Roman Literature",
        "AllRoadsGal@hotmail.com") );
        System.out.print(list);
        
        System.out.println("\nA fetch command to aquire Bill Evens' department:");
        System.out.print(list.fetch("Bill Evens").getDepartment()+ "\n");
        
        System.out.println("\nIterator will run through the list and add Dr. to each professor");
        while ( list.i.hasNext() )
        {
            professorListing = list.i.next();
            professorListing.setName( "Dr." + professorListing.getName() );
            list.i.set(professorListing);
        }
        
        list.i.reset();
        
        while ( list.i.hasNext() )
        {
            System.out.println( list.i.next() );
        }
    } 
}
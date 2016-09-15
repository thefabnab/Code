//Copyright:   Copyright (c) 2001
//Author:      Carl B. Struck
//Company:     SCCC West
//Description: Testing the classes Administrator and ClassroomFaculty.

import javax.swing.JOptionPane;

public class Driver1
{
    public static void main(String[] args)
    {
        CommunityMember[] member = new CommunityMember[4];

        member[0] = new Administrator("Michael", "Brewer", 2299, "Central", "President");
        member[1] = new Administrator("Alice", "Werner", 680, "Englis", "Asst Dean");
        member[2] = new ClassroomFaculty("Rodger", "Tallon", 3349, "Math", "assistant", 15);
        member[3] = new ClassroomFaculty("Joseph", "Price", 6702, "Computer", "prof", 29);

        for (int index = 0; index < member.length; index++)
        {
            JOptionPane.showMessageDialog(null, member[index] );
        }

        System.exit(0);
    }
}



//  Description: The Assignment6 class creates a Tabbed Pane with
//               two tabs, one for Club Creation and one for
//               Club Selection.

public class Club
 {
   private String clubName;
   private int numberOfMembers;
   private String university;

   //Constructor to initialize all member variables
   public Club()
    {
      clubName = "?";
      university = "?";
      numberOfMembers = 0;
    }

   //Accessor methods
   public String getClubName()
    {
      return clubName;
    }

   public String getUniversity()
    {
      return university;
    }

   public int getNumberOfMembers()
    {
	   return numberOfMembers;
	}

   //Mutator methods
   public void setClubName(String someClubName)
    {
     clubName = someClubName;
    }

   public void setUniversity(String someUniversity)
    {
     university = someUniversity;
    }

   public void setNumberOfMembers(int someNumber)
    {
        numberOfMembers = someNumber;
    }

   //toString() method returns a string containg its name, number of members, and university
   public String toString()
    {
      String result = "\nClub Name:\t\t" + clubName
                    + "\nNumber Of Members:\t" + numberOfMembers
                    + "\nUniversity:\t\t" + university
                    + "\n\n";
      return result;
     }
  }

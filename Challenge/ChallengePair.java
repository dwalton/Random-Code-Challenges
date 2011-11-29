 
/**
 * This class contains a pair of Challenges
 * Used for providing a set of 2 questions and answers
 */
public class ChallengePair
{
  public Challenge first;
  public Challenge second;
  
  public ChallengePair(Fragment q0, String a0,
                       Fragment q1, String a1)
  {
    first = new Challenge(q0, a0);
    second = new Challenge(q1, a1);
  }
  
  public ChallengePair(Challenge f, Challenge s)
  {
    first = f;
    second = s;
  }
  
  public ChallengePair()
  {
    //set first and second manually, later
    first = null;
    second = null;
  }
  
  public String toString()
  {
    return first.toString() + "\n" + second.toString();
  }
  
  public String toGoogleForm()
  {
    return "\"" + first.question.toString().replaceAll("\"", "\"\"") + "\"";
  }
  
}
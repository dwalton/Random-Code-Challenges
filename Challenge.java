
/**
 * This class contains an EnglishAST for a question
 * and a Solution for answer.
 */
public class Challenge
{
  public Fragment question;
  public String answer;
  
  public Challenge(Fragment q, String a)
  {
    question = q;
    answer = a;
  }
  public Challenge()
  {
    //set question and answer later by hand.
    question = null;
    answer = null;
  }
  
  public String toString()
  {
    return question.toString() + "\n" + answer.toString();
  }
}
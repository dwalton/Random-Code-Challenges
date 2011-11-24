
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
    String ret = "";
    if(question != null)
      ret = ret + question.toString() + "\n";
    if(answer != null)
      ret = ret + answer.toString();
    return ret;
  }
}
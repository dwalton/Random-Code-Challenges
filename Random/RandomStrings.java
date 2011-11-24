import java.util.Random;

/** RandomStrings
 * This class provides several methods for generating random
 *   Strings (possibly for use as variable/function names
 * @author Doug Walton
 * @version 0.1
 */
public class RandomStrings
{
  private Random random = new Random();
  private char[] charset = new char[27];

  /** 
   * Default constructor to initialize default values
   */
  public RandomStrings()
  {
    initDefaultCharset();
  }
  /**
   * Constructor that with a changeable character set
   * @param set a character set
   */
  public RandomStrings(char[] set)
  {
    charset = set;
  }

  /**
   * Sets the character set for random Strings to a-z with _
   */
  private void initDefaultCharset()
  {
    int i = 0;
    for(char c = 'a'; c < 'z'; c++)
      charset[i++] = c;
    charset[i++] = '_';
  }

  /**
   * Generate a random String with max length 'maxlength' (used for variable/function names)
   * @param maxlength the maximum length of the String to return
   */
  public String randomName(int maxlength)
  {
    int len = random.nextInt(maxlength) + 1;
    char[] name = new char[len];

    for(int i = 0; i < len; i++)
    {
      if(i == 0)
        name[i] = charset[random.nextInt(charset.length - 2)]; //-1 so not _ to start
      else
        name[i] = charset[random.nextInt(charset.length)];
    }

    return new String(name);
  }
}

import java.util.ArrayList;

/**
 * The most basic Fragment type
 * Used for simple strings that need to be inserted or added in a sentence
 */
public class StringFragment extends Fragment
{
  
  public StringFragment(String s)
  {
    text = s;
  }
  
  public StringFragment(Fragment f)
  {
    text = f.text;
  }
  
  public String toString()
  {
    return text;
  }
  
}  

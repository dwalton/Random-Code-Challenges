import java.util.ArrayList;

/**
 * The Fragment for function calls
 * See also FunctionCallArgsFragment
 * Takes a function name, no children really expected
 * May add children as arguments later, but kept separate
 *   at the moment to make it easier to add them to the 
 *   end of the question
 */
public class FunctionCallExpFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public String text;
  
  public FunctionCallExpFragment(String fname)
  {
    text = "a function call to " + fname;
    children = new ArrayList<Fragment>();
  }
  public FunctionCallExpFragment(String fname, ArrayList<Fragment> kids)
  {
    text = "a function call to " + fname;
    children = kids;
  }
  
  public void add(Fragment f)
  {
    children.add(f);
  }
  
  public String toString()
  {
    return text;
  }
  
}



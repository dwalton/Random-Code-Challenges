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
  public String name;
  boolean isExp;
  
  public FunctionCallExpFragment(String fname)
  {
    name = fname;
    children = new ArrayList<Fragment>();
    isExp = true;
  }
  public FunctionCallExpFragment(String fname, ArrayList<Fragment> kids)
  {
    name = fname;
    children = kids;
    isExp = true;
  }
  public FunctionCallExpFragment(String fname, ArrayList<Fragment> kids, boolean b)
  {
    name = fname;
    children = kids;
    isExp = b;
  }
  
  public void add(Fragment f)
  {
    children.add(f);
  }
  
  public String toString()
  {
    if(!isExp)
    {
      return "call the function " + name;
    }
    else
      return "a function call to " + name;
  }
  
}



import java.util.ArrayList;

/**
 * The Fragment for JUnit tests
 * Should only have 2 children, 
 *   the function to call(a FunctionCallExpFragment) and 
 *   the expected value(a StringFragment)
 */
public class JUnitFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public String text;
  
  public JUnitFragment(ArrayList<Fragment> kids)
  {
    text = "junit test for the function <func>.  We expect the function will return the value <val>.";
    children = kids;
  }
  
  public String toString()
  {
    return "junit test for " + children.get(0).toString() + ".  We expect the function will return the value " + children.get(1).toString();
  }
  
}


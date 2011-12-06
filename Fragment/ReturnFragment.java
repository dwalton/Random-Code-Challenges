import java.util.ArrayList;

/**
 * The Fragment for return statements
 * Should only have one child, will ignore the rest if there are more
 */
public class ReturnFragment extends Fragment
{
  
  public ReturnFragment()
  {
    children = new ArrayList<Fragment>();
  }
  public ReturnFragment(ArrayList<Fragment> kids)
  {
    children = kids;
  }
  
  public String toString()
  {
    return "returns " + children.get(0).toString();
  }
  
}



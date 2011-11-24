import java.util.ArrayList;

/**
 * The Fragment for return statements
 * Should only have one child, will ignore the rest if there are more
 */
public class ReturnFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public String text;
  
  public ReturnFragment()
  {
    text = "returns <0>";
    children = new ArrayList<Fragment>();
  }
  public ReturnFragment(String s)
  {
    text = s;
    children = new ArrayList<Fragment>();
  }
  public ReturnFragment(String s, ArrayList<Fragment> kids)
  {
    text = s;
    children = kids;
  }
  
  public void add(Fragment f)
  {
    children.add(f);
  }
  
  public String toString()
  {
    return text.replaceFirst("<0>", children.get(0).toString());
  }
  
}



import java.util.ArrayList;

public class FunctionCallExpFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public FunctionCallExpFragment(String fname)
  {
    text = "a function call to " + fname;
    children = new ArrayList<Fragment>();
  }
  
  public void add(Fragment f)
  {
    children.add(f);
    children.get(children.size()-1).parent = this;
    children.get(children.size()-1).checkParent();
  }
  
  public void checkParent()
  {
    super.checkParent();
  }
  
  public String toString()
  {
    return text;
  }
  
}



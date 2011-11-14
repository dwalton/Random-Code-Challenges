import java.util.ArrayList;

public class ReturnFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public ReturnFragment()
  {
    text = "return <0>";
    children = new ArrayList<Fragment>();
  }
  public ReturnFragment(String s)
  {
    text = s;
    children = new ArrayList<Fragment>();
  }
  
  public String toString()
  {
    return super.toString();
  }
  
}



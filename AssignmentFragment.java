import java.util.ArrayList;

public class AssignmentFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public AssignmentFragment()
  {
    text = "assign <1> to <0>";
    children = new ArrayList<Fragment>();
  }
  public AssignmentFragment(String s)
  {
    text = s;
    children = new ArrayList<Fragment>();
  }
  
}


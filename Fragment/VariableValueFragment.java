import java.util.ArrayList;

public class VariableValueFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public VariableValueFragment()
  {
    text = "<0> = <1>";
    children = new ArrayList<Fragment>();
  }
  
  public VariableValueFragment(String var, String val)
  {
    text = var + " = " + val;
    children = null;
  }
  
}  


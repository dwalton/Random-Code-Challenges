import java.util.ArrayList;

/**
 * The Fragment for parameters for functions
 * ParameterFragment should have an unlimited number of children
 *   (it will be limited in RandomCode.java)
 * Mostly for use with FunctionFragment as its first child
 */
public class ParameterFragment extends Fragment
{
  
  public ParameterFragment(ArrayList<Fragment> kids)
  {
    children = kids;
  }
  
  public String toString()
  {
    String ret = "";
    if(children.size() == 1)
      ret = "1 parameter: ";
    else
      ret = children.size() + " parameters: ";
    
    for(int i = 0; i < children.size(); i++)
    {
      ret = ret + children.get(i).toString();
      if(i < children.size()-2)
        ret = ret + ", ";
      else if(i == children.size()-2)
        ret = ret + ", and ";
    }
    
    if(children.isEmpty())
    {
      ret = "no parameters";
    }
    
    return ret;
  }
}
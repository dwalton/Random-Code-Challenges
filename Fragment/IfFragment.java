import java.util.ArrayList;

/**
 * The Fragment for if statements
 * IfFragment can potentially have 4 children
 * The first child should be a simple/complex boolean expression
 * The second and third should be statements
 *   (like ReturnFragment, IfFragment, FunctionCallExpFragment,
 *   AssignmentFragment... etc.) for the then/else clauses
 * MAY REMOVE - the last (fourth) child should be 
 *   what should come after the if statements
 */
public class IfFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public String text;
  
  public IfFragment()
  {
    text = "if <0> evaluates to true then <1><2><3>";
    children = new ArrayList<Fragment>();
  }
  public IfFragment(ArrayList<Fragment> kids)
  {
    children = kids;
  }
  
  public String toString()
  {
    String ret = "if ";
    String temp = "";
    
    int kidcount = children.size();
    
    //if at least a guard and a then
    if(kidcount >= 2)
    {
      temp = ret + children.get(0).toString() 
        + " then, "
        + children.get(1).toString();
      ret = temp;
    }
    
    //if there's an else (TODO: or maybe an after block...? figure out later)
    if(kidcount >= 3)
    {
      temp = ret + "; otherwise " 
        + children.get(2).toString();
      ret = temp;
    }
    
    return ret;
  }
  
}




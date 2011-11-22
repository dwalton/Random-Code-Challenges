import java.util.ArrayList;

/**
 * The Fragment used for assignment statements
 * This class will have two children,
 *   the first child will be a StringFragment of the variable
 *   we are assigning a value to and 
 *   the second child will be any type of expression/value Fragment
 *   (ie FunctionCallExpFragment, StringFragment, 
 *   ArithmeticFragment, BooleanFragment)
 */
public class AssignmentFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public String text;
  
  public AssignmentFragment(ArrayList<Fragment> kids)
  {
    text = "assign <1> to <0>";
    children = kids;
  }
  
  public String toString()
  {
    return "assign " + children.get(1).toString() + " to " + children.get(0).toString();
  }
  
}


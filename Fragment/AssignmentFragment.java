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
    text = "assigns <0> to <1>";
    children = kids;
  }
  
  public String toString()
  {
    return "assigns " + children.get(0).toString() + " the value of " + children.get(1).toString();
  }
  
}


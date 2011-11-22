import java.util.ArrayList;

/**
 * This class is for boolean expressions
 * It should have an operator and any number of children
 *   (although some operators have specific requirements, 
 *   ie NOT should have one child, while LESS_THAN will have 2
 *   and AND/OR may be used with several children for more 
 *   complex problems)
 * See BoolOps for types of operators
 */
public class BooleanFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public String text;
  
  public boolean complex;
  
  public BoolOps operator;
  
  public BooleanFragment(BoolOps op, ArrayList<Fragment> kids)
  {
    children = kids;
    operator = op;
  }
  
  public String toString()
  {
    String ret = "";
    String temp = "";
    if(children.size() == 1 
      && (operator.equals(BoolOps.NOT) || operator.equals(BoolOps.ITSELF)))
      ret = operator.opName() + " " + children.get(0).toString();
    else if(children.size() == 2)
    {  
      temp = ret + children.get(0).toString()
        + " " + operator.opName() 
        + " " + children.get(1).toString();
      ret = temp;
    }
    else if(children.size() > 2 && (operator.equals(BoolOps.AND) || operator.equals(BoolOps.OR)))
    {
      String[] one = {"one", "is"};
      if(operator.equals(BoolOps.AND))
      {  
        one[0] = "all";
        one[1] = "are";
      }
      ret = ret + " whether " + one[0] + " of the following " + one[1] + " true: ";
      for(int i = 0; i < children.size(); i++)
      {
        ret = ret + children.get(i).toString();
        if(i < children.size()-2)
          ret = ret + ", ";
        else if(i == children.size()-2)
          ret = ret + ", and ";
      }
    }
    return ret;
  }
}
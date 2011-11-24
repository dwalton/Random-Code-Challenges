import java.util.ArrayList;

/**
 * The Fragment for simple and complex math expressions
 * It will have two children and an operator
 *   either child can be a value (StringFragment/FunctionCallExpFragment)
 *   or another ArithmeticFragment
 * See Ops for operator information
 * For example: add the result of adding 4 and x, and function call to g()
 *   add has the first child of another add (with children 4 and x)
 *   and the second child is a function call to g()
 */
public class ArithmeticFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public String text;
  
  public boolean subLevel;
  
  public Ops operator;
  
  public ArithmeticFragment(Ops op)
  {
    operator = op;
    text = operator.opName() + " ";
    
    children = new ArrayList<Fragment>();
    subLevel = false;
  }
  public ArithmeticFragment(Ops op, ArrayList<Fragment> kids)
  {
    operator = op;
    children = kids;
    subLevel = false;
  }
  public ArithmeticFragment(Ops op, ArrayList<Fragment> kids, boolean sub)
  {
    operator = op;
    children = kids;
    subLevel = sub;
  }
  
//   public void checkParent()
//   {
//     //if its more complex math
//     if(parent instanceof ArithmeticFragment)
//     {
//       text = "the result of " 
//         + text.replaceFirst(operator.opName(),
//                             operator.addIng());
//     }
//   }
  
  public void add(Fragment f)
  {
    children.add(f);
  }
  
  /**
   * Makes the Arithmetic part of a sentence
   * operator name + value + (and/by/from) + value
   */
  public String toString()
  {
    String ret;
    if(subLevel)
      ret = "the result of " + operator.addIng() + " ";
    else
      ret = operator.opName() + " ";
    switch (operator)
    {
      case ADD:
      case MULTIPLY:
      case DIVIDE:
      case MODULO:
        ret = ret + "<0> " 
        + operator.connectingWord()
        + " <1>";
      break;
      case SUBTRACT:
        ret = ret + "<1> " 
        + operator.connectingWord()
        + " <0>";
      break;
      default:
        break;
    }
    String t0 = "";
    String rep = "";
    for(int i = 0; i < children.size(); i++)
    {
      t0 = ret;
      rep = "<"+i+">";
      ret = t0.replaceFirst("<"+i+">", children.get(i).toString());
    }
    
    return ret;
  }

}

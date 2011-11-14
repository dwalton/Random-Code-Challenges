import java.util.ArrayList;

public class ArithmeticFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public Ops operator;
  
  public ArithmeticFragment(Ops op)
  {
    operator = op;
    text = operator.opName() + " ";
    
    switch (operator)
    {
      case ADD:
      case MULTIPLY:
      case DIVIDE:
      case MODULO:
        text = text + "<0> " 
          + operator.connectingWord()
          + " <1>";
        break;
      case SUBTRACT:
        text = text + "<1> " 
          + operator.connectingWord()
          + " <0>";
        break;
      default:
        break;
    }
    
    children = new ArrayList<Fragment>();
  }
  
  public void checkParent()
  {
    //if its more complex math
    if(parent instanceof ArithmeticFragment)
    {
      text = "the result of " 
        + text.replaceFirst(operator.opName(),
                            operator.addIng());
    }
  }
  
  public void add(Fragment f)
  {
    children.add(f);
    children.get(children.size()-1).parent = this;
    children.get(children.size()-1).checkParent();
  }
  
  public String toString()
  {
    String ret = text;
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

import java.util.ArrayList;

/**
 * The Fragment for adding specific arguments to function calls
 *   at the end of a question (as a separate sentence)
 * Currently each FunctionCallArgsFragment is a separate child
 *   of the root of the question (EnglishAST)
 * Each child of this class is a simple StringFragment
 *   but later we will probably expand it to all
 *   expression types
 */
public class FunctionCallArgsFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public String text;
  
  public FunctionCallArgsFragment(String fname)
  {
    text = "The function " + fname 
      + " takes <num> arguments: ";
    children = new ArrayList<Fragment>();
  }
  public FunctionCallArgsFragment(String fname, ArrayList<Fragment> kids)
  {
    text = "The function " + fname 
    + " takes <num> arguments: ";
    children = kids;
  }
  
  
  public void add(Fragment f)
  {
    children.add(f);
  }
  
  
  public String toString()
  {
    String t0 = text;
    String t1 = "";
    int size = children.size();
    
    t1 = t0.replaceFirst("<num>", String.valueOf(size));
    t0 = t1;
    
    
    for(int i = 0; i < size; i++)
    {
      t0 = t0 + "<"+i+">";
      if(i == size-2 && size != 1)
        t0 = t0 + " and ";
      else if(i != size-1 && i != size)
        t0 = t0 + ", ";
    }
    
    if(size > 1)
      t1 = t0 + " in that order";
    else if(size == 0)
      t1 = t0.replaceFirst(": ","");
    else
    {
      t1 = t0.replaceFirst("arguments", "argument");
      t1 = t1 + ".";
    }
    t0 = t1;
    
    text = t0;
    
    for(int i = 0; i < children.size(); i++)
    {
      t1 = t0;
      t0 = t1.replaceFirst("<"+i+">", children.get(i).toString());
    }
//     t0 = super.toString();
    
    return t0;
  }
}



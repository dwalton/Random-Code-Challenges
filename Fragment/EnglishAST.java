import java.util.ArrayList;
/**
 * This class represents an English problem statement
 * It contains several sentences that will be put
 *   together from left to right
 * The first child is the main part of the problem
 *   and the rest are extra sentences with more
 *   detail (ie FunctionCallArgsFragment for giving
 *   the values to use as arguments for a function)
 */
public class EnglishAST extends Fragment
{
  
  public EnglishAST(String n, ArrayList<Fragment> c)
  {
    text = n;
    children = c;
  }
  
  public EnglishAST(String root)
  {
    text = root;
    children = new ArrayList<Fragment>();
  }
  
  public EnglishAST(Fragment root)
  {
    text = root.text;
    children = root.children;
  }
  
  public void add(Fragment f)
  {
    children.add(f);
  }
  
  public boolean hasFunctions()
  {
    return children.size() > 1;
  }
  
  public String toString()
  {
    String ret = text;
    if(ret.equals(""))
      ret = " ";
    String t0 = "";
    for(int i = 0; i < children.size(); i++)
    {
//       System.out.println(i);
      t0 = ret;
      ret = t0 + " " + children.get(i).toString() + ". ";
    }
    
    if(children.size() == 0)
    {
      ret = ret + ".";
    }
    return ret;
  }
  
}
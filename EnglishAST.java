import java.util.ArrayList;
/**
 * This class represents an English problem statement
 * It contains several sentences that can will be put
 * together from left to right.
 */
public class EnglishAST extends Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public EnglishAST(String n, ArrayList<Fragment> c)
  {
    text = n;
    children = c;
  }
  
  public EnglishAST(String root)
  {
    text = root;
    parent = null;
    children = new ArrayList<Fragment>();
  }
  
  public EnglishAST(Fragment root)
  {
    text = root.text;
    parent = null;
    children = root.children;
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
    if(ret.equals(""))
      ret = " ";
    String t0 = "";
    for(int i = 0; i < children.size(); i++)
    {
//       System.out.println(i);
      t0 = ret;
      ret = t0 + " " + children.get(i).toString();
    }
    
    if(children.size() == 0)
    {
      ret = ret + ".";
    }
    return ret;
  }
  
}
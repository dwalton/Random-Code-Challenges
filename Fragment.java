import java.util.ArrayList;

/**
 * This interface provides a structure for fragments that 
 * can either be a list of string fragments or a 
 * single string fragment
 */
public abstract class Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public Fragment(String s)
  {
    text = s;
    parent = null;
    children = new ArrayList<Fragment>();
  }
  public Fragment()
  {
    text = null;
    parent = null;
    children = new ArrayList<Fragment>();
  }
  
  public boolean isLeaf()
  {
    return children == null || children.size() == 0;
  }
  public boolean isRoot()
  {
    return parent == null;
  }
  
  
  
  public String toString()
  {
//     String t0 = text;
//     String t1 = "";
//     for(int i = 0; i < children.size(); i++)
//     {
//       if(!children.get(i).isLeaf() && !isRoot())
//       {
//         t1 = t0.replaceFirst("<"+i+">",
//                              children.get(i).toString());
//         if(t1.equals(t0))
//           t1 = t0 + children.get(i).toString();
//         t0 = t1;
//       }
//       else
//       {
//         t1 = t0 + children.get(i).toString();
//         t0 = t1;
//       }
//     }
//     return t0;
    return text;
  }
  
  public void add(Fragment f)
  {
    children.add(f);
    children.get(children.size()-1).parent = this;
    children.get(children.size()-1).checkParent();
  }
  
  public void checkParent()
  {
    text = text;
  }
  
  public boolean equals(Fragment f)
  {
    return f != null && (f instanceof Fragment) && text.equals(f.text);
  }
}
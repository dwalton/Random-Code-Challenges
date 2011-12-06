import java.util.ArrayList;

/**
 * This abstract class provides a structure for fragments that 
 * can either be a list of string fragments or a 
 * single string fragment
 */
public abstract class Fragment
{
  public ArrayList<Fragment> children;
  public String text;
  
  public Fragment(String s, ArrayList<Fragment> kids)
  {
    text = s;
    children = kids;
  }
  public Fragment(String s)
  {
    text = s;
    children = new ArrayList<Fragment>();
  }
  public Fragment()
  {
    text = "";
    children = new ArrayList<Fragment>();
  }
  
  public void add(Fragment f)
  {
    children.add(f);
  }
  
  
  
  public String toString()
  {
    return text;
  }
    
  
  public boolean equals(Fragment f)
  {
    return f != null && (f instanceof Fragment) && text.equals(f.text);
  }
}
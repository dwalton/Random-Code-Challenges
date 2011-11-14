import java.util.ArrayList;

public class IfFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public IfFragment()
  {
    text = "if <0> evaluates to true then <1><2><3>";
    children = new ArrayList<Fragment>();
  }
  
  public String toString()
  {
    String t0 = text;
    String t1 = "";
    
    //if no else
    if(children != null && children.size() < 4)
    {
      for(int i = 0; i < children.size(); i++)
      {
        if(i == 2)
          t1 = t0.replaceFirst("<2>", "; otherwise " + children.get(i).toString() + ".");
        else
          t1 = t0.replaceFirst("<"+i+">",
                            children.get(i).toString());
        t0 = t1;
      }
      t1 = t0.replaceFirst("<3>", "");
      t0 = t1;
    } //there is an else
    else if(children != null && children.size() == 4)
    {
      t1 = t0.replaceFirst("<0>",
                           children.get(0).toString());
      t0 = t1;
      
      t1 = t0.replaceFirst("<1>",
                           children.get(1).toString());
      t0 = t1;
      
      t1 = t0.replaceFirst("<2>", "; otherwise " 
        + children.get(2).toString() + ".");
      t0 = t1;
      
      t1 = t0.replaceFirst("<3>",
                           children.get(3).toString());
      t0 = t1;
    }
    return t0;
  }
  
}




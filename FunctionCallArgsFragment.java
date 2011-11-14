import java.util.ArrayList;

public class FunctionCallArgsFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public FunctionCallArgsFragment(String fname)
  {
    text = "The function " + fname 
      + " takes <num> arguments: ";
    children = new ArrayList<Fragment>();
  }
  
  public void add(Fragment f)
  {
    children.add(f);
    children.get(children.size()-1).parent = this;
    children.get(children.size()-1).checkParent();
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
      t1 = t0 + " in that order.";
    else if(size == 0)
      t1 = t0.replaceFirst(": ",".");
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



import java.util.ArrayList;

public class EvalWithFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public EvalWithFragment()
  {
    text = "with";
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
    String t1 = text;
    int size = children.size();
    
    if(size == 0)
      t1 = t0.replaceFirst("with",".");
    else
    {
      for(int i = 0; i < size; i++)
      {
        t0 = t0 + " <"+i+">";
        if(i == size-2 && size != 1)
          t0 = t0 + " and ";
        else if(i != size-1 && i != size)
          t0 = t0 + ", ";
      }
      t1 = t0;
    }
    
    text = t1;
    t0 = t1;
    
    for(int i = 0; i < children.size(); i++)
    {
      t1 = t0;
      t0 = t1.replaceFirst("<"+i+">", children.get(i).toString());
    }
    
    return t0;
  }
  
}  



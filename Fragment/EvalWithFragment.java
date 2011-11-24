import java.util.ArrayList;

/**
 * This class is usually for the second "evaluate it" questions
 * It provides an attachment for variable/function call values
 * by using a "with" clause
 * For example: "Now evaluate it with x = 3 and fne() = 11"
 *   x = 3 and fne() = 11 are separate StringFragment children
 */
public class EvalWithFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public String text;
  
  public EvalWithFragment()
  {
    text = "with";
    children = new ArrayList<Fragment>();
  }
  public EvalWithFragment(ArrayList<Fragment> kids)
  {
    children = kids;
    text = "with";
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



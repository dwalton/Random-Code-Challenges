import java.util.ArrayList;

/**
 * The Fragment for functions
 * FunctionFragment should take a 'public' boolean, a type, and a name
 * This class should have 2 children:
 *   First a ParameterFragment
 *   Second a ReturnFragment
 */
public class FunctionFragment extends Fragment
{
  public String text;
  public ArrayList<Fragment> children;
  
  public boolean pub;
  public String name;
  public Types type;
  
  public FunctionFragment(boolean p, Types t, String n, ArrayList<Fragment> kids)
  {
    pub = p;
    type = t;
    name = n;
    children = kids;
  }
  
  public String toString()
  {
    String ret = "";
    
    if(pub)
      ret = "public ";
    else
      ret = "private ";
    
    ret = ret + type.typeName() + " function that " + children.get(1).toString() 
      + ". It should take " + children.get(0).toString();
    
    return ret;
  }
}
import java.util.ArrayList;

public class StringFragment extends Fragment
{
  public ArrayList<Fragment> children;
  public Fragment parent;
  public String text;
  
  public StringFragment(String s)
  {
    text = s;
    children = null;
  }
  
  public StringFragment(Fragment f)
  {
    text = f.text;
    children = null;
  }
  
  public String toString()
  {
    return text;
  }
  
}  

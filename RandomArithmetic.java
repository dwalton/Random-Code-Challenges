import java.util.Random;
import java.util.ArrayList;

public class RandomArithmetic
{
  private Random random;
  private static int maxint = 20;
  private double maxdouble;
  private final String words = "Provide the expression you would use to ";
  private final String words2 = "Now evaluate the expression";
  private final String wordsVar = "Now evaluate the expression you just made with <num> = <num>";
  private final String complex = "";
  
  

  /**
   * Generic constructor
   */
  public RandomArithmetic()
  {
    random = new Random();
  }
  
  /**
   * @param max maximum value for numbers
   */
  public RandomArithmetic(int max)
  {
    random = new Random();
    maxint = max;
    maxdouble = 0;
  }

  /**
   * @param max maximum double value for numbers (NOT USED YET)
   */
  public RandomArithmetic(double max)
  {
    random = new Random();
    maxint = 0;
    maxdouble = max;
  }

  /**
   * REMOVED - Generate the problem text with tags replaced
   * @param verb if true adds "ing" to the operation
   * @param sentence the basic sentence with tags that need to be replaced
   * @param numl the left variable/number
   * @param numr the right variable/number
   */
//   public String generateProblem(boolean verb, String sentence, Ops op, String numl, String numr)
//   


 
  
  /**
   * Generate a random arithmetical expression
   * @param isSub true if this is within a more complex expression
   * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
   */
  public ChallengePair generateMath(boolean isSub) 
  {
    EnglishAST q0 = new EnglishAST("Provide the expression you would use to ");
    EnglishAST q1 = new EnglishAST(words2);
    ChallengePair left = getVal(true);
    ChallengePair right = getVal(true);
    String a0,a1;
//     int lf = random.nextInt(maxint);
//     int rt = random.nextInt(maxint);
    int lf = Integer.parseInt(left.second.answer);
    int rt = Integer.parseInt(right.second.answer);
    int sol = 0;
    Ops operation = Ops.generateOp();
    
    switch (operation)
    {
      case ADD:
        sol = lf + rt;
        break;
      case SUBTRACT:
        sol = lf - rt;
        break;
      case MULTIPLY:
        sol = lf * rt;
        break;
      case DIVIDE:
        if(rt == 0)
        {
          rt++;//so no 1/0
          if(right.first.answer.equals("0"))
            right.first.answer = "1";
        }
        sol = lf / rt;
        break;
      case MODULO:
        if(rt == 0)
        {
          rt++;//so no 1/0
          if(right.first.answer.equals("0"))
            right.first.answer = "1";
        }
        sol = lf % rt;
        break;
      default:
        System.exit(1);
        break;
    }
    a0 = left.first.answer + " " + operation + " " + right.first.answer;
    a1 = String.valueOf(sol);
    
    ArrayList<Fragment> m = new ArrayList<Fragment>();
    m.add(left.first.question);
    m.add(right.first.question);
    ArithmeticFragment math = new ArithmeticFragment(operation, m, isSub);
    q0.add(math);
    
    EvalWithFragment with = new EvalWithFragment();
    
    //if left is a function call
    if(left.first.question instanceof FunctionCallExpFragment)
    {
      //add args to q0 as new sentence
      q0.add(left.second.question);
      //add value to with for q1
      with.add(new StringFragment(left.first.answer + " = " + left.second.answer));
    }
    //if left is a variable
    else if(!left.first.answer.equals(left.second.answer))
    {
      //add value of variable for q1
      with.add(new StringFragment(left.first.answer + " = " + left.second.answer));
    }
    
    //if right is a function call
    if(right.first.question instanceof FunctionCallExpFragment)
    {
      //add args to q1 as new sentence
      q0.add(right.second.question);
      //add value of function call to with for q1
      with.add(new StringFragment(right.first.answer + " = " + right.second.answer));
    }
    //if right is a variable
    else if(!right.first.answer.equals(right.second.answer))
    {
      //add value of right for q1
      with.add(new StringFragment(right.first.answer + " = " + right.second.answer));
    }
    
    if(with.children.size() > 0)
    {
      q1.add(with);
    }
    
    return new ChallengePair(q0,a0,q1,a1);
  }

  /**
   * Generate a complex problem that can have multiple operations
   * Just starting with 1 layer of nested stuff
   * @param nests REMOVED - how deep to nest problems (only one level implemented)
   * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
   */
  public ChallengePair generateComplex()
  {
    int sol = 0;
    
    EnglishAST q0 = new EnglishAST("Provide the expression you would use to ");
    EnglishAST q1 = new EnglishAST(words2);
    ChallengePair left, right;
    ArrayList<Fragment> funcs = new ArrayList<Fragment>();
    ArrayList<Fragment> l2with = new ArrayList<Fragment>();
    String a0,a1;
    int lf, rt;
    
    ChallengePair level2 = generateMath(true);

    Ops operation = Ops.generateOp();

    int lorr = random.nextInt(2);
    if(lorr == 0)
    {
      if(level2.second.question.children.size() > 0)
      {
        left = new ChallengePair(
          ((EnglishAST)level2.first.question).children.get(0), //ArithmeticFragment
          level2.first.answer, //string answer
          ((EnglishAST)level2.second.question).children.get(0), //EvalWithFragment
          level2.second.answer //number answer
        );
      
        l2with.addAll(level2.second.question.children.get(0).children);
      }
      else
      {
        left = new ChallengePair(
          ((EnglishAST)level2.first.question).children.get(0), //ArithmeticFragment
          level2.first.answer, //string answer
          null,
          level2.second.answer //number answer
        );
      }
      //add function call specifications here
      if(((EnglishAST)level2.first.question).children.size() > 1)
      {
        for(int i = 1; i < ((EnglishAST)level2.first.question).children.size(); i++)
        {
          funcs.add(((EnglishAST)level2.first.question).children.get(i));
        }
      }
      right = getVal(true);
    }
    else
    {
      left = getVal(true);
      if(level2.second.question.children.size() > 0)
      {
        right = new ChallengePair(
          ((EnglishAST)level2.first.question).children.get(0), //ArithmeticFragment
          level2.first.answer, //string answer
          ((EnglishAST)level2.second.question).children.get(0), //EvalWithFragment
          level2.second.answer //number answer
        );
      
        l2with.addAll(level2.second.question.children.get(0).children);
      }
      else
      {
        right = new ChallengePair(
          ((EnglishAST)level2.first.question).children.get(0), //ArithmeticFragment
          level2.first.answer, //string answer
          null,
          level2.second.answer //number answer
        );
      }
      //add function call specifications here
      if(((EnglishAST)level2.first.question).children.size() > 1)
      {
        for(int i = 1; i < ((EnglishAST)level2.first.question).children.size(); i++)
        {
          funcs.add(((EnglishAST)level2.first.question).children.get(i));
        }
      }
    }

    lf = Integer.parseInt(left.second.answer);
    rt = Integer.parseInt(right.second.answer);
    
    switch (operation)
    {
      case ADD:
        sol = lf + rt;
        break;
      case SUBTRACT:
        sol = lf - rt;
        break;
      case MULTIPLY:
        sol = lf * rt;
        break;
      case DIVIDE:
        if(rt == 0)
        {
          rt++;//so no 1/0
          if(right.first.answer.equals("0"))
            right.first.answer = "1";
        }
        sol = lf / rt;
        break;
      case MODULO:
        if(rt == 0)
        {
          rt++;//so no 1/0
          if(right.first.answer.equals("0"))
            right.first.answer = "1";
        }
        sol = lf % rt;
        break;
      default:
        System.exit(1);
        break;
    }
    if(lorr == 0)
      a0 = "(" + left.first.answer + ") " + operation + " " + right.first.answer;
    else
      a0 = left.first.answer + " " + operation + " (" + right.first.answer + ")";
    a1 = String.valueOf(sol);
    
//     if(lorr == 0)
//     {
//       math.add(((EnglishAST)level2.first.question).children.get(0));
//       math.add(right.first.question);
//     }
//     else
//     {
//       math.add(left.first.question);
//       math.add(((EnglishAST)level2.first.question).children.get(0));
//     }
    ArrayList<Fragment> m = new ArrayList<Fragment>();
    m.add(left.first.question);
    m.add(right.first.question);
    
    ArithmeticFragment math = new ArithmeticFragment(operation, m, false);
    q0.add(math);
    
    EvalWithFragment with = new EvalWithFragment();
    
    if(left.first.question instanceof FunctionCallExpFragment)
    {
      q0.add(left.second.question);
      with.add(new StringFragment(left.first.answer + " = " + left.second.answer));
    }
    else if(!left.first.answer.equals(left.second.answer))
    {
      with.add(new StringFragment(left.first.answer + " = " + left.second.answer));
    }
    if(right.first.question instanceof FunctionCallExpFragment)
    {
      q0.add(right.second.question);
      with.add(new StringFragment(right.first.answer + " = " + right.second.answer));
    }
    else if(!right.first.answer.equals(right.second.answer))
    {
      with.add(new StringFragment(right.first.answer + " = " + right.second.answer));
    }
    
    if(funcs.size() > 0)
    {
      for(int i = 0; i < funcs.size(); i++)
      {
        q0.add(funcs.get(i));
      }
    }
    
    if(l2with.size() > 0)
    {
      for(int i = 0; i < l2with.size(); i++)
      {
        with.add(l2with.get(i));
      }
    }
    
    if(with.children.size() > 0)
    {
      q1.add(with);
    }
    
    
    return new ChallengePair(q0,a0,q1,a1);

  }

  /**
   * Generate a random value
   * @param num true will only return values that evaluate to numbers, false returns booleans
   * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
   */
  public static ChallengePair getVal(boolean num)
  {
    Random r = new Random();
    int which = -1;
    
    if(num)
      which = r.nextInt(3);
    else
      which = r.nextInt(2)+3;
    
    int i;
    ChallengePair answers = null;
    Fragment q0;
    String a0;
    Fragment q1;
    String a1;
    
    RandomStrings rs = new RandomStrings(); 
    RandomCode rc = new RandomCode();
    
    switch(which)
    {
      case 0:
        i = r.nextInt(maxint);
        q0 = new StringFragment(String.valueOf(i));
        a0 = String.valueOf(i);
        q1 = null;
        a1 = String.valueOf(i);;
        answers = new ChallengePair(q0,a0,q1,a1);
        break;
      case 1:
        i = r.nextInt(maxint); //val for variable
        String var = rs.randomName(1);
        q0 = new StringFragment(var); //name for variable
        a0 = var;
        a1 = String.valueOf(i);
        q1 = new StringFragment(a1);
        answers = new ChallengePair(q0,a0,q1,a1);
        break;
      case 2:
        i = r.nextInt(maxint);
        ChallengePair temp = rc.generateFunctionCall();
        
        answers = new ChallengePair(
          ((EnglishAST)temp.first.question).children.get(0),
          temp.first.answer,
          ((EnglishAST)temp.first.question).children.get(1),
          String.valueOf(i)
        );
        break;
      case 3:
        i = r.nextInt(1);
        if(i == 0)
        {
          a0 = rs.randomName(1);
          a1 = "false";
        }
        else
        {
          a0 = rs.randomName(1);;
          a1 = "true";
        }
        q0 = new StringFragment(a0);
        answers = new ChallengePair(q0,a0,null,a1);
        break;
      case 4:
        i = r.nextInt(1);
        temp = rc.generateFunctionCall();
        String ans;

        if(i == 0)
        {
          ans = "false";
        }
        else
        {
          ans = "true";
        }
        
        answers = new ChallengePair(
          ((EnglishAST)temp.first.question).children.get(0),
          temp.first.answer,
          ((EnglishAST)temp.first.question).children.get(1),
          ans
        );
        break;
      default:
        break;
    }
    return answers;
  }
}

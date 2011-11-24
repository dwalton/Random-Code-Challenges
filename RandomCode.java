import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

/** RandomCode
 * This class generates different sections of random code
 * @author Doug Walton
 * @version 0.1
 */
public class RandomCode
{
  private static Random random = new Random();
  private RandomArithmetic ra;
  private RandomStrings rs;
  
  /**
   * Eventually there should be a way to specify these,
   * maybe a config file...
   */
  private static final int FUNCTION_NAME_LENGTH = 7; 
  private static final int FUNCTION_MAX_ARGS = 5;
  private static final int VAR_NAME_LENGTH = 4;
  private static final int MAX_BOOLEAN_EXPRESSIONS = 3;
  private static final int FUNCTION_MAX_PARAMS = 4;

  /**
   * Generic constructor... may add one for specifying options above
   */
  public RandomCode()
  {
    ra = new RandomArithmetic();
    rs = new RandomStrings();
  }
  
  /**
   * Generate a random return statement
   * @return ChallengePair the object that contains two questions and two answers.  Sometimes the second question/answer can be null if irrelavent.
   */
  public ChallengePair generateReturn()
  {
    EnglishAST q0 = new EnglishAST("Give the statement that ");
    String a0;
    EnglishAST q1 = null;
    String a1 = "";
    
    ReturnFragment rf = new ReturnFragment();
    ArrayList<Fragment> funcargs = new ArrayList<Fragment>();
    ChallengePair exp = getExp();
    
    a0 = "return " + exp.first.answer;
    
//     int numfuncs = (((EnglishAST)exp.first.question).children).size()-1;
    
    if(exp.first.question instanceof EnglishAST)
    {
      funcargs.addAll(((EnglishAST)exp.first.question).children);
      if(!funcargs.isEmpty())
        rf.add(funcargs.remove(0));
    }
    else
      rf.add(exp.first.question);

    
    
    q0.add(rf);
    q0.children.addAll(funcargs);

    if(exp.first.question instanceof FunctionCallExpFragment)
    {
      //add args to q0 as new sentence
      q0.add(exp.second.question);
    }
    
    ChallengePair answers = new ChallengePair(q0,a0,q1,a1);
    return answers;
  }

  /**
   * Returns a random expression as a ChallengePair
   * Expressions can be: values, complex boolean/math, simple boolean/math,
   *   and function calls
   */
  public ChallengePair getExp()
  {
    /*
    * An expression can be:
    * value(inc function calls)
    * complex boolean
    * complex math
    * simple math
    * simple boolean
    */
    ChallengePair answers = new ChallengePair();
    
    int choice = random.nextInt(5);
    switch(choice)
    {
      case 0: //value
        int norb = random.nextInt(2);
        if(norb == 0)
          answers = ra.getVal(true);
        else
          answers = ra.getVal(false);
        break;
      case 1: //complex boolean
        answers = generateComplexBoolean();
        break;
      case 2: //complex math
        ChallengePair temp = ra.generateComplex();
        answers = temp;   
        break;
      case 3: //simple boolean
        if(random.nextInt(2) == 1)
          answers = generateBooleanExpr(true);
        else
          answers = generateBooleanExpr(false);
        break;
      case 4: //simple math
        temp = ra.generateMath(true);
        answers = temp;
        break;
      default:
        break;
    }
    
    return answers;
  }
  
  /**
   * Returns a random statement as a ChallengePair
   * Statements can be: returns, assignments, function calls,
   *   (and later: if, while, switch, for, blocks...)
   */
  public ChallengePair getStatement()
  {
    ChallengePair ret = null;
    int i = 0;
    int choice = random.nextInt(3);
    switch(choice)
    {
      case 0: //return
        ret = generateReturn();
        break;
      case 1: //assignment
        ret = generateAssignment();
        break;
      case 2: //function call
        ret = generateFunctionCall(false);
        break;
      //more later...
      default:
        System.exit(1);
        break;
    }
    
    return ret;
  }

  /**
   * Generate a random function call
   * @return ChallengePair the object that contains two questions and two answers.  Sometimes the second question/answer can be null if irrelavent.
   */
  public ChallengePair generateFunctionCall(boolean isExp)
  {
    String fname = rs.randomName(FUNCTION_NAME_LENGTH);
    int numargs = random.nextInt(FUNCTION_MAX_ARGS);
    
    EnglishAST q0 = new EnglishAST("");
    String a0;
    EnglishAST q1 = null;//new EnglishAST("");
    String a1 = "";
    
    ArrayList<Fragment> kids = new ArrayList<Fragment>();
    
    FunctionCallArgsFragment fargs = new FunctionCallArgsFragment(fname);
    
    
    String[] types = new String[numargs];
    for(int i = 0; i < numargs; i++)
    {
      types[i] = Types.randomVal();
      fargs.add(new StringFragment(types[i]));
    }
    
    FunctionCallExpFragment func = new FunctionCallExpFragment(fname, kids, isExp);
    q0.add(func);
    q0.add(fargs);
    
    String solution = fname + "(";
    for(int i = 0; i < numargs; i++)
    {
      solution = solution + types[i];
      if(i != numargs-1)
        solution = solution + ", ";
    }
    solution = solution + ")";
    
    a0 = solution;

    return new ChallengePair(q0,a0,q1,a1);
  }

  /**
   * REMOVED - Helper function for generateFunctionCall, replaces function name, types,
   * and parameters.
   */
  
  /**
   * Generate a random declaration (should be combined with generateAssignment()), like "int a;"
   * @return ChallengePair the object that contains two questions and two answers.  Sometimes the second question/answer can be null if irrelavent.
   */
//   public String[][] generateDeclaration()
//   {
//     String q0 = "Provide an expression to declare the variable <var> as <a> <type>.";
//     String var = rs.randomName(VAR_NAME_LENGTH);
//     Types t = Types.randomType();
//     
//     q0 = q0.replaceFirst("<type>", t.typeName().toLowerCase());
//     if(t.equals(Types.INT))
//     {
//       q0 = q0.replaceFirst("<a>", "an");
//     }
//     else
//     {
//       q0 = q0.replaceFirst("<a>", "a");
//     }
//     
//     q0 = q0.replaceFirst("<var>", var);
//     
//     String a0 = t.typeName() + " " + var + ";";
//     
//     String[][] answers = new String[2][2];
//     
//     answers[0][0] = q0;
//     answers[1][0] = a0;
//     
//     return answers;
//   }
  
  /**
   * Generate a random assignment (something = something else), should include some RandomArithmetic, generateFunctionCall(), and generateBooleanExpr()
   * @return ChallengePair the object that contains two questions and two answers.  Sometimes the second question/answer can be null if irrelavent.
   */
  public ChallengePair generateAssignment()
  {
    EnglishAST q0 = new EnglishAST("Provide an statement that ");
    EnglishAST q1 = null;
    StringFragment var = new StringFragment(rs.randomName(VAR_NAME_LENGTH));
    ArrayList<Fragment> kids = new ArrayList<Fragment>();
    ChallengePair value = getExp();
    Fragment val = value.first.question;
    String valstr = value.first.answer;
    ArrayList<Fragment> funcargs = new ArrayList<Fragment>();
    
    kids.add(var);
    if(val instanceof EnglishAST)
    {
      funcargs.addAll(((EnglishAST)val).children);
      if(!funcargs.isEmpty())
        kids.add(funcargs.remove(0));
      else
        kids.add(val);
    }
    else
      kids.add(val);
    
//     System.out.println("!!! "+kids.size());
    AssignmentFragment af = new AssignmentFragment(kids);
    
    q0.add(af);
    q0.children.addAll(funcargs);
    
    String a0 = var.toString() + " = " + valstr + ";";
    String a1 = "";
    
    
    return new ChallengePair(q0,a0,q1,a1);
  }
//   
  /**
   * Generate a random JUnit test (includes generateFunctionCall())
   * @return ChallengePair the object that contains two questions and two answers.  Sometimes the second question/answer can be null if irrelavent.
   */
  public ChallengePair generateJUnit()
  {
    EnglishAST q0 = new EnglishAST("Provide a ");
    String a0 = "assertEquals(@<type>, @<func>);";
    String a1 = "";
    
    ChallengePair func = generateFunctionCall(true);
    Fragment fval = func.first.question;
    
    String exp = func.first.answer;
    String act = Types.randomVal();
    
    ArrayList<Fragment> kids = new ArrayList<Fragment>();
    ArrayList<Fragment> funcargs = new ArrayList<Fragment>();
    
    //if the beginning is an EAST, get the args if there are function calls
    if(fval instanceof EnglishAST)
    {
      funcargs.addAll(((EnglishAST)fval).children);
      if(!funcargs.isEmpty())
        kids.add(funcargs.remove(0));
      else
        kids.add(fval);
    }
    else
      kids.add(fval);
    
    kids.add(new StringFragment(act));
    
    JUnitFragment jf = new JUnitFragment(kids);
    
    q0.add(jf);
    q0.children.addAll(funcargs);
    
    a0 = a0.replaceFirst("@<func>", exp);
    a0 = a0.replaceFirst("@<type>", act);
    
    return new ChallengePair(q0,a0,null,null);
  }
  
  /**
   * Generate a random function header (public/private [static] return_type name (parameters))
   * @return ChallengePair the object that contains two questions and two answers.  Sometimes the second question/answer can be null if irrelavent.
   */
//   public String[][] generateFunctionHeader()
//   {
//     String q0 = "Provide a header for a <static><public> function called <name> that returns <a><type> and has <num> parameters: <params>.";
//     String a0 = "<public> <static><type> <name>(<params>)";
//     String aParams = "";
//     String qParams = "";
//     String pub = "";
//     String name = rs.randomName(5);
//     String stc = "";
//     Types ret = Types.randomReturnType();
//     
//     //private or public?
//     int i = random.nextInt(2);
//     if(i == 0)
//     {
//       pub = "private";
//     }
//     else if(i == 1)
//     {
//       pub = "public";
//     }
//     q0 = q0.replaceFirst("<public>", pub);
//     a0 = a0.replaceFirst("<public>", pub);
//     
//     //static or no static?
//     i = random.nextInt(2);
//     if(i == 0)
//     {
//       stc = "";
//     }
//     else if(i == 1)
//     {
//       stc = "static ";
//     }
//     q0 = q0.replaceFirst("<static>", stc);
//     a0 = a0.replaceFirst("<static>", stc);
//     
//     //name
//     q0 = q0.replaceFirst("<name>", name);
//     a0 = a0.replaceFirst("<name>", name);
//     
//     //return type
//     switch(ret)
//     {
//       case VOID:
// 	q0 = q0.replaceFirst("<a><type>", "nothing");
// 	a0 = a0.replaceFirst("<type>", "void");
// 	break;
//       case INT:
// 	q0 = q0.replaceFirst("<a><type>", "an int");
// 	a0 = a0.replaceFirst("<type>", "int");
// 	break;
//       case DOUBLE:
//       case STRING:
//       case CHAR:
//       case BOOLEAN:
// 	q0 = q0.replaceFirst("<a><type>", "a " + ret.typeName());
// 	a0 = a0.replaceFirst("<type>", ret.typeName());
// 	break;
//       default:
// 	break;
//     }
//     
//     //params (0-4)
//     i = random.nextInt(5);
//     q0 = q0.replaceFirst("<num>", ""+i);
//     
//     if(i == 0)
//     {
//       q0 = q0.replaceFirst(": <params>", "");
//     }
//     else if(i == 1)
//     {
//       q0 = q0.replaceFirst("s: <params>", ": <params>");
//     }
//     
//     for(int k = 0; k < i; k++)
//     {
//       Types type = Types.randomType();
//       String pname = rs.randomName(4);
//       String a = "a";
//       if(type.equals(Types.INT))
// 	a = "an";
//       
//       if(k == i-1 && i != 1)
//       {
// 	qParams = qParams + "and " + a + " " + type.typeName() + " " + pname;
// 	aParams = aParams + type.typeName() + " " + pname;
//       }
//       else if(k == i-1)
//       {
//         qParams = qParams + a + " " + type.typeName() + " " + pname;
//         aParams = aParams + type.typeName() + " " + pname;
//       }
//       else
//       {
//         qParams = qParams + a + " " + type.typeName() + " " + pname + ", ";
// 	aParams = aParams + type.typeName() + " " + pname + ", ";
//       }
//     }
//     
//     q0 = q0.replaceFirst("<params>", qParams);
//     a0 = a0.replaceFirst("<params>", aParams);
//     
//     String[][] answers = new String[2][2];
//     
//     answers[0][0] = q0;
//     answers[1][0] = a0;
//     
//     return answers;
//   }
  
  /**
   * Generate a random boolean expression
   * @param comparison true if we want to deal with numeric comparisons
   * @return ChallengePair the object that contains two questions and two answers.  Sometimes the second question/answer can be null if irrelavent.
   */
  public ChallengePair generateBooleanExpr(boolean comparison)
  {
    EnglishAST q0 = new EnglishAST("Provide the expression that computes whether");
    EnglishAST q1 = new EnglishAST("Now evaluate the expression");
    String a0 = "<val> <op> <val>";
    String a1 = "";
    
    ArrayList<Fragment> kids = new ArrayList<Fragment>();
    ArrayList<Fragment> funcargs = new ArrayList<Fragment>();
    
    BoolOps op = BoolOps.generateBoolOp(comparison);
    a0 = a0.replaceFirst("<op>", op.toString());
    
    ChallengePair left = RandomArithmetic.getVal(comparison);
    ChallengePair right = RandomArithmetic.getVal(comparison);
    
    
    kids.add(left.first.question);
    kids.add(right.first.question);
    
    if(op.equals(BoolOps.NOT) 
      || op.equals(BoolOps.ITSELF))
    {
      a0 = a0.replaceFirst("<val> ", "");
      a0 = a0.replaceAll(" ", "");
      kids.remove(1);
    }
    
    BooleanFragment bool = new BooleanFragment(op, kids);
    q0.add(bool);
    
    EvalWithFragment with = new EvalWithFragment();
    
    //if left is a function call
    if(kids.contains(left.first.question) && left.first.question instanceof FunctionCallExpFragment)
    {
      //add args to q0 as new sentence
      q0.add(left.second.question);
      //add value to with for q1
      with.add(new StringFragment(left.first.answer + " = " + left.second.answer));
    }
    //if left is a variable
    else if(kids.contains(left.first.question) && !left.first.answer.equals(left.second.answer))
    {
      //add value of variable for q1
      with.add(new StringFragment(left.first.answer + " = " + left.second.answer));
    }
    
    //if right is a function call
    if(kids.contains(right.first.question) && right.first.question instanceof FunctionCallExpFragment)
    {
      //add args to q1 as new sentence
      q0.add(right.second.question);
      //add value of function call to with for q1
      with.add(new StringFragment(right.first.answer + " = " + right.second.answer));
    }
    //if right is a variable
    else if(kids.contains(right.first.question) && !right.first.answer.equals(right.second.answer))
    {
      //add value of right for q1
      with.add(new StringFragment(right.first.answer + " = " + right.second.answer));
    }
    
    //if there are some variables/functions
    if(!with.children.isEmpty())
    {
      q1.add(with);
    }
    
    //start evaluating the answer
    
    
    boolean ans = false;
    if(!comparison)
    {
      boolean lv = Boolean.parseBoolean(left.second.answer);
      boolean rv = Boolean.parseBoolean(right.second.answer);
      switch(op)
      {
        case AND:
          ans = lv && rv;
          break;
        case OR:
          ans = lv || rv;
          break;
        case EQUALS:
          ans = lv == rv;
          break;
        case NOT_EQUAL:
          ans = lv != rv;
          break;
        case NOT:
          ans = !lv;
          break;
        case ITSELF:
          ans = lv;
          break;
        default:
          System.exit(1);
          break;
      }
    }
    else
    {
      int lv = Integer.parseInt(left.second.answer);
      int rv = Integer.parseInt(right.second.answer);
      switch(op)
      {
        case GREATER_THAN:
          ans = lv > rv;
          break;
        case GREATER_THAN_OR_EQUAL:
          ans = lv >= rv;
          break;
        case EQUALS:
          ans = lv == rv;
          break;
        case NOT_EQUAL:
          ans = lv != rv;
          break;
        case LESS_THAN:
          ans = lv < rv;
          break;
        case LESS_THAN_OR_EQUAL:
          ans = lv <= rv;
          break;
        default:
          System.exit(1);
          break;
      }
    }
    
    a0 = a0.replaceFirst("<val>", left.first.answer);
    if(!op.equals(BoolOps.NOT) 
      && !op.equals(BoolOps.ITSELF))
    {
      a0 = a0.replaceFirst("<val>", right.first.answer);
    }
    
    return new ChallengePair(q0,a0,q1,String.valueOf(ans));
  }
  
  /**
   * Generate a random complex boolean expression (for example:
   *   AND/ORing a bunch of simple boolean expressions together)
   * @return ChallengePair the object that contains two questions and two answers.  Sometimes the second question/answer can be null if irrelavent.
   */
  public ChallengePair generateComplexBoolean()
  {
    EnglishAST q0 = new EnglishAST("Provide the expression that computes ");
    EnglishAST q1 = new EnglishAST("Now evaluate it");
    String a0 = "";
    String a1 = "";
    
    boolean eval;
    
    Random r = new Random();
    int boolcount = 3 + r.nextInt(MAX_BOOLEAN_EXPRESSIONS);
    int andor = r.nextInt(2);
    
    BoolOps operator;
    if(andor == 0)
    {
      operator = BoolOps.AND;
      eval = true;
    }
    else
    {
      operator = BoolOps.OR;
      eval = false;
    }
    
    ArrayList<Fragment> kids = new ArrayList<Fragment>();
    ArrayList<Fragment> funcargs = new ArrayList<Fragment>();
    ArrayList<Fragment> with = new ArrayList<Fragment>();
    
    ChallengePair temp;
    Fragment tempq;
    Fragment tempq2;
    ArrayList<Fragment> tempfargs = new ArrayList<Fragment>();
    ArrayList<Fragment> tempwith = new ArrayList<Fragment>();
    
    int numorbool;
    for(int i = 0; i < boolcount; i++)
    {
      tempfargs = new ArrayList<Fragment>();
      
      numorbool = r.nextInt(2);
      if(numorbool == 1)
      {
        temp = generateBooleanExpr(true);
      }
      else
      {
        temp = generateBooleanExpr(false);
      }
      
      tempq = temp.first.question;
      tempq2 = temp.second.question;

      //check to see if there are any functions, and add first child to
      //  the list of kids
      tempfargs.addAll(((EnglishAST)tempq).children);
      if(!tempfargs.isEmpty())
        kids.add(tempfargs.remove(0));
      else
        kids.add(tempq);
      
      //add the function arguments
      funcargs.addAll(tempfargs);

      //add the variable values for q1
      //they are stored in temp.second.question.children.get(0).children
      //  ie, the with fragment's children if it exists
      if(!((EnglishAST)tempq2).children.isEmpty())
      {
        with.addAll(((EvalWithFragment)((EnglishAST)tempq2).children.get(0)).children);
      }
      
      //add values for a0 as we go
      if(i != boolcount-1)
        a0 = a0 + "(" + temp.first.answer + ") " + operator.toString() + " ";
      else
        a0 = a0 + "(" + temp.first.answer + ")";
      
      //compute evaluation answer as we go (for a1)
      if(operator.equals(BoolOps.AND))
        eval &= Boolean.parseBoolean(temp.second.answer);
      else if(operator.equals(BoolOps.OR))
        eval |= Boolean.parseBoolean(temp.second.answer);
    }
    
    BooleanFragment bool = new BooleanFragment(operator, kids);
    q0.add(bool);
    q0.children.addAll(funcargs);
    
    if(!with.isEmpty())
    {
      EvalWithFragment w = new EvalWithFragment(with);
      q1.add(w);
    }
    
    a1 = String.valueOf(eval);
    
    return new ChallengePair(q0, a0, q1, a1);
  }
  
  /**
   * Generate a random if statement (possibly make else a random appearance)
   * @return ChallengePair the object that contains two questions and two answers.  Sometimes the second question/answer can be null if irrelavent.
   */
  public ChallengePair generateIfStatement()
  {
    EnglishAST q0 = new EnglishAST("Build a statement such that, ");
    String a0 = "if(<boolxp>)\n{\n<exp>;\n}\nelse\n{\n<exp>\n}";
    
    int i = random.nextInt(3);
    ChallengePair boolxp;
    //decide the what the guard will be
    if(i == 2)
    {
      boolxp = generateComplexBoolean();
    }
    else if(i == 1)
    {
      boolxp = generateBooleanExpr(true);
    }
    else
    {
      boolxp = generateBooleanExpr(false);
    }
    
    ArrayList<Fragment> kids = new ArrayList<Fragment>();
    ArrayList<Fragment> funcargs = new ArrayList<Fragment>();
    ArrayList<Fragment> tempfargs = new ArrayList<Fragment>();
    ArrayList<Fragment> with = new ArrayList<Fragment>();
    
    Fragment temp = boolxp.first.question;
    
    //get the function arguments and guard for first child
    tempfargs.addAll(((EnglishAST)temp).children);
    if(!tempfargs.isEmpty())
      kids.add(tempfargs.remove(0));
    else
      kids.add(temp);
    funcargs.addAll(tempfargs);
    tempfargs = new ArrayList<Fragment>();
    
    a0 = "if(" + boolxp.first.answer + ")\n{\n  ";
    
    //then block
    ChallengePair then = getStatement();
    temp = then.first.question;
    //get the function arguments and statement for second child
    tempfargs.addAll(((EnglishAST)temp).children);
    if(!tempfargs.isEmpty())
      kids.add(tempfargs.remove(0));
    else
      kids.add(temp);
    funcargs.addAll(tempfargs);
    tempfargs = new ArrayList<Fragment>();
    
    a0 = a0 + then.first.answer + "\n}\n";
    
    //decide if there's an else (50%)
    int iselse = random.nextInt(2);
    //if 1, we'll do an else
    if(iselse == 1)
    {
      //else block
      ChallengePair els = getStatement();
      temp = els.first.question;
      //get the function arguments and statement for second child
      tempfargs.addAll(((EnglishAST)temp).children);
      if(!tempfargs.isEmpty())
        kids.add(tempfargs.remove(0));
      else
        kids.add(temp);
      funcargs.addAll(tempfargs);
      tempfargs = new ArrayList<Fragment>();
      
      a0 = a0 + "else\n{\n  " + els.first.answer + "\n}\n";
    }
    
    IfFragment iff = new IfFragment(kids);
    q0.add(iff);
    q0.children.addAll(funcargs);
    
    return new ChallengePair(q0, a0, null, null);
  }
//   
//   /**
//    * Generate a random while statement
//    * @return ChallengePair the object that contains two questions and two answers.  Sometimes the second question/answer can be null if irrelavent.
//    */
//   public String[][] generateWhileStatement()
//   {
//     String q0 = "Build a statement such that, while <boolxp> is true then keep <exp>.";
//     String q1 = "";
//     String a0 = "while(<boolxp>)\n{\n<exp>\n}";
//     String a1 = "";
//     Random r = new Random();
//     int i = r.nextInt(1);
//     String[][] boolxp = new String[2][2];
//     if(i == 1)
//     {
//       boolxp = generateBooleanExpr(true);
//     }
//     else
//     {
//       boolxp = generateBooleanExpr(false);
//     }
//     
//     boolxp[0][0] = boolxp[0][0].replaceFirst("Provide the boolean expression for ","");
//     q0 = q0.replaceFirst("<boolxp>", boolxp[0][0]);
//     a0 = a0.replaceFirst("<boolxp>", boolxp[1][0]);
//     
//     q0 = moveArgsToEnd(q0);
//     //exp can be:
//       //function call
//       //assignment w/ getVal
//     
//     String[][] answers = new String[2][2];
//     
//     answers[0][0] = q0;
//     answers[0][1] = q1;
//     answers[1][0] = a0;
//     answers[1][1] = a1;
//     
//     return answers;
//   }
//   

  /**
   * Generate a function with only a return statement
   */
  public ChallengePair generateSimpleFunction()
  {
    EnglishAST q0 = new EnglishAST("Write the ");
    String a0 = "public ";
    
    boolean pub = true;
    if(random.nextInt(2) == 1)
    {
      pub = false;
      a0 = "private ";
    }
    
    String name = rs.randomName(FUNCTION_NAME_LENGTH);
    
    ChallengePair ret = generateReturn();
    Fragment temp = ret.first.question;
    
    Types type = Types.INT;
    if(((ReturnFragment)((EnglishAST)temp).children.get(0)).children.get(0) instanceof BooleanFragment)
      type = Types.BOOLEAN;
    
    a0 = a0 + type.typeName() + " " + name + "(";
    
    ArrayList<Fragment> kids = new ArrayList<Fragment>();
    ArrayList<Fragment> funcargs = new ArrayList<Fragment>();
    ArrayList<Fragment> params = new ArrayList<Fragment>();
    
    int numparams = random.nextInt(FUNCTION_MAX_PARAMS);
    for(int i = 0; i < numparams; i++)
    {
      String t = Types.randomType().typeName();
      String n = rs.randomName(VAR_NAME_LENGTH);
      params.add(new StringFragment(t + " " + n));
      
      a0 = a0 + t + " " + n;
      if(i < numparams-1)
        a0 = a0 + ", ";
    }
    
    a0 = a0 + ")\n{\n  ";
    
    ParameterFragment p = new ParameterFragment(params);
    kids.add(p);
    
    //get the function arguments for the return
    funcargs.addAll(((EnglishAST)temp).children);
    if(!funcargs.isEmpty())
      kids.add(funcargs.remove(0));
    else
      kids.add(temp);
    
    FunctionFragment ff = new FunctionFragment(pub, type, name, kids);
    q0.add(ff);
    q0.children.addAll(funcargs);
    
    a0 = a0 + ret.first.answer + ";\n}\n";
    
    return new ChallengePair(q0, a0, null, null);
  }
}

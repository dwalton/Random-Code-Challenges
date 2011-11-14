import java.util.Random;
import java.util.Arrays;

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

  /**
   * Generic constructor... may add one for specifying options above
   */
  public RandomCode()
  {
    ra = new RandomArithmetic();
    rs = new RandomStrings();
  }
  
  /**
   * Generate a random return statement given the type to return.
   * @param type the type of statement to return (String, int, boolean...)
   * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
   */
//   public String[][] generateReturn(Types type)
//   {
//     String q0 = "Give the statement that returns <exp>";
//     String a0 = "return <exp>";
//     
//     String[][] exp = new String[2][2];
//     switch(type)
//     {
//       case INT:
//         exp = RandomArithmetic.getVal(true);
//         break;
//       case BOOLEAN:
//         exp = RandomArithmetic.getVal(false);
//         break;
//       case DOUBLE:
//       case CHAR:
//       case STRING:
//         exp[0][0] = type.randomSVal();
//         exp[0][1] = exp[0][0];
//         exp[1][0] = exp[0][0];
//         exp[1][1] = exp[0][0]; 
//         break;
//       default:
//         break;
//     }
//     
//     q0 = q0.replaceFirst("<exp>", exp[0][0]);
//     a0 = a0.replaceFirst("<exp>", exp[1][0]);
//     
//     String[][] answers = new String[2][2];
//     
//     answers[0][0] = q0;
//     answers[1][0] = a0;
//     
//     return answers;
//   }

  /**
   * Generate a random function call
   * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
   */
  public ChallengePair generateFunctionCall()
  {
    String fname = rs.randomName(FUNCTION_NAME_LENGTH);
    int numargs = random.nextInt(FUNCTION_MAX_ARGS);
    
    EnglishAST q0 = new EnglishAST("Provide");
    String a0;
    EnglishAST q1 = null;//new EnglishAST("");
    String a1 = null;
    
    FunctionCallExpFragment func = new FunctionCallExpFragment(fname);
    
    FunctionCallArgsFragment fargs = new FunctionCallArgsFragment(fname);
    
    q0.add(func);
    
    String[] types = new String[numargs];
    for(int i = 0; i < numargs; i++)
    {
      types[i] = Types.randomVal();
      fargs.add(new StringFragment(types[i]));
    }
    
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
   * Helper function for generateFunctionCall, replaces function name, types,
   * and parameters.
   */
//   public String replaceTags(String sentence, String[] tags, String[] want)
//   {
//     String s = sentence;
//     String st = "";
//     String temp = "";
//     String[] types = null;
//     for(int i = 0; i < tags.length; i++)
//     {
//       if(tags[i].equals("<fname>"))
//         s = s.replaceAll("<fname>", want[i]);
//       else if(tags[i].equals("<argnum>"))
//         s = s.replaceFirst("<argnum>", want[i]);
//       else if(tags[i].equals("<types>"))
//       {
//         temp = want[i].replace("[", "");
//         temp = temp.replace("]", "");
// 
//         types = temp.split(", ");
// 
//         for(int j = 0; j < types.length; j++)
//         {
//           if(j == types.length-1 && j >= 1)
//             st = st + "and ";
// //           if(types[j].charAt(0) == 'i')
// //             st = st + "an " + types[j];
// //           else
//             st = st /*+ "a " */+ types[j];
// 
//           if(j < types.length-1)
//             st = st + ", ";
//         }
//         s = s.substring(0, s.indexOf("<types>"))
//           + st
//           + s.substring(s.indexOf("<types>") + "<types>".length());
//       }
//     }
//     return s;
//   }
  
  /**
   * Generate a random declaration (should be combined with generateAssignment()), like "int a;"
   * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
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
   * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
   */
//   public String[][] generateAssignment()
//   {
//     String q0 = "Provide an expression to assign <type> to the variable <var>.";
//     String var = rs.randomName(4);
//     String val = Types.randomVal();
//     
//     q0 = q0.replaceFirst("<type>", val);
//     
//     q0 = q0.replaceFirst("<var>", var);
//     
//     String a0 = var + " = " + val + ";";
//     String a1 = var + "=" + val + ";";
//     
//     String[][] answers = new String[2][2];
//     
//     answers[0][0] = q0;
//     answers[1][0] = a0;
//     answers[1][1] = a1;
//     
//     return answers;
//   }
//   
  /**
   * Generate a random JUnit test (includes generateFunctionCall())
   * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
   */
//   public String[][] generateJUnit()
//   {
//     String q0 = "Provide a junit test for the function <func>.  We expect the function will return the value <val>.";
//     String q1 = "Evaluate the junit test assuming the function returns the <cw> value.";
//     int eq = random.nextInt(2);
//     Types t = Types.randomType();
//     String a0 = "assertEquals(@<func>, @<type>);";
//     String a1 = "";
//     
//     String[][] func = generateFunctionCall();
//     String exp = func[1][0];
//     String act = t.randomSVal();
//     
//     q0 = q0.replaceFirst("<type>", t.typeName().toLowerCase());
//     func[0][0] = func[0][0].replaceFirst("Provide a function call to ", "");
//     func[0][0] = func[0][0].replaceFirst(" in that order.", "");
//     q0 = q0.replaceFirst("<func>", func[0][0]);
//     //not equal
//     if(eq == 0)
//     {
//       q1 = q1.replaceFirst("<cw>","wrong");
//       a1 = "false";
//     }
//     //equal
//     else if(eq == 1)
//     {
//       q1 = q1.replaceFirst("<cw>","correct");
//       a1 = "true";
//     }
//     q0 = q0.replaceFirst("<val>", act);
//     a0 = a0.replaceFirst("@<func>", exp);
//     a0 = a0.replaceFirst("@<type>", act);
//     a0 = a0.replaceFirst(";", "");
//     
//     q0 = moveArgsToEnd(q0);
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
  
  /**
   * Generate a random function header (public/private [static] return_type name (parameters))
   * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
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
  
//   /**
//    * Generate a random boolean expression
//    * @param comparison true if we want to deal with numeric comparisons
//    * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
//    */
//   public String[][] generateBooleanExpr(boolean comparison)
//   {
//     String q0 = "Provide the boolean expression for <val> <op> <val>";
//     String q1 = "Now evaluate the expression";
//     String a0 = "<val> <op> <val>";
//     String a1 = "";
//     
//     BoolOps op = BoolOps.generateBoolOp(comparison);
//     
//     q0 = q0.replaceFirst("<op>", op.opName());
//     
//     a0 = a0.replaceFirst("<op>", op.toString());
//     
//     String[][] left = RandomArithmetic.getVal(comparison);
//     String[][] right = RandomArithmetic.getVal(comparison);
//     
//     if(op.equals(BoolOps.NOT) 
//       || op.equals(BoolOps.ITSELF))
//     {
//       q0 = q0.replaceFirst("<val> ", "");
//       a0 = a0.replaceFirst("<val> ", "");
//       a0 = a0.replaceAll(" ", "");
//       if(!left[1][0].equals(left[1][1]))
//       {
//         q1 = q1 + " with " + left[1][0] + " = " + left[1][1] + ".";
//       }
//     }
//     else
//     {
//       if(!left[1][0].equals(left[1][1]))
//       {
//         q1 = q1 + " with " + left[1][0] + " = " + left[1][1];
//         if(!right[1][0].equals(right[1][1]))
//         {
//           q1 = q1 + " and " + right[1][0] + " = " + right[1][1];
//         }
//       }
//       else if(!right[1][0].equals(right[1][1]))
//       {
//         q1 = q1 + " with " + right[1][0] + " = " + right[1][1];
//       }
//       q1 = q1 + ".";
//     }
//     
//     
//     boolean ans = false;
//     if(!comparison)
//     {
//       boolean lv = Boolean.parseBoolean(left[1][1]);
//       boolean rv = Boolean.parseBoolean(right[1][1]);
//       switch(op)
//       {
//         case AND:
//           ans = lv && rv;
//           break;
//         case OR:
//           ans = lv || rv;
//           break;
//         case EQUALS:
//           ans = lv == rv;
//           break;
//         case NOT_EQUAL:
//           ans = lv != rv;
//           break;
//         case NOT:
//           ans = !lv;
//           break;
//         case ITSELF:
//           ans = lv;
//           break;
//         default:
//           System.exit(1);
//           break;
//       }
//     }
//     else
//     {
//       int lv = Integer.parseInt(left[1][1]);
//       int rv = Integer.parseInt(right[1][1]);
//       switch(op)
//       {
//         case GREATER_THAN:
//           ans = lv > rv;
//           break;
//         case GREATER_THAN_OR_EQUAL:
//           ans = lv >= rv;
//           break;
//         case EQUALS:
//           ans = lv == rv;
//           break;
//         case NOT_EQUAL:
//           ans = lv != rv;
//           break;
//         case LESS_THAN:
//           ans = lv < rv;
//           break;
//         case LESS_THAN_OR_EQUAL:
//           ans = lv <= rv;
//           break;
//         default:
//           System.exit(1);
//           break;
//       }
//     }
// //     System.out.println(op + " "+left[0][0] +" "+ left[1][0]);
//     q0 = q0.replaceFirst("<val>", left[0][0]);
//     a0 = a0.replaceFirst("<val>", left[1][0]);
//     if(!op.equals(BoolOps.NOT) 
//       && !op.equals(BoolOps.ITSELF))
//     {
//       q0 = q0.replaceFirst("<val>", right[0][0]);
//       a0 = a0.replaceFirst("<val>", right[1][0]);
//     }
//     
//     if(!left[1][0].equals(left[1][1]) && left[1][0].length() > 2)
//     {
//       //if not a variable.. it's a function call
//       q0 = moveArgsToEnd(q0);
//     }
//     if(!right[1][0].equals(right[1][1]) && right[1][0].length() > 2)
//     {
//       //if not a variable.. it's a function call
//       q0 = moveArgsToEnd(q0);
//     }
//     
//     String[][] answers = new String[2][2];
//     
//     answers[0][0] = q0;
//     answers[0][1] = q1;
//     answers[1][0] = a0;
//     answers[1][1] = String.valueOf(ans);
//     
//     return answers;
//   }
//   
//   /**
//    * Generate a random if statement (possibly make else a random appearance)
//    * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
//    */
//   public String[][] generateIfStatement()
//   {
//     String q0 = "Build a statement such that, if <boolxp> is true then <exp>; otherwise <exp>.";
//     String q1 = "";
//     String a0 = "if(<boolxp>)\n{\n<exp>\n}\nelse\n{\n<exp>\n}";
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
//     Types return_type = Types.randomType();
//     //exp can be: return or complexReturn
//     String[][] then = new String[2][2];
//     String[][] els = new String[2][2];
//     
//     then = generateReturn(return_type);
//     els = generateReturn(return_type);
//     
//     q0 = q0.replaceFirst("<exp>", then[0][0]);
//     a0 = a0.replaceFirst("<exp>", then[1][0] + ";");
//     
//     q0 = q0.replaceFirst("<exp>", els[0][0]);
//     a0 = a0.replaceFirst("<exp>", els[1][0] + ";");
//     
//     q0 = q0.replaceAll("G", "g");
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
//   /**
//    * Generate a random while statement
//    * @return 2x2 String array. Provide expression (0,0), expression (1,0), solve the expression (0,1), evaluation/solution (1,1).
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
//   /**
//    * Given a sentence, move the arguments for a function call to the end
//    * Assumes the sentence contains a function call
//    * @param s the sentence with one function calls
//    * @return the problem sentence followed by a separate sentence specifying the function arguments
//    */
//   public static String moveArgsToEnd(String s)
//   {
//     int order = s.indexOf("order") + 5;
//     String args = "";
//     String sentence = s;
//     //one argument
//     if(order >= 5)
//     {
//       args = s.substring(s.indexOf("."), order);
//     }
//     else if (order < 5 && s.indexOf("argument:") != -1)
//     {
//       order = s.indexOf(" ", s.indexOf("argument:")+2); //first space after
//       args = s.substring(s.indexOf("."), order);
//     }
//     sentence = s.replaceFirst(args, "") + args;
//     
//     return sentence;
// //     return s;
//   }
}

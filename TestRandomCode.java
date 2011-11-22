public class TestRandomCode
{
  public static void main (String[] args)
  {
    RandomCode rc = new RandomCode();
    
    System.out.println("Random function call:");
    ChallengePair testfc = rc.generateFunctionCall();
    System.out.println(testfc);

//     System.out.println();
//     
    System.out.println("Random return:");
    ChallengePair r = rc.generateReturn();
    System.out.println(r);

//     System.out.println();
// 
//     System.out.println("Random declarations:");
//     String[][] testd = rc.generateDeclaration();
//     System.out.println(testd[0][0]);
//     System.out.println(testd[1][0]);
//     
    System.out.println();

    System.out.println("Random assignments:");
    ChallengePair testa = rc.generateAssignment();
    System.out.println(testa);
    
    System.out.println();

    System.out.println("Random junit:");
    ChallengePair testjun = rc.generateJUnit();
    System.out.println(testjun);
    
//     System.out.println();
// 
//     System.out.println("Random Function Header:");
//     String[][] testfh= rc.generateFunctionHeader();
//     System.out.println(testfh[0][0]);
//     System.out.println(testfh[1][0]);
//     
//     System.out.println();
// 
    System.out.println("Random numeric comparison:");
    ChallengePair comp = rc.generateBooleanExpr(true);
    System.out.println(comp);
    
    System.out.println();

    System.out.println("Random boolean expression:");
    ChallengePair bool = rc.generateBooleanExpr(false);
    System.out.println(bool);
    
    System.out.println();

    System.out.println("Random complex boolean expression:");
    ChallengePair compbool = rc.generateComplexBoolean();
    System.out.println(compbool);
    
    System.out.println();
    
    System.out.println("Random if statement:");
    ChallengePair ifs = rc.generateIfStatement();
    System.out.println(ifs);
    
    System.out.println();
    
    System.out.println("Random one line function:");
    ChallengePair fn = rc.generateSimpleFunction();
    System.out.println(fn);

//     System.out.println("Random while statement:");
//     String[][] whl = rc.generateWhileStatement();
//     System.out.println(whl[0][0]);
//     System.out.println(whl[1][0]);
//     System.out.println(whl[0][1]);
//     System.out.println(whl[1][1]);
  }
}

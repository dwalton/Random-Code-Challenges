public class TestRandomCode
{
  public static void main (String[] args)
  {
    RandomCode rc = new RandomCode();
    
    System.out.println("Random function call (no variables):");
    ChallengePair testfc = rc.generateFunctionCall();
    System.out.println(testfc);

//     System.out.println();
//     
//     System.out.println("Random return:");
//     String[][] r = rc.generateReturn(Types.INT);
//     System.out.println(r[0][0]);
//     System.out.println(r[1][0]);
//     r = rc.generateReturn(Types.BOOLEAN);
//     System.out.println(r[0][0]);
//     System.out.println(r[1][0]);
// 
//     System.out.println();
// 
//     System.out.println("Random declarations:");
//     String[][] testd = rc.generateDeclaration();
//     System.out.println(testd[0][0]);
//     System.out.println(testd[1][0]);
//     
//     System.out.println();
// 
//     System.out.println("Random assignments:");
//     String[][] testa = rc.generateAssignment();
//     System.out.println(testa[0][0]);
//     System.out.println(testa[1][0]);
//     
//     System.out.println();
// 
//     System.out.println("Random junit:");
//     String[][] testjun = rc.generateJUnit();
//     System.out.println(testjun[0][0]);
//     System.out.println(testjun[1][0]);
//     System.out.println(testjun[0][1]);
//     System.out.println(testjun[1][1]);
//     
//     System.out.println();
// 
//     System.out.println("Random Function Header:");
//     String[][] testfh= rc.generateFunctionHeader();
//     System.out.println(testfh[0][0]);
//     System.out.println(testfh[1][0]);
//     
//     System.out.println();
// 
//     System.out.println("Random numeric comparison:");
//     String[][] comp = rc.generateBooleanExpr(true);
//     System.out.println(comp[0][0]);
//     System.out.println(comp[1][0]);
//     System.out.println(comp[0][1]);
//     System.out.println(comp[1][1]);
//     
//     System.out.println();
// 
//     System.out.println("Random boolean expression:");
//     String[][] bool = rc.generateBooleanExpr(false);
//     System.out.println(bool[0][0]);
//     System.out.println(bool[1][0]);
//     System.out.println(bool[0][1]);
//     System.out.println(bool[1][1]);
//     
//     System.out.println();
// 
//     System.out.println("Random if statement:");
//     String[][] ifs = rc.generateIfStatement();
//     System.out.println(ifs[0][0]);
//     System.out.println(ifs[1][0]);
//     System.out.println(ifs[0][1]);
//     System.out.println(ifs[1][1]);
//     
//     System.out.println();
// 
//     System.out.println("Random while statement:");
//     String[][] whl = rc.generateWhileStatement();
//     System.out.println(whl[0][0]);
//     System.out.println(whl[1][0]);
//     System.out.println(whl[0][1]);
//     System.out.println(whl[1][1]);
  }
}

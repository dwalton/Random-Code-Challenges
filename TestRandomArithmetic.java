public class TestRandomArithmetic
{
  public static void main (String[] args)
  {
    RandomArithmetic ra = new RandomArithmetic(20);
//     System.out.println(7 % 0);
    
    System.out.println("Random simple:");
    ChallengePair test = ra.generateMath(false);
    System.out.println(test);

    System.out.println();

    System.out.println("Random complex:");
    ChallengePair test2 = ra.generateComplex(1);
    System.out.println(test2);

    System.out.println();

//     System.out.println("Random Complex with one variable:");
//     String[][] testComplexVar = ra.generateComplex(true, 1);
//     System.out.println(testComplexVar[0][0]);
//     System.out.println(testComplexVar[1][0]);
//     System.out.println(testComplexVar[0][1]);
//     System.out.println(testComplexVar[1][1]);
  }
}

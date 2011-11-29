/**
 * The sole purpose of this class is to output 
 * questions in a way that Google Docs can make
 * a form from it;
 * All questions need to be on one line with "" 
 * instead of "
 * 
 * @author Doug Walton
 * @version 1
 */
public class MakeGoogleForm
{
  public static void main(String[] args)
  {
    RandomArithmetic ra = new RandomArithmetic();
    RandomCode rc = new RandomCode();
    System.out.print(",");
    
    System.out.print(ra.generateMath(false).toGoogleForm() + ",");
    System.out.print(ra.generateMath(false).toGoogleForm() + ",");
    System.out.print(ra.generateMath(false).toGoogleForm() + ",");
    System.out.print(ra.generateMath(false).toGoogleForm() + ",");
    System.out.print(ra.generateMath(false).toGoogleForm() + ",");
    
    System.out.print(ra.generateComplex().toGoogleForm() + ",");
    System.out.print(ra.generateComplex().toGoogleForm() + ",");
    System.out.print(ra.generateComplex().toGoogleForm() + ",");
    System.out.print(ra.generateComplex().toGoogleForm() + ",");
    System.out.print(ra.generateComplex().toGoogleForm() + ",");
    
    System.out.print(rc.generateFunctionCall(false).toGoogleForm() + ",");
    System.out.print(rc.generateFunctionCall(false).toGoogleForm() + ",");
    System.out.print(rc.generateFunctionCall(false).toGoogleForm() + ",");
    System.out.print(rc.generateFunctionCall(false).toGoogleForm() + ",");
    System.out.print(rc.generateFunctionCall(false).toGoogleForm() + ",");
    
    System.out.print(rc.generateAssignment().toGoogleForm() + ",");
    System.out.print(rc.generateAssignment().toGoogleForm() + ",");
    System.out.print(rc.generateAssignment().toGoogleForm() + ",");
    System.out.print(rc.generateAssignment().toGoogleForm() + ",");
    System.out.print(rc.generateAssignment().toGoogleForm() + ",");
    
    System.out.print(rc.generateJUnit().toGoogleForm() + ",");
    System.out.print(rc.generateJUnit().toGoogleForm() + ",");
    System.out.print(rc.generateJUnit().toGoogleForm() + ",");
    System.out.print(rc.generateJUnit().toGoogleForm() + ",");
    System.out.print(rc.generateJUnit().toGoogleForm() + ",");
    
    System.out.print(rc.generateBooleanExpr(true).toGoogleForm() + ",");
    System.out.print(rc.generateBooleanExpr(true).toGoogleForm() + ",");
    System.out.print(rc.generateBooleanExpr(true).toGoogleForm() + ",");
    System.out.print(rc.generateBooleanExpr(true).toGoogleForm() + ",");
    System.out.print(rc.generateBooleanExpr(true).toGoogleForm() + ",");
    
    System.out.print(rc.generateBooleanExpr(false).toGoogleForm() + ",");
    System.out.print(rc.generateBooleanExpr(false).toGoogleForm() + ",");
    System.out.print(rc.generateBooleanExpr(false).toGoogleForm() + ",");
    System.out.print(rc.generateBooleanExpr(false).toGoogleForm() + ",");
    System.out.print(rc.generateBooleanExpr(false).toGoogleForm() + ",");
    
    System.out.print(rc.generateComplexBoolean().toGoogleForm() + ",");
    System.out.print(rc.generateComplexBoolean().toGoogleForm() + ",");
    System.out.print(rc.generateComplexBoolean().toGoogleForm() + ",");
    System.out.print(rc.generateComplexBoolean().toGoogleForm() + ",");
    System.out.print(rc.generateComplexBoolean().toGoogleForm() + ",");
    
    System.out.print(rc.generateIfStatement().toGoogleForm() + ",");
    System.out.print(rc.generateIfStatement().toGoogleForm() + ",");
    System.out.print(rc.generateIfStatement().toGoogleForm() + ",");
    System.out.print(rc.generateIfStatement().toGoogleForm() + ",");
    System.out.print(rc.generateIfStatement().toGoogleForm() + ",");
    
    System.out.print(rc.generateSimpleFunction().toGoogleForm() + ",");
    System.out.print(rc.generateSimpleFunction().toGoogleForm() + ",");
    System.out.print(rc.generateSimpleFunction().toGoogleForm() + ",");
    System.out.print(rc.generateSimpleFunction().toGoogleForm() + ",");
    System.out.print(rc.generateSimpleFunction().toGoogleForm());
  }
}
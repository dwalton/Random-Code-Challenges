import java.util.Random;

/**
 * This enum provides an abstraction for operations that return boolean values.
 */
public enum BoolOps 
{
  AND, OR, NOT, ITSELF, NOT_EQUAL, EQUALS, LESS_THAN, GREATER_THAN, LESS_THAN_OR_EQUAL, GREATER_THAN_OR_EQUAL;

  /**
   * For use in problem answers and code segments.
   */
  @Override public String toString() 
  {
    String s = "";
    switch(BoolOps.valueOf(super.toString()))
    {
      case AND:
        s = "&&";
        break;
      case OR:
        s = "||";
        break;
      case NOT:
        s = "!";
        break;
      case NOT_EQUAL:
        s = "!=";
        break;
      case EQUALS:
        s = "==";
        break;
      case LESS_THAN:
        s = "<";
        break;
      case GREATER_THAN:
        s = ">";
        break;
      case LESS_THAN_OR_EQUAL:
        s = "<=";
        break;
      case GREATER_THAN_OR_EQUAL:
        s = ">=";
        break;
      case ITSELF:
        s = "";
        break;
      default:
        break;
    }
    return s;
  }

  /**
   * For use in English sentences.
   */
  public String opName()
  {
    if(super.toString().toLowerCase().equals("itself"))
      return "the boolean";
    return super.toString().toLowerCase().replaceAll("_"," ");
  }

  /**
   * @return a Random boolean operation
   * @param comparison true for a numeric comparison (9 > 2), false for a boolean expression (and/or)
   */
  public static BoolOps generateBoolOp(boolean comparison)
  {
    int op = 0;
    Random r = new Random();
    if(!comparison)
      op = r.nextInt(5);
    else
      op = 4 + r.nextInt(6);
    BoolOps operation = null;
    switch(op)
    {
      case 0:
        operation = BoolOps.AND;
        break;
      case 1:
        operation = BoolOps.OR;
        break;
      case 2:
        operation = BoolOps.NOT;
        break;
      case 3: 
        operation = BoolOps.ITSELF;
        break;
      case 4:
        operation = BoolOps.NOT_EQUAL;
        break;
      case 5:
        operation = BoolOps.EQUALS;
        break;
      case 6:
        operation = BoolOps.LESS_THAN;
        break;
      case 7:
        operation = BoolOps.GREATER_THAN;
        break;
      case 8:
        operation = BoolOps.LESS_THAN_OR_EQUAL;
        break;
      case 9:
        operation = BoolOps.GREATER_THAN_OR_EQUAL;
        break;
      default:
        System.exit(1);
        break;
    }
    return operation;
  }
}

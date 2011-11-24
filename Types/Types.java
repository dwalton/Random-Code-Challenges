import java.util.Random;

public enum Types 
{
  //may need to add NULL? more of a special case value... not really a type
  STRING, CHAR, INT, DOUBLE, BOOLEAN, VOID;

  /**
   * For using Types in English sentences.
   */
  public String typeName()
  {
    String s = "";
    switch(Types.valueOf(super.toString()))
    {
      case STRING:
        s = "String";
        break;
      case CHAR:
      case INT:
      case DOUBLE:
      case BOOLEAN:
      case VOID:
        s = super.toString().toLowerCase();
        break;
      default:
        break;
    }
    return s;
  }

  /**
   * Not currently used, possibly in the future (use typeName() for proper String representation).
   */
  @Override public String toString()
  {
    return super.toString();
  }
  
  /**
   * @return Same as randomSVal(), but is static (random Types and value instead of just a random value).
   */
  public static String randomVal()
  {
    Types t = randomType();
    return t.randomSVal();
  }
  
  /**
   * @return random String value of current Type.
   *   STRING has max length of 5.  INT has max 49, DOUBLE has max 49.99
   *   CHAR can be a-z, BOOLEAN can be true or false.
   */
  public String randomSVal()
  {
    RandomStrings r = new RandomStrings();
    String s = "";
    switch(this)
    {
      case STRING:
        s = "\"" + r.randomName(5) + "\"";
        break;
      case CHAR:
        s = "\'" + r.randomName(1) + "\'";
        break;
      case INT:
        s = String.valueOf((new Random()).nextInt(50));
        break;
      case DOUBLE:
        s = String.valueOf((new Random()).nextInt(50)) + "." + String.valueOf((new Random()).nextInt(100));
        break;
      case BOOLEAN:
        if((new Random()).nextInt(2) == 1)
          s = "true";
        else
          s = "false";
        break;
      default:
        break;
    }
    return s;
  }

  /**
   * @return random enum from Types (excluding VOID), 
   *   use randomReturnType if you want to include VOID.
   */
  public static Types randomType()
  {
    Random random = new Random();
    Types type = null;
    int i = random.nextInt(5);
    switch(i)
    {
      case 0: 
        type = STRING;
        break;
      case 1:
        type = INT;
        break;
      case 2:
        type = BOOLEAN;
        break;
      case 3:
        type = CHAR;
        break;
      case 4:
        type = DOUBLE;
        break;
      default:
        break;
    }
    return type;
  }
  
  /**
   * @return random RETURN type (like randomType(), but includes VOID).
   */
  public static Types randomReturnType()
  {
    Random random = new Random();
    Types type = null;
    int i = random.nextInt(6);
    switch(i)
    {
      case 0: 
        type = STRING;
        break;
      case 1:
        type = INT;
        break;
      case 2:
        type = BOOLEAN;
        break;
      case 3:
        type = CHAR;
        break;
      case 4:
        type = DOUBLE;
        break;
      case 5:
        type = VOID;
        break;
      default:
        break;
    }
    return type;
  }
}
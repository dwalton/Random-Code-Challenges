import java.util.Random;

/**
 * This enum provides an abstraction for numeric operations
 */
public enum Ops 
{
  //removed SQUARE because it was complicating it too much... 
    //it's basically MULTIPLY anyway
  ADD, SUBTRACT, MULTIPLY,/* SQUARE,*/ DIVIDE, MODULO;

  /**
   * For use in problem answers/code segments
   */
  @Override public String toString() 
  {
    String s = "";
    switch(Ops.valueOf(super.toString()))
    {
      case ADD:
        s = "+";
        break;
      case SUBTRACT:
        s = "-";
        break;
      case MULTIPLY:
        s = "*";
        break;
      case DIVIDE:
        s = "/";
        break;
//         case SQUARE:
//           s = "*";
//           break;
      case MODULO:
        s = "%";
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
    return super.toString().toLowerCase();
  }

  /**
   * Decide the correct connecting word for the given operation
   */
  public String connectingWord()
  { 
    String s = "";
    switch (this)
    {  
      case ADD:
      case MULTIPLY:
        s = "and";
        break;
      case DIVIDE:
      case MODULO:
        s = "by";
        break;
      case SUBTRACT:
        s = "from";
        break;
      default:
        break;
    }
    return s;
  }

  /**
   * Adds ing to an operation where needed in a sentence
   */
  public String addIng()
  {
    String s = "";
    switch (this)
    {  
      case ADD:
      case MULTIPLY:
      case SUBTRACT:
        s = opName() + "ing";
        break;
      case DIVIDE:
        s = "dividing";
        break;
      case MODULO:
        s = "moding";
        break;
      default:
        break;
    }
    return s;
  }
  /**
   * @return a random operator
   */
  public static Ops generateOp()
  {
    Random random = new Random();
    int op = random.nextInt(5);
    Ops operation = null;
    switch(op)
    {
      case 0:
        operation = Ops.ADD;
        break;
      case 1:
        operation = Ops.SUBTRACT;
        break;
      case 2:
        operation = Ops.MULTIPLY;
        break;
      case 3:
        operation = Ops.DIVIDE;
        break;
      case 4:
        operation = Ops.MODULO;
        break;
//       case 5:
//         operation = Ops.SQUARE;
//         break;
      default:
        System.exit(1);
        break;
    }
    return operation;
  }
}
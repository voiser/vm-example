import org.scalatest.FunSuite
import vm._
import ast._
import defaults._
import grammar._
import org.antlr.v4.runtime._

class SimpleTests extends FunSuite {

  def root = {
    val f = new Frame(None)
    f.storelocal("add", new IntAdd)
    f.storelocal("mul", new IntMul)
    f.storelocal("print", new Print)
    Some(f)
  }

  def parse(code: String) = {
    val lexer = new PlayLexer(new ANTLRInputStream(code))
    val parser = new PlayParser(new CommonTokenStream(lexer))
    val cst = parser.prog()
    new AstVisitor().visitProg(cst)
  }

  test("traverse") {
    val f = new Frame(root)
    val ast = NBlock(List(
      NDef("plus1",
        NFn(List("x"),
          NCall("add", List(
            NRef("x"),
            NInt(1))))),
      NCall("plus1", List(
        NInt(8))),
      NCall("print", List(
        NStr("Hola Mundo")))))
    Traverse.traverse(f, ast)
    assert(f.stack.toList == List(Nothing(), Int(9)))
  }

  test("parse") {
    val code =
      """
        |a = 1
        |b = 2
        |c = add c(a, b)
      """.stripMargin
    val ast = parse(code)
    assert(ast == NBlock(List(
      NDef("a", NInt(1)),
      NDef("b", NInt(2)),
      NDef("c", NRef("add")),
      NCall("c", List(
        NRef("a"),
        NRef("b")
      ))
    )))
    val f = new Frame(root)
    Traverse.traverse(f, ast)
  }
}

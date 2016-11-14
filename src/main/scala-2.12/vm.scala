package vm

import scala.collection.mutable
import ast._

/*
 * Runtime objects
 */
abstract class Obj {
  def getType: String
}
case class Nothing() extends Obj {
  override def getType: String = "Nothing"
  override def toString: String = "Nothing"
}
case class Int(i: java.lang.Integer) extends Obj {
  override def getType: String = "Int"
}
case class Str(s: java.lang.String) extends Obj {
  override def getType: String = "String"
  override def toString: String = s
}
abstract class Fun extends Obj {
  def apply(f: Frame) : Unit
}
class AstFun(fn: NFn) extends Fun {
  override def getType: String = s"Function(${fn.params.length})"
  override def apply(f: Frame): Unit = {
    fn.params.reverse.foreach { p =>
      f.storelocal(p)
    }
    Traverse.traverse(f, fn.body)
  }
  override def toString: String = s"FunctionObject(${fn.params.length})"
}



/*
 * Basically this is the entire VM
 */
class Frame(parent: Option[Frame]) {
  val stack = mutable.Stack[Obj]()
  val locals = mutable.Map[String, Obj]()

  def push(o: Obj) = stack.push(o)

  def pop() = stack.pop

  def storelocal(name: String) = locals.put(name, pop())

  def storelocal(name: String, o: Obj) = locals.put(name, o)

  def getlocal(name: String) : Option[Obj] = locals.get(name) match {
    case x : Some[Obj] => x
    case None => parent match {
      case Some(p) => p.getlocal(name)
      case None => None
    }
  }

  def pushlocal(name: String) = getlocal(name) match {
    case Some(o) => push(o)
    case None => throw new Exception(s"No such local ${name}")
  }

  def inspect = {
    System.out.println(stack.mkString("Stack(", ",", ")"))
    System.out.println(locals.mkString("Locals(", ",", ")"))
  }

  def checkType(o: Obj, ty: String) =
    if (o.getType.equals(ty)) true
    else throw new Exception(s"${o.getType} is not a ${ty}")

  def call() = pop() match {
    case x : Fun => x.apply(this)
    case y @ _ => throw new Exception(s"${y} is not a function")
  }
}

object Traverse {
  def traverse(f: Frame, ast: Node) : Unit = ast match {
    case NBlock(ns) => ns.foreach(n => traverse(f, n))
    case NInt(i) => f.push(Int(i))
    case NStr(x) => f.push(Str(x))
    case NCall(fname, params) =>
      val f2 = new Frame(Some(f))
      params.foreach { p =>
        traverse(f, p)
        f2.push(f.pop())
      }
      f2.pushlocal(fname)
      f2.call()
      f.push(f2.pop())
    case NDef(name, ast) =>
      traverse(f, ast)
      f.storelocal(name)
    case NRef(name) => f.pushlocal(name)
    case x @ NFn(params, body) =>
      val o = new AstFun(x)
      f.push(o)
  }
}

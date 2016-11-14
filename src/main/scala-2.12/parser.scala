package ast

import grammar._
import grammar.PlayParser._

import collection.JavaConverters._


abstract class Node
case class NBlock(children: List[Node]) extends Node
case class NInt(i: java.lang.Integer) extends Node
case class NStr(s: java.lang.String) extends Node
case class NCall(fname: String, params: List[Node]) extends Node
case class NDef(name: String, value: Node) extends Node
case class NRef(name: String) extends Node
case class NFn(params: List[String], body: Node) extends Node


class AstVisitor extends PlayBaseVisitor[Node] {

  override def visitCall(ctx: CallContext): Node = {
    val pars = ctx.args().value().asScala.toList.map(visitValue)
    NCall(ctx.ID().getText, pars)
  }

  override def visitRef(ctx: RefContext): Node = {
    NRef(ctx.ID().getText)
  }

  override def visitDefn(ctx: DefnContext): Node = {
    val v = visit(ctx.value())
    val name = ctx.ID().getText
    NDef(name, v)
  }

  override def visitValue(ctx: ValueContext): Node = {
    if (ctx.INT() != null) NInt(Integer.parseInt(ctx.INT().getText))
    else if (ctx.STRING() != null) NStr(ctx.STRING().getText)
    else visitChildren(ctx)
  }

  override def visitProg(ctx: ProgContext): Node = {
    val x = ctx.stmt().asScala.toList.map(visitStmt)
    NBlock(x)
  }
}

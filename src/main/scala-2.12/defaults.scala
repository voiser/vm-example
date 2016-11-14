package defaults

import vm._

class IntAdd extends Fun {
  override def toString: String = "NativeFunction(2)"
  override def getType: String = "Function(2)"
  def apply(f: Frame) = {
    val o1 = f.pop()
    val o2 = f.pop()
    val t1 = o1.getType
    val t2 = o2.getType
    (t1, t2) match {
      case ("Int", "Int") => f.push(Int(o1.asInstanceOf[Int].i + o2.asInstanceOf[Int].i))
      case _ => throw new Exception(s"Can't add ${t1} and ${t2}")
    }
  }
}

class IntMul extends Fun {
  override def toString: String = "NativeFunction(2)"
  override def getType: String = "Function(2)"
  def apply(f: Frame) = {
    val o1 = f.pop()
    val o2 = f.pop()
    val t1 = o1.getType
    val t2 = o2.getType
    (t1, t2) match {
      case ("Int", "Int") => f.push(Int(o1.asInstanceOf[Int].i * o2.asInstanceOf[Int].i))
      case _ => throw new Exception(s"Can't add ${t1} and ${t2}")
    }
  }
}

class Print extends Fun {
  override def toString: String = "NativeFunction(1)"
  override def getType: String = "Function(1)"
  def apply(f: Frame) = {
    val o1 = f.pop()
    f.push(Nothing())
    System.out.println(o1.toString)
  }
}

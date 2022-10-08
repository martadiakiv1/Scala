package com.a11945524.plc


trait Artikel{
  var id: Int
  var name: String
  var value: Int
}


trait Logger{
  def logAction(actionName: String, name: String): Unit = {println(name,actionName)}
}


class StoreItem(var id: Int, var name: String, var value: Int ) extends Artikel with Logger{
  override def logAction(actionName: String, name: String): Unit ={
    println(name + " " + actionName);
  }
}


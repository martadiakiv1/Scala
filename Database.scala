package com.a11945524.plc

class Database {
  private  var storedItems = Array.empty[StoreItem]


  def delete(id: Integer): Unit = {
    val newItems = storedItems.filter(i => i.id != id)
    val deletedItem = storedItems.filter(i => i.id == id)

    if(deletedItem.isEmpty)
      println("Id " + id + "nicht gefunden");
    else {
      deletedItem(0).logAction("gelöscht", deletedItem(0).name)
      storedItems = newItems
    }

  }


  def high(f : (String, String)=>Unit, x: StoreItem): Unit ={
    f(x.name, x.value.toString);
  }

  def higherThan(value: Int): Array[StoreItem] = {
    var newItems = storedItems.filter(i => i.value > value);
     newItems.sortBy(_.value);
     newItems.foreach(i=> high(i.logAction, i ))
     newItems
  }



  def sortByValueAsc(): Array[StoreItem] = {
    storedItems.sortBy(_.value)
    storedItems
  }

  def sortByValueDesc(): Array[StoreItem] = {
    storedItems.sortWith((x, y) => x.value > y.value)
    storedItems
  }


  def high2(f:(String, String) => Unit, x :StoreItem): Unit = {
    f(x.name, x.value.toString)
  }

  def higherThan2(value : Int): Array[StoreItem] = {
    var newItems = storedItems.filter(i => i.value > value)
    newItems.sortBy(_.value)
    newItems.foreach(x => high2(x.logAction, x))
    newItems
  }

  def search(name : String) : StoreItem = {
    val foundVal = storedItems.filter(i => i.name == name)
    if(foundVal.isEmpty)
      println(name + "nicht gefunden")
    else
      foundVal.foreach(i => i.logAction("gefunden", name))

    foundVal(0)
  }



  def store(item : StoreItem) : Array[StoreItem] = {
    storedItems = storedItems :+ item
    item.logAction("gespeichert", item.name)
    storedItems
  }


   def sumUp(): Integer = {
     var sum = 0
     storedItems.foreach(i => sum += i.value)
     sum
   }


}

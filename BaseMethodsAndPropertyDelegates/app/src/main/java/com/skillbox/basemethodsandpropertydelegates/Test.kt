package com.skillbox.basemethodsandpropertydelegates

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


fun main(){

    val man1 = Person(180, 70, "Иосиф")
    val man2 = Person(180, 70, "Иосиф")
    val set = hashSetOf(man1, man2)
    println("${set.size}")
    val man3 = Person(150, 45, "Анжела")
    set.add(man3)
    set.forEach{println(it)}
    set.forEach{it.buyPet()}
    set.forEach{it.buyPet()}
}
class PrintPersonNameAndListOfAnimal<T>(
        private var value: T
): ReadOnlyProperty<Person, T> {
    override fun getValue(thisRef: Person, property: KProperty<*>): T {
        println("Имя = ${thisRef.name}, животные = $value" )
        return value
    }

}

import arrow.core.Option
import arrow.core.Tuple2
import arrow.core.toT

typealias KleisliOptional<A> = Tuple2<Option<A>, String>

fun main() {
    val optional20: KleisliOptional<Int> = Option.just(20) toT "просто двадцать"
    val optional30: KleisliOptional<Int> = Option.fromNullable(null) toT "а тут налл"

    val composed = optional20 compose optional30
    val computationResult = composed.a.fold({ 0 }, { it })
    val log = composed.b
    println("Результат сложения: $computationResult\nЛог: $log")
}

infix fun <A> KleisliOptional<A>.compose(another: KleisliOptional<A>): KleisliOptional<A> {
    return this.a.and(another.a) toT ("${this.b} а потом вдруг бац и ${another.b}")
}

fun <A> KleisliOptional<A>.identity() = this

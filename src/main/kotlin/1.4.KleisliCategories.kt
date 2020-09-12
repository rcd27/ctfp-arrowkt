import arrow.core.Option
import arrow.core.Tuple2
import arrow.core.toT

typealias KleisliOptional<A> = Tuple2<Option<A>, String>

fun main() {
    val optional20: KleisliOptional<Int> = Option.just(20) toT "just twelve"
    val optional30: KleisliOptional<Int> = Option.fromNullable(null) toT "unfortunately null"
    val optional40: KleisliOptional<Int> = Option.just(40) toT "forty"

    val composed = optional20 compose optional30 compose optional40
    val computationResult = composed.a.fold({ 0 }, { it })
    val log = composed.b
    println("Computation result: $computationResult\nLog: $log")
}

infix fun <A> KleisliOptional<A>.compose(another: KleisliOptional<A>): KleisliOptional<A> {
    return this.a.and(another.a) toT ("${this.b} and then ${another.b}")
}

fun <A> KleisliOptional<A>.identity() = this

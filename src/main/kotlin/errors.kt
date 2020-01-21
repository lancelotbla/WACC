package ic.org

import arrow.core.Validated
import arrow.core.Validated.*
import arrow.core.extensions.list.foldable.forAll
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

typealias Errors = PersistentList<CompilationError>
typealias Parsed<A> = Validated<Errors, A>

sealed class CompilationError {
  abstract val msg: String
  abstract val code: Int
}

class SyntacticError(override val msg: String) : CompilationError() {
  override val code = 100
}

sealed class SemanticError : CompilationError() {
  override val code = 200
}

inline val <reified A> Parsed<A>.errors: Errors
  get() = when (this) {
    is Invalid -> this.e
    else -> persistentListOf()
  }

inline val <reified A> List<Parsed<A>>.errors: Errors
  get() = this.flatMap { it.errors }.toPersistentList()

inline val <reified A> List<Parsed<A>>.allValid: Boolean
  get() = this.forAll { it is Valid }

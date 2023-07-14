package com.github.yuuki1293.parser

import scala.util.parsing.combinator.*
import scala.util.parsing.input.Reader

object CParser extends JavaTokenParsers with IParser {
  def integer_constant: CParser.Parser[Option[String] ~ String ~ Option[String]] =
    opt("""0[xX]?""".r) ~ integer ~ opt("[uU]?[lL]?")

  def integer: Parser[String] =
    """(0|[1-9]\d*)""".r

  override def parse[A](in: Reader[Char]): CParser.ParseResult[A] = ???
}
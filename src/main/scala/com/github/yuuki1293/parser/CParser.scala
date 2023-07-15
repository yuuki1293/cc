package com.github.yuuki1293.parser

import scala.util.matching.Regex
import scala.util.parsing.combinator.*
import scala.util.parsing.input.Reader

object CParser extends JavaTokenParsers {
  def integerConstant: Parser[String ~ String ~ Option[String]] =
    "[+-]?".r
      ~ (decimalConstant | octalConstant | hexConstant)
      ~ opt("[uU]?[lL]?".r)

  private def decimalConstant: Parser[String] =
    """0|[1-9]\d*""".r

  private def octalConstant: Parser[String] =
    """0[0-7]+""".r

  private def hexConstant: Parser[String] =
    """0[xX][\da-fA-F]+""".r
}
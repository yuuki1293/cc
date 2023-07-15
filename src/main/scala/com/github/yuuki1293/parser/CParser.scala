package com.github.yuuki1293.parser

import scala.util.matching.Regex
import scala.util.parsing.combinator.*
import scala.util.parsing.input.Reader

object CParser extends JavaTokenParsers {
  /**
   * 整数のパーサー。
   *
   * 整定数が符号なしであることを示すには、文字uあるいはUをあとに付けて良い。
   * またそれがlongであることを示すのに文字lあるいはLを付けることができる。
   */
  def integerConstant: Parser[String ~ Option[String]] =
      (decimalConstant | octalConstant | hexConstant)
      ~ opt("[uU]?[lL]?".r)

  /**
   * 10進数のパーサー。
   *
   * 0以外から始まる数字の列。
   * 数字の列は0~9を含む。
   */
  private def decimalConstant: Parser[String] =
    """0|[1-9]\d*""".r

  /**
   * 8進数のパーサー。
   *
   * 0から始まる数字の列。
   * 数字の列は0~7を含む。
   */
  private def octalConstant: Parser[String] =
    """0[0-7]+""".r

  /**
   * 16進数のパーサー。
   *
   * 0xあるいは0Xで始まる数字の列。
   * 数字の列は0～9、a～fまたはA～Fを含む。
   */
  private def hexConstant: Parser[String] =
    """0[xX][\da-fA-F]+""".r
}
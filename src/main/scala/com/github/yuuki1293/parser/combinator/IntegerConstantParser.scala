package com.github.yuuki1293.parser.combinator

import scala.util.matching.Regex
import scala.util.parsing.combinator.*
import scala.util.parsing.input.Reader

object IntegerConstantParser extends JavaTokenParsers {
  /**
   * 整数のパーサー。
   *
   * 整定数が符号なしであることを示すには、文字uあるいはUをあとに付けて良い。
   * またそれがlongであることを示すのに文字lあるいはLを付けることができる。
   */
  def integerConstant: Regex =
    s"""($octalConstant|$hexConstant|$decimalConstant)[uU]?[lL]?""".r

  /**
   * 10進数にマッチする。
   *
   * 0以外から始まる数字の列。
   * 数字の列は0~9を含む。
   */
  private def decimalConstant =
    """0|[1-9]\d*"""

  /**
   * 8進数にマッチする。
   *
   * 0から始まる数字の列。
   * 数字の列は0~7を含む。
   */
  private def octalConstant =
    """0[0-7]+"""

  /**
   * 16進数にマッチする。
   *
   * 0xあるいは0Xで始まる数字の列。
   * 数字の列は0～9、a～fまたはA～Fを含む。
   */
  private def hexConstant =
    """0[xX][\da-fA-F]+"""
}
package com.github.yuuki1293.parser.combinator

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

object FloatingConstantParser extends RegexParsers {
  /**
   * 浮動小数点リテラルをパースする。
   *
   * 整数部分、小数点、小数部分、eまたはE、整数の指数（符号は省略可）、および省略可能な型接尾子（f、F、l、Lのいずれか）からなる。
   * 整数と小数はともに数字の列からなり、一方は省略可能。eと指数もしくは小数点の一方は省略可能。
   */
  def floatingConstant: Regex =
    s"""($floating1|$floating2|$floating3)[fFlL]?""".r

  /**
   * 仮数部にマッチする。
   *
   * eまたはEの後に指数が続く。
   */
  private val mantissa =
    """[eE][+-]?\d+"""

  /**
   * 整数部分、小数点、小数部分、仮数部にマッチする。
   *
   * 整数部分と仮数部は省略できる。
   */
  private val floating1 =
    """\d*\.\d+(""" + mantissa + """)?"""

  /**
   * 整数部分、小数点、仮数部にマッチする。
   *
   * 仮数部は省略できる。
   */
  private val floating2 =
    """\d+\.(""" + mantissa + """)?"""

  /**
   * 整数部分、仮数部にマッチする。
   */
  private val floating3 =
    """\d+""" + mantissa
}

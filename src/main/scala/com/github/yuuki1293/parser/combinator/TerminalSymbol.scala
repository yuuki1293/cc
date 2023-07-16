package com.github.yuuki1293.parser.combinator

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

object TerminalSymbol extends RegexParsers {
  /**
   * 整数リテラルのパーサー。
   *
   * 整定数が符号なしであることを示すには、文字uあるいはUをあとに付けて良い。
   * またそれがlongであることを示すのに文字lあるいはLを付けることができる。
   */
  def integerConstant: Regex =
    s"""($octal|$hex|$decimal)[uU]?[lL]?""".r

  /**
   * 10進数にマッチする。
   *
   * 0以外から始まる数字の列。
   * 数字の列は0~9を含む。
   */
  private val decimal =
    """0|[1-9]\d*"""

  /**
   * 8進数にマッチする。
   *
   * 0から始まる数字の列。
   * 数字の列は0~7を含む。
   */
  private val octal =
    """0[0-7]+"""

  /**
   * 16進数にマッチする。
   *
   * 0xあるいは0Xで始まる数字の列。
   * 数字の列は0～9、a～fまたはA～Fを含む。
   */
  private val hex =
    """0[xX][\da-fA-F]+"""

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

  /**
   * 文字リテラルをパースする。
   *
   * 文字定数は'x'のように単一引用符で囲まれた一つ以上の文字の列。
   * 文字定数には'文字あるいは改行は含まれない。
   */
  def charConstant: Regex =
    s"""L?'($escapedChar|$oneChar)'""".r

  /**
   * エスケープされた文字にマッチする。
   * マッチするエスケープ列は以下の通り。
   *
   * \n、\t、\v、\b、\r、\f、\a、\\、\?、\'、\"、\ooo、\xhh
   *
   * エスケープ\oooは、バックスラッシュの後に1～3個の8進数を並べたもので、望む文字の値を表すのに使われる。
   * エスケープ\xhhはバックスラッシュの後にx、さらに16進数を続けたもので、これも望む文字の値を表すのに使われる。
   */
  private val escapedChar =
    """\\([ntvbrfa\\?'"]|[0-7]{1,3}|x[\da-fA-F]+)"""

  /**
   * 1文字にマッチする。
   *
   * NUL、LF、CR、\、'は文字リテラルに含まれない。
   */
  private val oneChar =
    """[^\x00\f\n\\']"""

  /**
   * 識別子をパースする。
   *
   * アルファベット、アンダーバーまたは数字で構成される。
   * 数字は先頭には使用できない。
   */
  def identifier: Regex =
    """[a-zA-Z_]\w""".r

  /**
   * 列挙定数をパースする。
   *
   * アルファベット、アンダーバーまたは数字で構成される。
   * 数字は先頭には使用できない。
   */
  def enumerationConstant: Regex = identifier

  def string: Parser[List[String]] =
    rep1(s"""L?\"($escapedChar|$chars)*\"""".r)

  /**
   * 複数文字にマッチする。
   *
   * NUL、LF、CR、\、"は文字リテラルに含まれない。
   */
  private val chars =
    """[^\x00\f\n\\"]"""
}

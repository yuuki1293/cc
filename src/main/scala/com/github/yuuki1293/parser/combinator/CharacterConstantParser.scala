package com.github.yuuki1293.parser.combinator

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

object CharacterConstantParser extends RegexParsers {
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
}

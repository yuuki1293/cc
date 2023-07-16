package com.github.yuuki1293.parser.combinator

import com.github.yuuki1293.parser.combinator.TerminalSymbol.*
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.parsing.combinator.RegexParsers

class TerminalSymbolSpec extends AnyFlatSpec with Diagrams {
  "integerConstant" should "10進数をパースする。" in {
    List(
      "0",
      "1234567890",
      "1234567890u",
      "1234567890l",
      "1234567890ul"
    )
      .map(parseAll(integerConstant, _).successful)
      .foreach(assert(_))
  }

  it should "8進数をパースする。" in {
    List(
      "00",
      "012345670",
      "012345670u",
      "012345670l",
      "012345670ul"
    )
      .map(parseAll(integerConstant, _).successful)
      .foreach(assert(_))
  }

  it should "16進数をパースする。" in {
    List(
      "0x0",
      "0x123aF",
      "0x123aFu",
      "0x123aFl",
      "0x123aFul"
    )
      .map(parseAll(integerConstant, _).successful)
      .foreach(assert(_))
  }

  it should "パースに失敗する。" in {
    List(
      "",
      "ABC",
      "09",
      "0xG",
      "u",
      "5 u"
    )
      .map(!parseAll(integerConstant, _).successful)
      .foreach(assert(_))
  }

  "charConstant" should "1文字をパースする。" in {
    List(
      """' '""",
      """'a'""",
      """'\n'""",
      """'\\'""",
      """'\0'""",
      """'\x1F00'""",
      "'\t'",
      """L'あ'"""
    )
      .map(parseAll(charConstant, _).successful)
      .foreach(assert(_))
  }

  it should "パースに失敗する。" in {
    List(
      "",
      """''""",
      """'ab'""",
      """'\'""",
      "'\n'",
      """'\900'""",
      """'''""",
      "'\u0000'"
    )
      .map(!parseAll(charConstant, _).successful)
      .foreach(assert(_))
  }

  "floatingConstant" should "少数にマッチする。" in {
    List(
      "31.41e-1f",
      ".56e3l",
      ".123",
      "10.e5",
      "12.",
      "123e4"
    )
      .map(parseAll(floatingConstant, _).successful)
      .foreach(assert(_))
  }

  it should "パースに失敗する。" in {
    List(
      "",
      ".",
      "e21",
      "0",
      "a"
    )
      .map(!parseAll(floatingConstant, _).successful)
      .foreach(assert(_))
  }

  "identifier" should "識別子をパースする。" in {
    List(
      "AbC",
      "_10",
      "_"
    )
      .map(parseAll(identifier, _).successful)
      .foreach(assert(_))
  }

  it should "パースに失敗する" in {
    List(
      "",
      "10c"
    )
      .map(!parseAll(identifier, _).successful)
      .foreach(assert(_))
  }

  "string" should "文字列をパースする。" in {
    List(
      "\"\"",
      "\"abc\"",
      "\"\\t\\n\"",
      "\"\t\"",
      "\"ab\" \"cd\\n\""
    ).foreach(x => assert(parseAll(string, x).successful))
  }

  it should "パースに失敗する。" in {
    List(
      "\"\"\"",
      "\"",
      ""
    ).foreach(x => assert(!parseAll(string, x).successful))
  }
}

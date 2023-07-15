package com.github.yuuki1293.parser

import com.github.yuuki1293.parser.CParser.parseAll
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.input.CharArrayReader

class CParserSpec extends AnyFlatSpec with Diagrams {
  private def parseSuccessful(input: CharSequence) =
    parseAll(CParser.integerConstant, input)
      .successful

  private def parseFailure(input: CharSequence) =
    !parseSuccessful(input)

  "integerConstant" should "10進数をパースする。" in {
    assert(parseSuccessful("0"))
    assert(parseSuccessful("1234567890"))
    assert(parseSuccessful("1234567890u"))
    assert(parseSuccessful("1234567890l"))
    assert(parseSuccessful("1234567890ul"))
  }

  it should "8進数をパースする。" in {
    assert(parseSuccessful("00"))
    assert(parseSuccessful("012345670"))
    assert(parseSuccessful("012345670u"))
    assert(parseSuccessful("012345670l"))
    assert(parseSuccessful("012345670ul"))
  }

  it should "16進数をパースする。" in {
    assert(parseSuccessful("0x0"))
    assert(parseSuccessful("0x123aF"))
    assert(parseSuccessful("0x123aFu"))
    assert(parseSuccessful("0x123aFl"))
    assert(parseSuccessful("0x123aFul"))
  }

  it should "パースに失敗する。" in {
    assert(parseFailure(""))
    assert(parseFailure("ABC"))
    assert(parseFailure("09"))
    assert(parseFailure("0xG"))
    assert(parseFailure("u"))
  }
}

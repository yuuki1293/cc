package com.github.yuuki1293.parser

import com.github.yuuki1293.parser.CParser.parseAll
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.input.CharArrayReader

class CParserSpec extends AnyFlatSpec with Diagrams {
  "integerConstant" should "Successfully" in {
    def parseSuccessful(input: CharSequence) =
      parseAll(CParser.integerConstant, input)
        .successful

    assert(parseSuccessful("0"))
    assert(parseSuccessful("123"))
    assert(parseSuccessful("0123"))
    assert(parseSuccessful("0xAF"))
    assert(parseSuccessful("123u"))
    assert(parseSuccessful("123ul"))
    assert(parseSuccessful("0x12F0ul"))
  }

  "integer" should "Successfully parse integer literal" in {
    def parseSuccessful(input: CharSequence) =
      parseAll(CParser.integer, input)
        .successful

    assert(parseSuccessful("123"))
    assert(parseSuccessful("0"))
  }

  it should "Fail to parse integer literal" in {
    def parseFailure(input: CharSequence) =
      !parseAll(CParser.integer, input)
        .successful

    assert(parseFailure("123."))
    assert(parseFailure("0124"))
    assert(parseFailure(".123"))
  }
}

package com.github.yuuki1293.parser.combinator

import com.github.yuuki1293.parser.combinator.FloatingConstantParser.floatingConstant
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.parsing.combinator.RegexParsers

class FloatingConstantParserSpec extends AnyFlatSpec with Diagrams with RegexParsers {
  private def parseSuccessful(input: CharSequence) =
    parseAll(floatingConstant, input)
      .successful

  private def parseFailure(input: CharSequence) =
    !parseSuccessful(input)

  "floatingConstant" should "少数にマッチする。" in {
    assert(parseSuccessful("31.41e-1f"))
    assert(parseSuccessful(".56e3l"))
    assert(parseSuccessful(".123"))
    assert(parseSuccessful("10.e5"))
    assert(parseSuccessful("12."))
    assert(parseSuccessful("123e4"))
  }

  it should "パースに失敗する。" in {
    assert(parseFailure(""))
    assert(parseFailure("."))
    assert(parseFailure("e21"))
    assert(parseFailure("0"))
    assert(parseFailure("a"))
  }
}

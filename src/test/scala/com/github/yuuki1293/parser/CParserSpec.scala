package com.github.yuuki1293.parser

import com.github.yuuki1293.parser.CParser.parseAll
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.input.CharArrayReader

class CParserSpec extends AnyFlatSpec with Diagrams {
  "integer" should "数値リテラルを解析する" in {
    assert(
      parseAll(CParser.integer, "123")
        .successful
    )
  }
}

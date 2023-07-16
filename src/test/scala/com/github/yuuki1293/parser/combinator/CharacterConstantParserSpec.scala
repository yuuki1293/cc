package com.github.yuuki1293.parser.combinator

import com.github.yuuki1293.parser.combinator.CharacterConstantParser.parseAll
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

class CharacterConstantParserSpec extends AnyFlatSpec with Diagrams {
  private def parseSuccessful(input: CharSequence) =
    parseAll(CharacterConstantParser.charConstant, input)
      .successful

  private def parseFailure(input: CharSequence) =
    !parseSuccessful(input)

  "charConstant" should "1文字をパースする。" in {
    assert(parseSuccessful("""' '"""))
    assert(parseSuccessful("""'a'"""))
    assert(parseSuccessful("""'\n'"""))
    assert(parseSuccessful("""'\\'"""))
    assert(parseSuccessful("""'\0'"""))
    assert(parseSuccessful("""'\x1F00'"""))
    assert(parseSuccessful("'\t'"))
    assert(parseSuccessful("""L'あ'"""))
  }

  it should "パースに失敗する。" in {
    assert(parseFailure(""))
    assert(parseFailure("""''"""))
    assert(parseFailure("""'ab'"""))
    assert(parseFailure("""'\'"""))
    assert(parseFailure("'\n'"))
    assert(parseFailure("""'\900'"""))
    assert(parseFailure("""'''"""))
    assert(parseFailure("'\u0000'"))
  }
}

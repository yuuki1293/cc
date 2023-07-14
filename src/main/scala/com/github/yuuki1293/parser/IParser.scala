package com.github.yuuki1293.parser

import scala.util.parsing.combinator.Parsers
import scala.util.parsing.input.Reader

trait IParser extends Parsers {
  def parse[A](in: Reader[Char]): ParseResult[A]
}

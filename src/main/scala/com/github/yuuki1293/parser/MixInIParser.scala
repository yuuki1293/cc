package com.github.yuuki1293.parser

trait MixInIParser extends UsesIParser {
  val cParser: IParser = CParser
}

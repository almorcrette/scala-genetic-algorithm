package model

trait Encodable {
  def encoding: String
}

trait EnumerableCode {
  def allCodes: Seq[String]
}

trait Decodable[T] {
  def decoded(string: String): T
}

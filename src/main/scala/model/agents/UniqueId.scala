package model.agents

import java.util.UUID

case class UniqueId(value: String)

object UniqueId {
  def apply(): UniqueId = UniqueId(UUID.randomUUID().toString)
}

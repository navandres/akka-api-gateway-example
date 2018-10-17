package jp.co.dzl.example.akka.api.handler

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import de.knutwalker.akka.http.support.CirceHttpSupport
import jp.co.dzl.example.akka.api.response.MessageResponse
import io.circe.generic.auto._

class RootHandler(handlers: List[Handler]) extends Handler with CirceHttpSupport {
  def routes = {
    val root = pathSingleSlash {
      get {
        complete(OK -> MessageResponse("Hello, example for reverse proxy"))
      }
    }

    handlers.foldRight(root)(_.routes ~ _)
  }
}

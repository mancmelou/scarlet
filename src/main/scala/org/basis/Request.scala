package org.basis

import scala.collection.mutable.Map
import javax.servlet.http.HttpServletRequest
import collection.JavaConversions.enumerationAsScalaIterator

class Request(private val req: HttpServletRequest, private val routeParams: Map[String, String]) {
  /**
   * Request headers container
   */
  private val _headers = Map.empty[String, String]

  /**
   * Request GET and POST params container
   */
  private val _params  = Map.empty[String, String]

  /**
   * Maps servlet request headers
   */
  private def mapRequestHeaders() {
    for(name <- req.getHeaderNames()) {
      _headers(name.asInstanceOf[String]) = req.getHeader(name.asInstanceOf[String])
    }
  }

  /**
   * Maps servlet request params
   */
  private def mapRequestParams() {
    for(name <- req.getParameterNames()) {
      _params(name.asInstanceOf[String]) = req.getParameter(name.asInstanceOf[String])
    }

    _params ++= routeParams
  }

  /**
   * Returns the specified header's value
   *
   * @param name  Header name
   * @return      Header value
   */
  def header(name: String) = _headers.getOrElse(name, "")

  /**
   * Returns the specified param's value
   *
   * @param name  Param name
   * @return      Param value
   */
  def param(name: String)  = _params.getOrElse(name, "")

  /**
   * Returns current request's {@link HttpServletRequest} object
   *
   * @return
   */
  def servlet = req

  /**
   * Returns map of the fields stored in this object
   *
   * @return Mapping
   */
  def toMap: Map[String, String] = {
    Map(
      "headers" -> _headers.toString,
      "params"  -> _params.toString
    )
  }

  /**
   * Constructor procedure
   */
  mapRequestHeaders()
  mapRequestParams()
}
/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gatling.http.check.header

import io.gatling.core.session.{ Expression, RichExpression }
import io.gatling.http.check.{ HttpCheckBuilders, HttpMultipleCheckBuilder }
import io.gatling.http.response.Response

object HttpHeaderCheckBuilder {

	def header(headerName: Expression[String]) =
		new HttpMultipleCheckBuilder[Response, String](HttpCheckBuilders.headerCheckFactory, HttpCheckBuilders.passThroughResponsePreparer) {
			def findExtractor(occurrence: Int) = headerName.map(new SingleHttpHeaderExtractor(_, occurrence))
			def findAllExtractor = headerName.map(new MultipleHttpHeaderExtractor(_))
			def countExtractor = headerName.map(new CountHttpHeaderExtractor(_))
		}
}

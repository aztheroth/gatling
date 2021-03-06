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
package io.gatling.http.dom

import java.net.URI

import scala.io.Codec.UTF8

import org.apache.commons.io.IOUtils
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import io.gatling.core.util.IOHelper.withCloseable

@RunWith(classOf[JUnitRunner])
class HtmlParserSpec extends Specification {

	"parsing Akka.io page" should {

		val htmlContent = withCloseable(getClass.getClassLoader.getResourceAsStream("akka.io.html")) {
			IOUtils.toString(_, UTF8.charSet)
		}

		"extract all urls" in {
			HtmlParser.getEmbeddedResources(new URI("http://akka.io"), htmlContent).map(res => res.uri.toString -> res.resType) must beEqualTo(Seq(
				"http://akka.io/resources/favicon.ico" -> Regular,
				"http://akka.io/resources/stylesheets/style.css" -> Css,
				"http://fonts.googleapis.com/css?family=Exo:300,400,600,700" -> Css,
				"http://akka.io/resources/stylesheets/prettify-frontpage.css" -> Css,
				"http://akka.io/resources/stylesheets/base.css" -> Css,
				"http://akka.io/resources/images/logo-small.png" -> Regular,
				"http://akka.io/resources/images/logo_dropshadow.png" -> Regular,
				"http://akka.io/resources/images/scala-sm.png" -> Regular,
				"http://akka.io/resources/images/ubs.png" -> Regular,
				"http://akka.io/resources/images/klout.png" -> Regular,
				"http://akka.io/resources/images/ign.png" -> Regular,
				"http://akka.io/resources/images/tdc.png" -> Regular,
				"http://akka.io/resources/images/vmware.png" -> Regular,
				"http://akka.io/resources/images/csc.png" -> Regular,
				"http://akka.io/resources/images/moshimonsters.png" -> Regular,
				"http://akka.io/resources/images/amazon.png" -> Regular,
				"http://akka.io/resources/images/zeebox.png" -> Regular,
				"http://akka.io/resources/images/creditsuisse.png" -> Regular,
				"http://akka.io/resources/images/autodesk.png" -> Regular,
				"http://akka.io/resources/images/atos.png" -> Regular,
				"http://akka.io/resources/images/blizzard.png" -> Regular,
				"http://akka.io/resources/images/rss.png" -> Regular,
				"http://akka.io/resources/images/watermark.png" -> Regular,
				"http://akka.io/resources/javascript/jquery.js" -> Regular,
				"http://akka.io/resources/javascript/prettify.js" -> Regular,
				"http://akka.io/resources/javascript/slideleft.js" -> Regular,
				"http://akka.io/resources/javascript/jquery.livetwitter.js" -> Regular,
				"http://akka.io/resources/javascript/livetwitter.js" -> Regular,
				"http://akka.io/resources/javascript/jquery.rss.min.js" -> Regular,
				"http://akka.io/resources/javascript/blogfeed.js" -> Regular,
				"http://akka.io/resources/javascript/moment.js" -> Regular,
				"http://akka.io/resources/javascript/dateparse.js" -> Regular,
				"http://akka.io/resources/javascript/jquery.scrollTo-1.4.2-min.js" -> Regular,
				"http://akka.io/resources/javascript/jquery.localscroll-1.2.7-min.js" -> Regular,
				"http://akka.io/resources/javascript/jquery.serialScroll-1.2.2-min.js" -> Regular,
				"http://akka.io/resources/javascript/sliderbox.js" -> Regular))
		}
	}
}

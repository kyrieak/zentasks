package controllers;

import models.Uzer;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.*;

import play.mvc.*;
import play.libs.*;
import play.test.*;
import static play.test.Helpers.*;

import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;

public class LoginTest extends WithApplication {
   @Before
   public void setUp() {
      start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
      new Uzer("momo@taro.com", "Taro", "kibidango-ooo-").save();
   }

   
/* https://github.com/playframework/playframework/blob/master/documentation/
 * manual/javaGuide/tutorials/zentasks/JavaGuide4.md
 * the tutorial on the main site says 302, but it should be 303. source above.
 */
   @Test
   public void authSuccess() {
       Result result = callAction(
                          controllers.routes.ref.Application.auth(),
                          fakeRequest().withFormUrlEncodedBody(
                             ImmutableMap.of(
                                "email", "momo@taro.com",
                                "password", "kibidango-ooo-"
                             )
                          ));

       assertEquals(303, status(result));
       assertEquals("momo@taro.com", session(result).get("email"));
   }
   
   @Test
   public void authFail() {
      Result result = callAction(
                           controllers.routes.ref.Application.auth(),
                           fakeRequest().withFormUrlEncodedBody(
                              ImmutableMap.of(
                                 "email", "momo@taro.com",
                                 "password", "password"
                              )
                           ));
      
      assertEquals(400, status(result));
      assertNull(session(result).get("email"));
   }
}
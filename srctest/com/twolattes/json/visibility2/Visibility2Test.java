package com.twolattes.json.visibility2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.Test;

import com.twolattes.json.Marshaller;
import com.twolattes.json.visibility1.PublicClassPackagePrivateConstructor;
import com.twolattes.json.visibility1.PublicClassPrivateConstructor;
import com.twolattes.json.visibility1.PublicClassProtectedConstuctor;

public class Visibility2Test {
  @Test
  public void testPublicClassPackagePrivateConstructorM() throws Exception {
    JSONObject o = Marshaller
        .create(PublicClassPackagePrivateConstructor.class)
        .marshall(PublicClassPackagePrivateConstructor.create());
    
    assertEquals(1, o.length());
    assertEquals(9, o.get("value"));
  }
  
  @Test
  public void testPublicClassPrivateConstructorM() throws Exception {
    JSONObject o = Marshaller
        .create(PublicClassPrivateConstructor.class)
        .marshall(PublicClassPrivateConstructor.create());
    
    assertEquals(1, o.length());
    assertEquals(9, o.get("value"));
  }
  
  @Test
  public void testPublicClassProtectedConstuctorM() throws Exception {
    JSONObject o = Marshaller
        .create(PublicClassProtectedConstuctor.class)
        .marshall(PublicClassProtectedConstuctor.create());
    
    assertEquals(1, o.length());
    assertEquals(9, o.get("value"));
  }

  @Test
  public void testPublicClassPackagePrivateConstructorU() throws Exception {
    PublicClassPackagePrivateConstructor e = Marshaller
        .create(PublicClassPackagePrivateConstructor.class)
        .unmarshall(new JSONObject("{value:4}"));
    
    assertNotNull(e);
    assertEquals(4, e.value);
  }

  @Test
  public void testPublicClassPrivateConstructorU() throws Exception {
    PublicClassPrivateConstructor e = Marshaller
        .create(PublicClassPrivateConstructor.class)
        .unmarshall(new JSONObject("{value:4}"));
    
    assertNotNull(e);
    assertEquals(4, e.value);
  }

  @Test
  public void testPublicClassProtectedConstuctorU() throws Exception {
    PublicClassProtectedConstuctor e = Marshaller
        .create(PublicClassProtectedConstuctor.class)
        .unmarshall(new JSONObject("{value:4}"));
    
    assertNotNull(e);
    assertEquals(4, e.value);
  }
}
package com.twolattes.json.collection;

import static com.twolattes.json.Json.string;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;

import com.twolattes.json.Json;
import com.twolattes.json.TwoLattes;

public class CollectionTest {

  @Test
  public void sortedSet1() throws Exception {
    EntityWithSortedSet1 e = TwoLattes.createMarshaller(EntityWithSortedSet1.class)
        .unmarshall((Json.Object) Json.fromString("{\"names\":[\"a\",\"c\",\"b\"]}"));

    assertNotNull(e.names);
    assertEquals(3, e.names.size());
    Iterator<String> iterator = e.names.iterator();
    assertEquals("a", iterator.next());
    assertEquals("b", iterator.next());
    assertEquals("c", iterator.next());
    assertFalse(iterator.hasNext());
  }

  @Test
  public void sortedSet2() throws Exception {
    EntityWithSortedSet2 entityWithSortedSet2 = new EntityWithSortedSet2();
    entityWithSortedSet2.entities = new TreeSet<PolymorphicEntity>();
    entityWithSortedSet2.entities.add(new ChildPolymorphicEntity());

    Json.Object o = TwoLattes.createMarshaller(EntityWithSortedSet2.class)
        .marshall(entityWithSortedSet2);

    assertEquals(1, o.size());
    Json.Array entities = (Json.Array) o.get(string("entities"));
    assertEquals(1, entities.size());
    assertEquals(
        string("child"),
        ((Json.Object) entities.get(0)).get(string("type")));
  }

  @Test
  public void sortedSet3() throws Exception {
    EntityWithSortedSet3 entityWithSortedSet3 = new EntityWithSortedSet3();
    entityWithSortedSet3.entities = new TreeSet<PolymorphicEntity>();
    entityWithSortedSet3.entities.add(new ChildPolymorphicEntity());

    Json.Object o = TwoLattes.createMarshaller(EntityWithSortedSet3.class)
        .marshall(entityWithSortedSet3);

    assertEquals(1, o.size());
    Json.Array entities = (Json.Array) o.get(string("entities"));
    assertEquals(1, entities.size());
    assertEquals(
        string("child"),
        ((Json.Object) entities.get(0)).get(string("type")));
  }

}

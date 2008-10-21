package com.twolattes.json;

import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Array;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A descriptor for arrays.
 *
 * @author pascallouis
 */
class ArrayDescriptor extends AbstractDescriptor<Object, Object> {
  private final Descriptor<Object, Object> elementsDescriptor;

  @SuppressWarnings("unchecked")
  ArrayDescriptor(Descriptor elementsDescriptor) {
    super(Array.class);
    this.elementsDescriptor = elementsDescriptor;
  }

  @Override
  public boolean isInlineable() {
    return elementsDescriptor.isInlineable();
  }

  public Object marshall(Object entity, String view) {
    if (entity == null) {
      return JSONArray.NULL;
    }
    checkState(entity.getClass().isArray(), "array expected");
    JSONArray jsonArray = new JSONArray();
    int l = Array.getLength(entity);
    if (elementsDescriptor.shouldInline()) {
      for (int i = 0; i < l; i++) {
        jsonArray.put(elementsDescriptor.marshallInline(Array.get(entity, i), view));
      }
    } else {
      for (int i = 0; i < l; i++) {
        jsonArray.put(elementsDescriptor.marshall(Array.get(entity, i), view));
      }
    }
    return jsonArray;
  }

  public Object unmarshall(Object object, String view) {
    if (JSONObject.NULL.equals(object)) {
      return null;
    }
    checkState(object instanceof JSONArray);
    JSONArray jsonArray = (JSONArray) object;
    Object[] array = (Object[]) Array.newInstance(elementsDescriptor.getReturnedClass(), jsonArray.length());
    try {
      if (elementsDescriptor.shouldInline()) {
        for (int i = 0; i < array.length; i++) {
          array[i] = elementsDescriptor.unmarshallInline(jsonArray.get(i), view);
        }
      } else {
        for (int i = 0; i < array.length; i++) {
          array[i] = elementsDescriptor.unmarshall(jsonArray.get(i), view);
        }
      }
    } catch (JSONException e) {
      throw new IllegalStateException(e);
    }
    return array;
  }

  @Override
  public String toString() {
    return elementsDescriptor + "[]";
  }
}
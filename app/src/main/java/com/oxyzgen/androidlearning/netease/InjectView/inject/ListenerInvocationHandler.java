package com.oxyzgen.androidlearning.netease.InjectView.inject;

import android.app.Activity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ListenerInvocationHandler implements InvocationHandler {
  private Object target;

  private HashMap<String,Method> methodHashMap=new HashMap<>();

  public ListenerInvocationHandler(Activity activity) {
    this.target=activity;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    if (null != target){

      String name=method.getName();
      // 原接口可能有多个方法
      method=methodHashMap.get(name);

      if (method != null) {
        // 获取方法参数
        Type[] parameterTypes = method.getGenericParameterTypes();
        if (parameterTypes.length == 0) { // 加入方法参数判断
          return method.invoke(target);
        } else {
          return method.invoke(target, args);
        }
      }

    }

    return null;
  }


  public void addMethod(String methodName,Method method){
    methodHashMap.put(methodName,method);
  }

}

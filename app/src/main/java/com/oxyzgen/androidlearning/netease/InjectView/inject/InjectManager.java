package com.oxyzgen.androidlearning.netease.InjectView.inject;

import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.oxyzgen.androidlearning.netease.InjectView.annotation.ContentView;
import com.oxyzgen.androidlearning.netease.InjectView.annotation.EventBase;
import com.oxyzgen.androidlearning.netease.InjectView.annotation.InjectView;
import com.oxyzgen.androidlearning.netease.InjectView.annotation.OnItemClick;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectManager {

  public static void inject(Activity activity) {
    injectLayout(activity);
    injectViews(activity);
    injectEvents(activity);
    injectListOnClickEvents(activity);
  }

  private static void injectViews(Activity activity) {
    Class<? extends Activity> cls = activity.getClass();
    Field[] fields = cls.getDeclaredFields();
    for (Field field : fields) {
      InjectView injectView = field.getAnnotation(InjectView.class);
      if (null != injectView) {
        int vId = injectView.value();
        field.setAccessible(true);
        try {
          field.set(activity, activity.findViewById(vId));

          /*
                    method = clazz.getMethod("findViewById", int.class);
                    View view = (View) method.invoke(activity, viewId);
                    field.setAccessible(true);
                    field.set(activity, view);
           */
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static void injectListOnClickEvents(Activity activity) {
    Class<? extends Activity> clazz = activity.getClass();

    Method[] methods = clazz.getDeclaredMethods();
    for (final Method method : methods) {
      OnItemClick onItemClick = method.getAnnotation(OnItemClick.class);

      if (onItemClick != null) {
        int recycleViewId = onItemClick.value();
        View recycleView = activity.findViewById(recycleViewId);
        if (recycleView instanceof RecyclerView) {
          ((RecyclerView) recycleView).addOnItemTouchListener(new RecyclerItemClickListener(activity, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
              try {
                method.invoke(activity, view, position);
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          }));
        }
      }
    }
  }

  private static void injectEvents(Activity activity) {
    Class<? extends Activity> cls = activity.getClass();
    Method[] methods = cls.getDeclaredMethods();

    for (Method method : methods) {
      Annotation[] annotations = method.getAnnotations();
      //注解对象本身
      for (Annotation annotation : annotations) {
        // 注解Class  interface com.oxyzgen.androidlearning.netease.InjectView.annotation.OnClick
        Class<? extends Annotation> annotationType = annotation.annotationType();
        if (annotationType != null) {
          EventBase eventBase = annotationType.getAnnotation(EventBase.class);
          if (eventBase != null) {
            String callListener = eventBase.callListener();
            String listenerSetter = eventBase.listenerSetter();
            Class<?> listenerType = eventBase.listenerType();

            try {
              Method valueMethod = annotationType.getDeclaredMethod("value");
//              Method valueMethod = annotation.getClass().getDeclaredMethod("value");
              int[] viewIds = (int[]) valueMethod.invoke(annotation);

              // AOP了一下  代理onClick方法,来执行被注解的method
              ListenerInvocationHandler invocationHandler = new ListenerInvocationHandler(activity);
              // 被代理的接口可能有多个方法
              invocationHandler.addMethod(callListener, method);
              // classLoader  被代理的接口  handler
              Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(),
                      new Class[]{listenerType}, invocationHandler);

              for (int viewId : viewIds) {
                View view = activity.findViewById(viewId);
                Method setter = view.getClass().getMethod(listenerSetter, listenerType);
//                setter.invoke(view,listenerType.newInstance());
                setter.invoke(view, listener); // listener，代理后的View.OnClickListener
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
  }

  private static void injectLayout(Activity activity) {

    Class<? extends Activity> aClass = activity.getClass();
    ContentView cv = aClass.getAnnotation(ContentView.class);
    if (null != cv) {
      int layout = cv.value();
      try {
        activity.setContentView(layout);
//        Method method = aClass.getMethod("setContentView", int.class);
//        method.invoke(activity, layout);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }


  }
}

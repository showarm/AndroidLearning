package com.oxyzgen.netease.NetManager;

import java.io.InputStream;

public interface CallbackListener {
  void onSuccess(InputStream inputStream);
  void onFailure();
}

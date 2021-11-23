package com.oxyzgen.androidlearning.NeteaseDemos.InjectView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oxyzgen.androidlearning.R;
import com.oxyzgen.netease.InjectView.annotation.ContentView;
import com.oxyzgen.netease.InjectView.annotation.InjectView;
import com.oxyzgen.netease.InjectView.annotation.OnClick;
import com.oxyzgen.netease.InjectView.annotation.OnItemClick;
import com.oxyzgen.netease.InjectView.annotation.OnLongClick;

import java.util.ArrayList;

@ContentView(R.layout.activity_inject_view1)
public class InjectView1Activity extends InjectBaseActivity {

  @InjectView(R.id.recycler_view)
  private RecyclerView recyclerView;
  private ArrayList<Fruit> fruitList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_inject_view1);
    initFruits();
//    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    FruitAdapter adapter = new FruitAdapter(fruitList);
    recyclerView.setAdapter(adapter);
  }

  private void initFruits() {
    fruitList=new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      Fruit apple = new Fruit("Apple", R.mipmap.ic_launcher);
      fruitList.add(apple);
      Fruit banana = new Fruit("Banana", R.mipmap.ic_launcher);
      fruitList.add(banana);
      Fruit orange = new Fruit("Orange", R.mipmap.ic_launcher);
      fruitList.add(orange);
      Fruit watermelon = new Fruit("Watermelon", R.mipmap.ic_launcher);
      fruitList.add(watermelon);
      Fruit pear = new Fruit("Pear", R.mipmap.ic_launcher);
      fruitList.add(pear);
      Fruit grape = new Fruit("Grape", R.mipmap.ic_launcher);
      fruitList.add(grape);
      Fruit pineapple = new Fruit("Pineapple", R.mipmap.ic_launcher);
      fruitList.add(pineapple);
      Fruit strawberry = new Fruit("Strawberry", R.mipmap.ic_launcher);
      fruitList.add(strawberry);
      Fruit cherry = new Fruit("Cherry", R.mipmap.ic_launcher);
      fruitList.add(cherry);
      Fruit mango = new Fruit("Mango", R.mipmap.ic_launcher);
      fruitList.add(mango);
    }
  }

  @OnClick(R.id.btn)
  public void show(View view){
    Log.d("show","234141");
    Toast.makeText(this, "show1", Toast.LENGTH_SHORT).show();
  }

  @OnLongClick(R.id.btn)
  public boolean show2(View view){
    Log.d("show","234141");
    Toast.makeText(this, "show2", Toast.LENGTH_SHORT).show();
    return true;
  }

  @OnItemClick(R.id.recycler_view)
  public void OnRecycleItemClick(View view,int position){
    Toast.makeText(this, "OnRecycleItemClick"+position, Toast.LENGTH_SHORT).show();
  }

}
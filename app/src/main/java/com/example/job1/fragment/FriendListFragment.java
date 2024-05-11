package com.example.job1.fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.example.job1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendListFragment extends Fragment {

    private ListView listView;
    private LottieAnimationView animationView;
    private FrameLayout listContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friend_list, container, false);

        listView = view.findViewById(R.id.listview_friends);

        // 数据填充代码
        // 创建一个字符串数组作为数据源
        String[] items = new String[]{"item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8", "item9", "item10"};

        // 创建一个 List<Map<String, String>> 作为 SimpleAdapter 的数据源
        List<Map<String, String>> data = new ArrayList<>();
        for (String item : items) {
            Map<String, String> map = new HashMap<>();
            map.put("item", item);
            data.add(map);
        }

        // 创建 SimpleAdapter，并设置数据源
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, android.R.layout.simple_list_item_1, new String[]{"item"}, new int[]{android.R.id.text1});

        // 将适配器设置到 ListView
        listView.setAdapter(adapter);

        animationView = view.findViewById(R.id.animation_view);
        listContainer = view.findViewById(R.id.list_container);

        // 设置动画
        animationView.setAnimation(R.raw.loading);
        animationView.loop(true);
        animationView.playAnimation();

        // 确保在动画开始前ListView是不可见的
        listContainer.setAlpha(0f);
        listContainer.setVisibility(View.VISIBLE); // 需要设置为VISIBLE，否则动画不会执行

        // 创建一个ValueAnimator来实现淡入效果
        ValueAnimator fadeInAnimator = ValueAnimator.ofFloat(0f, 1f);
        fadeInAnimator.setDuration(500); // 设置动画时长
        fadeInAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                listContainer.setAlpha(value); // 逐渐改变透明度
            }
        });

        // 添加动画结束监听器，用于在动画结束后取消Lottie动画
        fadeInAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationView.pauseAnimation(); // 暂停Lottie动画
                animationView.setProgress(0f); // 重置动画进度
                animationView.loop(false); // 设置为不循环
            }
        });

        // 使用Handler在动画结束后5秒开始淡入动画
        new Handler().postDelayed(() -> {
            animationView.setVisibility(View.INVISIBLE);
            // 开始淡入动画
            fadeInAnimator.start();

        }, 2000);

        return view;
    }
}


